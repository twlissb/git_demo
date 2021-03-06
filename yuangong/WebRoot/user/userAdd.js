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
			layer.alert("?????????????????????3??????")
			return false;
		} else if (pass.length < 5 || pass.length > 19) {
			layer.alert('????????????6???12???????????????????????????');
			return false;
		} else if (realName.length == "" || realName.length == null) {
			layer.alert('?????????????????????');
			return false;
		}
		$.ajax({
			url : "/yuangong/User?action=addUser",
			data : data,
			tyep : "post",
			success : function(data) {
				if (data == 1) {
					layer.msg("????????????")
					setTimeout(function() {
						/*layer.closeAll("iframe");
							            //???????????????
							            parent.location.reload();*/

						var index = parent.layer.getFrameIndex(window.name); //???????????????iframe????????????
						parent.layer.close(index); //???????????????
						parent.layui.table.reload("newsList");

					}, 1000);
				} else {
					layer.msg("????????????");
				}

			}
		})
		return false;
	})

});