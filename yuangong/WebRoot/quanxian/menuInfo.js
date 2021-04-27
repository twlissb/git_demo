layui.use(['form','layer','layedit','laydate','upload'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
       
    /* --- 验证权限名是否存在 ---------------------------------------------------------------*/
    /*$("#mname").blur(function(){
    	var mname = $("#mname").val();  //权限
    	$.post("/admin/MenuServlet?action=isMenuName&mname="+mname,function(res){
    		var cs = eval('(' + res + ')');
    		if(res == 1){
    			layer.msg('权限名已存在');
    		}
		 })
    })*/
    /* --- 验证权限名是否存在 -- end -------------------------------------------------------------*/
    
    /* --- 验证请求路径是否存在 ----------------------------------------------------------------*/
    /*$("#mfunction").blur(function(){
    	var mfunction = $("#mfunction").val();  //请求路径
    	$.post("/admin/MenuServlet?action=isMenuUrl&mfunction="+mfunction,function(res){
    		var cs = eval('(' + res + ')');
    		if(res == 1){
    			layer.msg('请求路径已存在');
    		}
		 })
    })*/
    /* --- 验证请求路径是否存在 -- end --------------------------------------------------------------*/
    
    /* -- 修改权限 --------------------------------------------------------------- */
    $("#menuAjax").click(function(){
    	var mid = $("#mid").val();  //权限
    	var mname = $("#mname").val();  //权限名
	    var mfunction = $("#mfunction").val();  //请求路径
	    var type2 = $("#type2").val();  //请求类型
	    var icon = $("#icon").val();  //icon图标
	    var mbtn = $("#mbtn").val();  //按钮代码
	    var father = $("#father").val();  //父级
	    var data2 = {
        		"mid":mid,
        		"mname":mname,
        		"mfunction":mfunction,
        		"type2":type2,
        		"icon":icon,
        		"father":father,
        		"mbtn":mbtn
    	};
	    //权限名
        if(mname == "" || mname == null){
        	layer.msg("权限名不能为空");
        	return false;
        }
        
        //请求路径
        if(mfunction == "" || mfunction == null){
        	layer.msg("请求路径不能为空");
        	return false;
        }
    	
        //类型
        if(type2 == 0 || type2.size == 0){
        	layer.msg("请选择类型");
        	return false;
        }
        
        if(type2 != 1){
        	if(father == 0){
        		layer.msg("菜单或按钮不能没有上级");
            	return false;
        	}
        }
        if(type2 == 3){
        	if(mbtn == null || mbtn.length < 30 || mbtn==""){
            	layer.msg("按钮代码格式错误");
            	return false;
            }
        }
        if(type2 == 3){
        	mbtn = mname;
        }
        //ajax提交
        $.ajax({
        	url:"/yuangong/Menu?action=upMenu",
        	type:"post",
        	data:data2,
        	success:function(data){
        		var info = eval('(' + data + ')')
        		if(info.status == 1){
    	 			top.layer.msg("权限修改成功");
    	 			layer.closeAll("iframe");
    	            //刷新父页面
    	            parent.location.reload();
    	 		}else{
    	 			top.layer.msg("权限修改失败");
    	 		}
        	}
        })
        return false;
    })
    /* -- 修改权限 -- end ------------------------------------------------------------- */
   
    
    /*-----------类型选择下拉框的点击事件----------*/
    form.on('select(type2)', function(data){
    	var type = data.value;
        var dom = $("#goodsid");
        var mname = $("#mname").val();  //权限
        dom.html("");
        if(type == 3){
        	$("#mbtn").val('');
        	$("#iconclass").css("display","none");
        	$("#btnclss").css("display","block");
        	$.get("/yuangong/Menu?action=allButtenType",function (res) {
        		var cs = eval('(' + res + ')');
        		var btntype = $('#btnType');
        		var html = '<span value="0"></span>';
                $.each(cs,function (index, item) {
                	html += '<span class="btnType4" onclick="tongyiType(this)">'+item.btntype+'</span> ';
                });
                btntype.html(html);
        	})
        }else{
        	$("#iconclass").css("display","block");
        	$("#btnclss").css("display","none");
        }
        $.get("/yuangong/Menu?action=menuByType2&type="+type,function (res) {
        	var cs = eval('(' + res + ')');
            var data = cs.data;
            var dom = $("#father");
            if(type == 1){
            	var html = '<option value="0">目录没有上一级</option>';
            }else{
            	var html = '<option value="0">请选择</option>';
                $.each(data,function (index, item) {
                    html += '<option value="'+item.id+'">'+item.mname+'</option>';
                });
            }
            dom.html(html);
            //重新渲染下拉列表
            form.render("select");
        });
    });
    /*-----------类型选择下拉框的点击事件-- end --------*/

    
})
	/*---- 点击获取按钮代码事件 ------*/
	function tongyiType(obj){
		$("#mbtn").val('');
    	var info =  $(obj).html();
    	$("#mbtn").val(info);
    }
	/*---- 点击获取按钮代码事件 -- end ----*/
    