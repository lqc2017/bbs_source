<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帖子</title>
<link href="/css/question-home.css" rel="stylesheet">
<%@ include file="../source.jsp"%>
<script src="/js/commons/paginator.js"></script>
</head>

<body>
	<div>
		<table class="table">
			<thead>
				<tr>
					<td>帖子名称</td>
					<td>发布者</td>
					<td>发布时间</td>
					<td>最后回复时间</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${post}">
					<tr>
						<td><a href="/bulletin/home/${post.id}">${post.title}</a></td>
						<td><a href="#">${post.name}</a></td>
						<td>${post.postTime}</td>
						<td>${post.updateTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>