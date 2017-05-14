<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的问题</title>
<%@ include file="../source.jsp"%>
<script src="/js/commons/paginator.js"></script>
</head>

<body>
	<input id="totalPages" type="hidden" value="${pageInfo.getPages()}" />
	<input id="currentPn" type="hidden" value="${pageInfo.getPageNum()}" />

	<div class="table-responsive"
		style="width: 700px; margin: 20px 0 0 300px;">
		<table class="table table-hover">
			<thead>
				<tr>
					<td>回答数</td>
					<td>标题</td>
					<td>创建时间</td>
					<td>更新时间</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageInfo.getList()}" var="question"
					varStatus="vs">
					<tr>
						<td>${question.answers}</td>
						<td><a href="/question?q=${question.id}"><c:out value="${question.title}" escapeXml="true"></c:out></a></td>
						<td>${format.getTime(question.createTime)}</td>
						<td>${format.getTime(question.updateTime)}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div id="paginator" style="width: 23%; margin: 10px auto;"></div>

	</div>
	
	<form id="search_form" class="form-inline" method="get" action="/question/list">
		<input name="createBy" type="hidden" value="${so.createBy}" />
		<input name="pn" type="hidden" value="${pageInfo.getPageNum()}" />
	</form>
</body>

</html>