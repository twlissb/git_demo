<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.entity.user"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link href="layui-v2.5.4/layui/css/layui.css" rel="stylesheet">
<script src="layui-v2.5.4/layui/layui.js"></script>
</head>
<body>
	<form class="layui-form" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">输入框</label>
			<div class="layui-input-block">
				<input type="text" name="title" required lay-verify="required"
					placeholder="请输入账号" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码框</label>
			<div class="layui-input-inline">
				<input type="password" name="password" required
					lay-verify="required" placeholder="请输入密码" autocomplete="off"
					class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">辅助文字</div>
		</div>

		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="login">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

	<script>
		//Demo
		layui.use('form', function(){
		  var form = layui.form;
		  
		  //监听提交
		  form.on('submit(login)', function(data){
		    layer.msg(JSON.stringify(data.field));
		    return false;
		  });
		});
		</script>
	<script src="lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
	<script>
		layui.use([ 'form', 'jquery' ], function() {
			var $ = layui.jquery,
				form = layui.form,
				layer = layui.layer;
	
			// 登录过期的时候，跳出ifram框架
			if (top.location != self.location)
				top.location = self.location;
	
			$('.bind-password').on('click', function() {
				if ($(this).hasClass('icon-5')) {
					$(this).removeClass('icon-5');
					$("input[name='password']").attr('type', 'password');
				} else {
					$(this).addClass('icon-5');
					$("input[name='password']").attr('type', 'text');
				}
			});
	
			$('.icon-nocheck').on('click', function() {
				if ($(this).hasClass('icon-check')) {
					$(this).removeClass('icon-check');
				} else {
					$(this).addClass('icon-check');
				}
			});
	
			// 进行登录操作
			form.on('submit(login)', function(data) {
				data = data.field;
				if (data.title == '') {
					layer.msg('用户名不能为空');
					return false;
				}
				if (data.password == '') {
					layer.msg('密码不能为空');
					return false;
				}
	
				//var index = top.layer.msg('正在验证身份，请稍候',{icon: 16,time:false,shade:0.8});
				$.ajax({
					url : "<%=basePath%>/User?action=login",
					type : "post",
					data : {
						
						"action" : "login",
						"name" : data.title,
						"pwd" : data.password
					},
					dataType : "text",
					success : function(user) {
						var result = eval("(" + user + ")");
						if (1 == result.status) {
							layer.msg(data.message);
							setTimeout(function() {
								//top.layer.close(index);
	
								window.location = 'http://localhost:8080/yuangong/lan.jsp';
							}, 1000);
						} else {
							//layer.msg(data.message);
						}
					}
				})
				return false;
			});
		});
	</script>

</body>

</html>
