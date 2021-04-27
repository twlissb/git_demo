layui.use(['layer','table', 'treeTable'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var treeTable = layui.treeTable;
        var layer = layui.layer;
        
        // 渲染表格
		var insTb = treeTable.render({
            elem: '#newsList',
            url: 'Menu?action=allMenu',
            toolbar: '#an',
            height: 'full-115',
            tree: {
                iconIndex: 2,           // 折叠图标显示在第几列
                isPidData: true,        // 是否是id、pid形式数据
                idName: 'id',  // id字段名称
                pidName: 'mfatherid'     // pid字段名称
            },
            cols: [[
            	{field: 'id', title: '权限编号',width:10},
                {type: 'radio'},
                {field: 'mname', title: '权限名称'},
                {field: 'type', width: 80, align: 'center', title: '类型', templet: function (d) {
                        if (d.type == 3) {
                            return '<span class="layui-badge layui-bg-orange">按钮</span>';
                        }
                        if (d.mfatherid == -1) {
                            return '<span class="layui-badge layui-bg-blue">目录</span>';
                        } else {
                            return '<span class="layui-badge layui-bg-green">菜单</span>';
                        }
                }}
            ]]
        });
		
	/*------------- 加载用户数据 --end------------------------------*/


	//监听工具栏
		treeTable.on('toolbar(newsList)', function(obj) {
		switch (obj.event) {
		/*  case 'btn-expand':	//全部展开
			  insTb.expandAll('#demoTreeTb');
		  break;
		  case 'btn-fold':	//全部折叠
			  insTb.foldAll('#demoTreeTb');
		  break;*/
		case 'addMenu': //新增权限
			layer.open({
				title : "添加权限",
				type : 2,
				content : "quanxian/menuAdd.jsp",
				area : [ '800px', '700px' ],
			})
			break;

		case 'upMenu': //修改权限
			updataMenu();
			break;

		case 'delMenu': //删除权限
			layer.confirm('确定删除此权限吗?', {
				icon : 3,
				title : '提示'
			}, function(index) {
				delMenu();
				layer.close(index);
			});
			break;
		}
		;
	});

	//---------删除权限-------
	function delMenu() {
		var menuid = '';
		JSON.stringify(insTb.checkStatus().map(function(d) {
			menuid = d.id;
		}));
		if (menuid == null || menuid == "") {
			layer.msg("请选中一个节点进行删除");
		} else {
			$.ajax({
				url : "Menu?action=delMenu",
				data : {
					"menuid" : menuid
				},
				type : "post",
				dataType : "json",
				success : function(data) {
					//var info = eval("("+data+")");
					if (data.status == 1) {
						layer.msg("删除成功");
						insTb.reload();
					} else {
						layer.msg("删除失败");
					}
				}
			})
		}
	}

	/*---------修改权限------*/
	function updataMenu() {
		var authorityId = '';
		JSON.stringify(insTb.checkStatus().map(function(d) {
			authorityId = d.id;
		}));
		if (authorityId == null || authorityId == "") {
			layer.msg('请选择一个进行修改');
		} else {
			layer.open({
				type : 2,
				title : "修改权限",
				area : [ '800px', '700px' ],
				content : "quanxian/menuInfo.jsp",
				success : function(layero, index) {
					$.post("Menu?action=allMenuById", {
						"menuid" : authorityId
					}, function(data) {
						var info = eval('(' + data + ')')
						var body = layui.layer.getChildFrame('body', index);
						body.find("#mid").val(info.data.id);
						body.find("#mname").val(info.data.mname); //权限名
						body.find("#mfunction").val(info.data.url); //请求路径
						var select = 'dd[lay-value=' + info.data.type + ']';
						body.find("#type2").siblings("div.layui-form-select").find('dl').find(select).click(); //菜单样式
						body.find("#mbtn").val(info.data.btn); //按钮代码
						body.find("#icon").val(info.data.icon); //icon图标
						var menuid3 = info.data.mfatherid;
						//上级的下拉框
						$.post("Menu?action=allMenuById", {
							"menuid" : menuid3
						}, function(res) {
							var cs = eval('(' + res + ')');
							body.find("#fatherType2").val(cs.data.mname);
						})

					})
				}
			})
		}
	}
	;
});