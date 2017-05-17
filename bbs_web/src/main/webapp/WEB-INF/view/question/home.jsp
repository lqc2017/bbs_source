<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>嘿嘿</title>
<link href="/css/question-home.css" rel="stylesheet">
<%@ include file="../source.jsp"%>
<script src="/js/commons/paginator.js"></script>
</head>

<body>
	<input id="totalPages" type="hidden" value="${pageInfo.getPages()}" />
	<input id="currentPn" type="hidden" value="${pageInfo.getPageNum()}" />
	<div class="main">
	<c:forEach items="${pageInfo.getList()}" var="question">
		<div class="question-list">
				<div class="attr">
					浏览
					<div class="number">
						<c:if test="${question.views<=999}">${question.views}</c:if>
						<c:if test="${question.views>999}">999+</c:if>
					</div>
				</div>
				<div class="attr">
					回答
					<div class="number">
						<div class="number">
							<c:if test="${question.answers<=999}">${question.answers}</c:if>
							<c:if test="${question.answers>999}">999+</c:if>
						</div>
					</div>
				</div>
				<div class="info">
				<div class="title"><a href="/question?q=${question.id}">${question.title}</a></div>
					<div class="tag">
					<c:set var="key" value="${question.id.toString()}"/>
					<c:forEach items="${tagsMap[key]}" var="tag">
						<a data="${tag.index}" href="javascript:;">${tag.name}</a>
						</c:forEach>
					</div>
					<div class="message">
					<div class="username">
						<a href="">usernasssss</a>
					</div>
					<div>|</div>
					<div class="createtime">${format.getTime(question.createTime)}</div>
				</div>
			</div>
		</div>
		</c:forEach>
		<div id="paginator" style="width: 23%; margin: 10px auto;"></div>
	</div>

	<form id="search_form" class="form-inline" method="get" action="/question/home">
		<input name="tagIndex" type="hidden" value="${so.tagIndex}" />
		<input name="pn" type="hidden" value="${pageInfo.getPageNum()}" />
	</form>
	
	<script type="text/javascript">
		$(".tag").find("a").bind("click",function(){
			var tagIndex = $(this).attr("data");
			$("input[name='tagIndex']").val(tagIndex);
			$("#search_form").submit();
		})
	</script>
</body>

</html>