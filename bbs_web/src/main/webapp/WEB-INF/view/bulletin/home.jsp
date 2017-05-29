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
	<div style="display: inline">
		<div style="float: left; width: 900px">
		<table class="table table-striped">
		<caption><div align="center">帖子（按照发布时间时间排序）</div></caption>
			<thead>
				<tr>
					<td>帖子名称</td>
					<td>发布者</td>
					<td>发布时间</td>
					<td>最后回复时间</td>
					<td>悬赏分</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${post}">
					<tr>
						<td><a href="/bulletin/home/${post.id}">${post.title}  </a><c:if test="${post.status==1}"><span class="label label-default">已结帖</span></c:if></td>
						<td><a href="#">${post.name}</a></td>
						<td>${post.postTime}</td>
						<td>${post.updateTime}</td>
						<c:if test="${post.acceptId!=0}">
						<td>${post.rewards}  <span class="label label-success">已打赏</span></td>
						</c:if>
						<c:if test="${post.acceptId==0}">
						<td>${post.rewards}</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		<div style="float: right; width: 300px">
		<table class="table table-condensed">
		<caption><div align="center">排行榜（只显示前十名）</div></caption>
			<thead>
				<tr>
					<td><div align="center">用户昵称</div></td>
					<td><div align="center">用户积分</div></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rankuser" items="${rankuser}" varStatus="vs" >
				<c:if test="${vs.count<=10}">
					<tr>
						<td><div align="center"><a href="#">${rankuser.nickname}</a></div></td>
						<td><div align="center">${rankuser.score}</div></td>
					</tr>
				</c:if>
				</c:forEach>
			</tbody>
		</table>
		</div>
	</div>
</body>

</html>