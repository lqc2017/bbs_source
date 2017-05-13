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
<script src="/ckeditor/ckeditor.js"></script>
<script src="/js/commons/paginator.js"></script>
</head>

<body>
	<input id="totalPages" type="hidden" value="${pageInfo.getPages()}" />
	<input id="currentPn" type="hidden" value="${pageInfo.getPageNum()}" />
	
	<div class="table-responsive" style="width: 95%; margin: 30px auto;">
		<table class="table table-hover">
			<tbody>
				<c:forEach items="${pageInfo.getList()}" var="question"
					varStatus="vs">
					<tr>
						<td></td>
						<td><a href="/question?questionId=${question.id}">${question.title}</a></td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div id="paginator" style="width: 23%; margin: 10px auto;"></div>

	</div>
</body>

</html>