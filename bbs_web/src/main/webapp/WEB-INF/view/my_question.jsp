<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单列表</title>
<%@ include file="source.jsp"%>
<script src="/js/commons/paginator.js"></script>
</head>

<body>
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#example-navbar-collapse">
				<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">codeground</a>
		</div>
		<div class="collapse navbar-collapse" id="example-navbar-collapse">
			<ul class="nav navbar-nav">
				<li class=""><a href="#">论坛</a></li>
				<li><a href="/q">问答</a></li>
			</ul>
			<ul class="nav navbar-nav float-right">
				<c:if
					test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication==null }">
					<li><a class="sign" href="javascript:;" data-toggle="modal"
						data-target="#myModal">登陆/注册</a></li>
				</c:if>
				<c:if
					test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null }">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><img
							src="${currentUser.protraitUrl}"
							height="20" width="20"><b class="caret"></b> </a>
						<ul class="dropdown-menu">
							<li><p class="p-cur-user">当前用户：${currentUser.nickname}</p></li>
							<li class="divider"></li>
							<li><a href="/u/${currentUser.id}?active=10">个人信息</a></li>
							<li><a href="/u/post">我的帖子</a></li>
							<li><a href="/myq">我的问题</a></li>
							<li class="divider"></li>
							<li><a href="/u/${currentUser.id}?active=20">消息 <c:if test="${messageCnt!=null}"><span class="badge">新</span></c:if></a></li>
							<li class="divider"></li>
							<li><a href="/logout">登出</a></li>
							<li class="divider"></li>
							<li><a href="/u/${currentUser.id}?active=30">设置</a></li>
						</ul></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>
	<input id="totalPages" type="hidden" value="${pageInfo.getPages()}" />
	<input id="currentPn" type="hidden" value="${pageInfo.getPageNum()}" />
	
	<div style="width: 95%; margin: 0 auto 15px auto;">
		<form id="search_form" class="form-inline" method="get">
			<div class="form-group">
				<label for="name">关键字:</label> <input type="text" name="keywords"
					class="form-control" value="${so.keywords}">
			</div>

			<div class="form-group">
				<label for="status">状态:</label> <select name="status" id="status"
					class="form-control">
					<option value="">无</option>
					<option value="10" <c:if test="${so.status==10}">selected</c:if>>未解决</option>
					<option value="20" <c:if test="${so.status==20}">selected</c:if>>已解决</option>
				</select>
			</div>
			
			<div class="form-group">
				<label for="status">排序:</label> <select name="order" id="order"
					class="form-control">
					<option value="0" <c:if test="${so.order==null||so.order==10}">selected</c:if>>创建时间</option>
					<option value="2" <c:if test="${so.order==20}">selected</c:if>>更新时间</option>
					<option value="1" <c:if test="${so.order==20}">selected</c:if>>热度</option>
				</select>
			</div>

			<div class="form-group">
				<div class="btn-toolbar">
					<a href="javascript:;" onclick="select()" class="btn btn-default">查询</a>
					<a href="javascript:;" onclick="reset()" class="btn btn-default">重置</a>
				</div>
			</div>

			<!-- 页码 -->
			<input name="pn" type="hidden" value="${pageInfo.getPageNum()}" />
		</form>
	</div>

	<div class="table-responsive" style="width: 95%; margin: 30px auto;">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="13%">发布时间</th>
					<th width="30%">标题名</th>
					<th width="12%">最后更新时间</th>
					<th width="12%">更新人</th>
					<th width="8%">浏览数</th>
					<th width="8%">回答数</th>
					<th width="5%">状态</th>
					<th width="12%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageInfo.getList()}" var="question" varStatus="vs">
					<tr>
						<td><fmt:formatDate value="${question.createTime}"
										pattern="yyyy-MM-dd  HH:mm" /></td>
						<td>${question.title}</td>
						<td><fmt:formatDate value="${question.updateTime}"
										pattern="yyyy-MM-dd  HH:mm" /></td>
						<td><a href="/u/${users[vs.index].id}">${users[vs.index].nickname}</a></td>
						<td>${question.views}</td>
						<td>${question.answers}</td>
						<td><c:choose>
								<c:when test="${question.status==10}">未解决</c:when>
								<c:when test="${question.status==20}">已解决</c:when>
							</c:choose></td>
						<td><div class="btn-toolbar">
								<button type="button" class="btn btn-default btn-xs"
									onclick="javascript:location.href='/q/${question.id}'">查看</button>
								<c:choose>
									<c:when test="${question.status==10}"><button name="solved-btn" type="button"
									class="btn btn-danger btn-xs" qId="${question.id}"
									uId="${currentUser.id}">关闭问题</button>
									</c:when>
								</c:choose>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form id="information_form" method="post">
			<input name="orderId" type="hidden"> <input name="cashierId"
				type="hidden" value="${cashierId}">
		</form>
		<div id="paginator" style="width: 23%; margin: 10px auto;"></div>

	</div>

	<script defer type="text/javascript">
	$("button[name='solved-btn']").bind("click",function(){
		var qId = $(this).attr("qId");
		var uId = $(this).attr("uId");
		$.ajax({	
			async : false,
			type : "get",
			url : "/q/solved?q="+qId+"&u="+uId,
			datatype : 'json',
			success : function(result) {
				if (result == "success") {
					toastr.success("设置成功！");
					setTimeout(function(){location.reload();},1500);
				}
				if (result == "fail")
					toastr.error("确认失败，没有权限");
				},
			error : function(jqXHR,textStatus,errorThrown) {
						alert(textStatus);
			}
		});
	})
	
		function select() {
			var form = $("#search_form");
			form.attr("action", "/myq");
			form.submit();
		}

		function reset() {
			var form = $("#search_form");
			form.find("input").val("");
			$("select").find("option").attr("selected", false);
			$("select").find("option:first").attr("selected", true);
			form.submit();
		}
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			format : 'yyyy-mm-dd'
		});
	</script>
</body>

</html>