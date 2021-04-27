layui.extend({
    dtree: '{/}js/lay-module/layui_ext/dtree/dtree'   // {/}的意思即代表采用自有路径，即不跟随 base 路径
}).use(['form','layer','laydate','table','laytpl','dtree'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    	var dtree = layui.dtree, layer = layui.layer, $ = layui.jquery;
    

    /*------------- 加载用户数据 --------------------------------*/
    var tableIns = table.render({
        elem: '#newsList',
        url : '/quanxian/juese',
        toolbar: '#an',
        page : true,
        height: 'full-145',
        limit : 10,
        limits : [10,15,20,25],
        cols : [[
        	{fixed:"left",type: "checkbox", width:50},
            
            {field: 'id', title: '编号',  align:'center'},
            {field: 'name', title: '职位', minWidth:150, align:'center'},
           
        ]]
    });
    /*------------- 加载用户数据 --end------------------------------*/

    /*-------- 搜索用户 ----------------------------*/
    $("#doSubmit").click(function(){
    	var like = $("#likename").val()
    	 tableIns.reload({
	      url:"http://localhost:8080/quanxian/jubu?uname="+like,
	      page: {
	        curr: 1 //重新从第 1 页开始
	      }
	    });
    })
    
     //工具栏事件
	  table.on('toolbar(newsList)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    var data = checkStatus.data;
	    var userid = '';
	    for(i=0;i<data.length;i++){
	    	userid = data[i].id;
	    }
	    switch(obj.event){
	      case 'hairMenu':	//分配权限
				if(data.length == 0 || data.length > 1){
					layer.msg("请选择一行数据进行操作")
					return ;
				}else{
					hairMenu(userid);
				}
	      break;
	      
	      case 'addjuese':	//新增用户
	    	  addUser();
	      break;
	      
	      case 'hairRole':	//分配角色
	    	  if(data.length == 0 || data.length > 1){
					layer.msg("请选择一行数据进行操作")
					return ;
				}else{
					//HairRole(userid);
				}
	      break;
	      
	      case 'upjuese':	//修改用户信息
	    	  if(data.length == 0 || data.length > 1){
					layer.msg("请选择一行数据进行操作")
					return ;
				}else{
					upRole(userid);
				}
	      break;
		        
	      case 'deljuese':	//删除用户
	    	  if(data.length == 0 || data.length > 1){
					layer.msg("请选择一行数据进行操作")
					return ;
				}else{
					layer.confirm('确定删除用户吗', {icon: 3, title:'提示'}, function(index){
						var loginName = $("#loginName").val();
						if(userid == loginName){
							layer.msg("你正在登录当前账号,无法删除")
						}else{
							delUser(userid);
							layer.close(index);
						}
		            });
				}
	      break;
	    };
	  });
    
    //修改用户
  //修改角色
	function upRole(roleid){
		layui.layer.open({
    		title : "修改角色",
    		type : 2,
    		content : "juese/roleInfo.jsp",
    		area:['350px','230px'],
    		success:function(layero, index){
    			$.ajax({
    				url:"/quanxian/role?action=allRoleByRoleid",
    				type:"post",
    				data:{"roleid":roleid},
    				success:function(data){
    					var info = eval("("+data+")")
    					var body = layui.layer.getChildFrame('body', index);
    					body.find("#roleid").val(info.id);
    					body.find("#rname2").val(info.name);
    					body.find("#rname").val(info.name);
    				}
    			})
/*        			//获取新窗口对象
                var iframeWindow = layero.find('iframe')[0].contentWindow;
                //重新渲染
                iframeWindow.layui.form.render();*/
    		}
		});
	}
    
    //删除用户
    function delUser(roleid){
    	$.ajax({
    		url:"role?action=delrole",
    		data:{"roleid":roleid},
    		type:"post",
    		success:function(data){
    			if(data == 1){
    				layer.msg("删除成功")
    				tableIns.reload("#newsList");
    			}
    		}
    	})
    }
    
    
    //分配权限
    function hairMenu(userid){
    	layui.layer.open({
    		title : "分配权限",
    		type : 1,
    		content : $('#dtree1'),
    		area:['300px','500px'],
    		success:function(){
    		    //给dtree树加载数据
    			dtree.render({
				  elem: "#dataTree3",
				  url: "/MenuTest/MenuServletInterface?action=allMenuDtree",
				  dataStyle: "layuiStyle",  //使用layui风格的数据格式
				  dataFormat: "list",  //配置data的风格为list
				  response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
				  checkbar:true,
				  line: true,  // 显示树线
				  done: function(res, $ul, first){
					  $.ajax({
						  url:"/MenuTest/MenuServletInterface?action=menuByUseridType1",
						  type:"post",
						  data:{"userid":userid},
						  success:function(res){
							  var cs = eval('(' + res + ')');
							  $.each(cs,function(index,row){
								dtree.chooseDataInit("dataTree3",[row.id]); // 初始化选中
							  })
						  }
					  })
  		    	  }
    			});
    		},
    		btn:['分配权限'],
    		yes: function(index, layero){
    			var params = dtree.getCheckbarNodesParam("dataTree3");
    			var infos = JSON.stringify(params);
    			var cs = eval('(' + infos + ')');
    			var menuidList = new Array();	//所有选中值的权限id
    			//alert(menuidList.length);
    			$.each(cs,function(index,row){
					menuidList[index] = row.nodeId;
    			})
    			$.ajax({
    				url:"/MenuTest/MenuServlet?action=menuByUserid",
    				data:{"array":menuidList,"userid":userid},
    				type:"post",
    				traditional:true,
    				success:function(data){
    					var demo = eval('(' + data + ')');
    					if(demo.status == 1){
    						layer.msg(demo.message);
    						layer.close(index)	//关闭
    					}else{
    						layer.msg("分配失败");
    					}
    				}
    			})
    		}
    	})
    }

    //分配角色
    function HairRole(userid){
    	layer.open({
    		type:1,
    		title:"分配角色",
    		area:['200px','100px'],
    		content:$('#hairRole'),
    		success:function(){
    			//查询全部角色
    	    	$.ajax({
    	    		url:"/MenuTest/RoleServlet?action=hairRole",
    	    		type:"post",
    	    		dataType:"json",
    	    		success:function(data){
    	    			
    	    		}
    	    	})
    		}
    	})
    }
    
    //新增用户
    function addUser(){
    	layui.layer.open({
    		title : "添加用户",
    		type : 2,
    		content : "juese/roleAdd.jsp",
    		area:['400px','490px'],
    	})
    }
    
     

})