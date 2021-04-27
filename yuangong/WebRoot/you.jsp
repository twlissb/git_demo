<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.entity.Menu"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 管理系统大布局 - Layui</title>
<link rel="stylesheet" href="layui-v2.5.4/layui/css/layui.css">
<link rel="stylesheet" href="js/lay-module/layui_ext/dtree/dtree.css">
<link rel="stylesheet"
	href="js/lay-module/layui_ext/dtree/font/dtreefont.css">
<link rel="stylesheet" href="lib/layui-v2.5.5/css/layui.css" media="all">
<script src="scripts/jquery-1.12.4.js"></script>
<script src="layui-v2.5.4/layui/layui.js"></script>
<link rel="stylesheet"
	href="layui-v2.5.4/layui/css/modules/laydate/default/laydate.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div class="layuimini-container">
				<div class="layuimini-main">
					<blockquote class="layui-elem-quote quoteBox">
						<div class="layui-inline">
							<div class="layui-input-inline">
								<input type="text" name="uname" id="likename"
									class="layui-input" placeholder="请输入查找的登录名" />
							</div>
						</div>
						<div class="layui-inline">
							<button type="button" class="layui-btn" lay-filter="doSubmit"
								id="doSubmit">
								<i class="layui-icon layui-icon-search layui-icon-normal"></i>搜索
							</button>
						</div>
						<input type="text" id="loginName" value="${user.id}"
							style="display:none" />
					</blockquote>

					<script type="text/html" id="an">
						<c:forEach var="i" items="${list2}">
							${i.btn}
						</c:forEach>
						</script>

					<table id="newsList" lay-filter="newsList" class="layui-table"></table>

				</div>
			</div>


		</div>
	</div>
</body>
<!-- 分配权限 -->
<div style="height: 400px;overflow: auto;display: none;" id="dtree1">
	<ul id="dataTree3" class="dtree" data-id="0"></ul>
</div>
<!-- 分配角色 -->
<div style="height: 400px;overflow: auto;display: none;" id="hairRole">
	<select name="Roleid" id="RoleName" lay-filter="RoleName">
	</select>
</div>
</html>