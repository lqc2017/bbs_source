<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="source.jsp"%>
<title>登录页面</title>
</head>

<body>
	<form action="/login" method="post" class="form-horizontal" style="position: fixed; bottom: 0px;">
		<c:if test="${param.error != null}">
			<div class="alert alert-danger" style="margin-bottom:5px;width: 95%; margin: 0 auto;">
				<p>用户名或密码不正确</p>
			</div>
		</c:if>
		<div class="input-group input-lg">
			<label class="input-group-addon" for="username"><span
				class="glyphicon glyphicon-user"></span></label> <input type="text"
				class="form-control" id="username" name="ssoId" placeholder="用户名"
				required>
		</div>
		<div class="input-group input-lg">
			<label class="input-group-addon" for="password"><span
				class="glyphicon glyphicon-lock"></span></label> <input type="password"
				class="form-control" id="password" name="password" placeholder="密码"
				required>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<div class="form-actions" style="width: 90%; margin: 0 auto;">
			<input type="submit" class="btn btn-block btn-primary" value="登陆">
		</div>
	</form>
</body>

</html>