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
	<div class="search-panel">
		<div class="search-div">
			<input size="10" placeholder="关键字">
			<button name="search" class="btn btn-default btn-sm">search</button> 
		</div>
	</div>
	<div class="select-panel">
		<ol class="breadcrumb">
			<c:if test="${questionSo.status==null}">
				<li class="active">全部</li>
				<li><a name="wait-btn" href="javascript:;">待解决</a></li>
				<li><a name="solved-btn" href="javascript:;">已解决</a></li>
			</c:if>
			<c:if test="${questionSo.status==10}">
				<li><a name="all-btn" href="javascript:;">全部</a></li>
				<li class="active">待解决</li>
				<li><a name="solved-btn" href="javascript:;">已解决</a></li>
			</c:if>
			<c:if test="${questionSo.status==20}">
				<li><a name="all-btn" href="javascript:;">全部</a></li>
				<li><a name="wait-btn" href="javascript:;">待解决</a></li>
				<li class="active">已解决</li>
			</c:if>
		</ol>
		<c:if test="${s_tag!=null}">
			<div  class="select-item">
				<a name="tag-btn" class="tag" href="javascript:;"><span
					class="glyphicon glyphicon-tag"></span>${s_tag.name}<span
					class="glyphicon glyphicon-remove remove"></span></a>
			</div>
		</c:if>
		<c:if test="${questionSo.keywords!=null && questionSo.keywords!=''}">
			<div class="select-item">
				<a name="keywords-btn" class="tag" href="javascript:;"><span
					class="glyphicon glyphicon-search"></span>${questionSo.keywords}<span
					class="glyphicon glyphicon-remove remove"></span></a>
			</div>
		</c:if>
		<div class="select-item-right">
			<div class="btn-group">
				<a name="time-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${questionSo.order==null||questionSo.order==0}">active</c:if>">时间</a>
				<a name="hot-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${questionSo.order==1}">active</c:if>">热度</a>
			</div>
			
			<div class="btn-group">
				<a name="day-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${questionSo.timeFrame==10}">active</c:if>">本日</a>
				<a name="week-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${questionSo.timeFrame==20}">active</c:if>">本周</a>
				<a name="month-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${questionSo.timeFrame==30}">active</c:if>">本月</a>
			</div>
		</div>
	</div>

	<div class="main">
	<c:if test="${pageInfo.getList().size()==0}"><hr/>找不到相关问题</c:if>
	<c:forEach items="${pageInfo.getList()}" var="question">
		<div class="question-list">
				<div class="attr">
					浏览
					<div class="number">
						<c:if test="${question.views<=999}">${question.views}</c:if>
						<c:if test="${question.views>999}">999+</c:if>
					</div>
				</div>
				<div class="attr" <c:if test="${question.status==20}">style="background-color:#d4cece;"</c:if>>
					回答
					<div class="number">
						<div class="number">
							<c:if test="${question.answers<=999}">${question.answers}</c:if>
							<c:if test="${question.answers>999}">999+</c:if>
						</div>
					</div>
				</div>
				<div class="info">
				<div class="title"><a href="/q/${question.id}">${question.title}</a></div>
				<div class="status"><c:if test="${question.status==20}"><span class="label label-default">已解决</span></c:if></div>
					<div class="tag">
					<c:set var="key" value="${question.id.toString()}"/>
					<c:forEach items="${tagsMap[key]}" var="tag">
						<a data="${tag.index}" href="javascript:;">${tag.name}</a>
						</c:forEach>
					</div>
					<div class="message">
					<div class="username">
						<a href="/u/${question.createBy}">${question.name}</a>
					</div>
					<div>|</div>
					<div class="createtime">${format.getTime(question.createTime)}</div>
				</div>
			</div>
		</div>
		</c:forEach>
		<div id="paginator" style="width: 30%; margin: 10px auto;"></div>
	</div>

	<form id="search_form" class="form-inline" method="get" action="/q">
		<input name="keywords" type="hidden" value="${questionSo.keywords}" />
		<input name="tagIndex" type="hidden" value="${questionSo.tagIndex}" />
		<input name="timeFrame" type="hidden" value="${questionSo.timeFrame}" />
		<input name="status" type="hidden" value="${questionSo.status}" />
		<input name="order" type="hidden" value="${questionSo.order}" />
		<input name="pn" type="hidden" value="${pageInfo.getPageNum()}" />
	</form>
	
	<script type="text/javascript">
		$(".tag").find("a").bind("click",function(){
			var tagIndex = $(this).attr("data");
			$("input[name='tagIndex']").val(tagIndex);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='all-btn']").bind("click",function(){
			$("input[name='status']").val("");
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='wait-btn']").bind("click",function(){
			$("input[name='status']").val(10);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='solved-btn']").bind("click",function(){
			$("input[name='status']").val(20);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='time-btn']").bind("click",function(){
			$("input[name='order']").val(0);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='hot-btn']").bind("click",function(){
			$("input[name='order']").val(1);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='day-btn']").bind("click",function(){
			$("input[name='timeFrame']").val(10);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='week-btn']").bind("click",function(){
			$("input[name='timeFrame']").val(20);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='month-btn']").bind("click",function(){
			$("input[name='timeFrame']").val(30);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("button[name='search']").bind("click",function(){
			$("input[name='timeFrame']").val("");
			$("input[name='tagIndex']").val("");
			$("input[name='status']").val("");
			$("input[name='order']").val("0");
			
			var keywords = $(this).parent("div").children("input").val();
			$("input[name='keywords']").val(keywords);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='tag-btn']").bind("click",function(){
			$("input[name='tagIndex']").val("");
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='keywords-btn']").bind("click",function(){
			$("input[name='keywords']").val("");
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
	</script>
</body>

</html>