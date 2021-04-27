layui.use([ 'form', 'layedit', 'laydate' ], function() {
	var form = layui.form,
		layer = layui.layer,
		layedit = layui.layedit,
		laydate = layui.laydate;

	$.ajax({
		url : "/yuangong/User?action=bumen",
		type : "post",
		success : function(data) {
			var info = eval("(" + data + ")");
			var row = info.data;
			var role = $("#role1");
			var html = '';
			$.each(row, function(index, item) {
				html += '<option value="' + item.id + '">' + item.name + '</option>';
			})
			role.html(html);
			form.render("select");
		}
	})
	$.ajax({
		url : "/yuangong/User?action=zhi",
		type : "post",
		success : function(data) {
			var info = eval("(" + data + ")");
			var row = info.data;
			var role = $("#role2");
			var html = '';
			$.each(row, function(index, item) {
				html += '<option value="' + item.id + '">' + item.name + '</option>';
			})
			role.html(html);
			form.render("select");
		}
	})
	$.ajax({
		url : "/yuangong/User?action=role",
		type : "post",
		success : function(data) {
			var info = eval("(" + data + ")");
			var row = info.data;
			var role = $("#role3");
			var html = '';
			$.each(row, function(index, item) {
				html += '<option value="' + item.id + '">' + item.name + '</option>';
			})
			role.html(html);
			form.render("select");
		}
	})



	function checkUname(uname) {
		var is = false;
		$.ajax({
			url : "/MenuTest/UserServlet?action=isUname",
			data : {
				"uname" : uname
			},
			async : false,
			type : "post",
			success : function(data) {
				if (data == 0) {
					is = true;
				} else {
					is = false;
				}

			}
		})
		return is;
	}

	$("#tijiao").click(function() {
		var name = $("#uname").val();
		var pass = $("#password").val();
		var realName = $("#realName").val();
		var age = $("#age").val();
		var sex = $("input[type='radio']:checked").val();
		var role = $("select[name='role1']").val();
		var role2 = $("select[name='role2']").val();
		var role3 = $("select[name='role3']").val();
		var data = {
			"name" : name,
			"pass" : pass,
			"realName" : realName,
			"age" : age,
			"sex" : sex,
			"role" : role,
			"role2" : role2,
			"role3" : role3
		}
		if (name.length < 3) {
			layer.alert("登录名不能小于3位数")
			return false;
		} else if (pass.length < 5 || pass.length > 19) {
			layer.alert('密码必须6到12位，且不能出现空格');
			return false;
		} else if (realName.length == "" || realName.length == null) {
			layer.alert('用户名不能为空');
			return false;
		}
		$.ajax({
			url : "/yuangong/User?action=addUser",
			data : data,
			tyep : "post",
			success : function(data) {
				if (data == 1) {
					layer.msg("添加成功")
					setTimeout(function() {
						/*layer.closeAll("iframe");
							            //刷新父页面
							            parent.location.reload();*/

						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭
						parent.layui.table.reload("newsList");

					}, 1000);
				} else {
					layer.msg("系统异常");
				}

			}
		})
		return false;
	})

});