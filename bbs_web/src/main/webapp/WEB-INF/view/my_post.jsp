<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的帖子列表</title>
<%@ include file="source.jsp"%>
<script src="/js/commons/paginator.js"></script>
</head>

<body>
	<!-- 导航头 -->
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid nav-container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
				<span class="sr-only">切换导航</span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">codeground</a>
		</div>
		<div class="collapse navbar-collapse" id="example-navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="/bulletin/home">论坛</a></li>
				<li><a href="/q">问答</a></li>
			</ul>
			<ul class="nav navbar-nav float-right">
				<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication==null }">
					<li><a class="sign" href="javascript:;" data-toggle="modal" data-target="#signModal">登陆/注册</a></li>
				</c:if>
				
				<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null }">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img src="${currentUser.protraitUrl}" height="20" width="20"><b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><p class="p-cur-user">当前用户：${currentUser.nickname}</p></li>
							<li class="divider"></li>
							
							<li><a href="/u/${currentUser.id}?active=10">个人信息</a></li>
							<li><a href="/u/post">我的帖子</a></li>
							<li><a href="/myq">我的问题</a></li>
							<li class="divider"></li>
							
							<li><a href="/u/${currentUser.id}?active=20">消息
							<c:if test="${messageCnt!=0}">
								<span class="badge">新</span>
							</c:if></a></li>
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
	<!-- 导航尾 -->
	
	<input id="totalPages" type="hidden" value="${pageInfo.getPages()}" />
	<input id="currentPn" type="hidden" value="${pageInfo.getPageNum()}" />
	
	<div style="margin-top: 0;margin-bottom: 15px;margin-left:80px;margin-right:80px;">
		<form id="search_form" class="form-inline" method="get">
			<div class="form-group">
				<label for="name">关键字:</label> <input type="text" name="keywords"
					class="form-control" value="${so.keywords}">
			</div>

			<div class="form-group">
				<label for="status">状态:</label> <select name="status" id="status"
					class="form-control">
					<option value="">无</option>
					<option value="0" <c:if test="${so.status==0}">selected</c:if>>未结贴</option>
					<option value="1" <c:if test="${so.status==1}">selected</c:if>>已结帖</option>
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

	<div class="table-responsive" style="margin: 30px 80px;">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="13%">发布时间</th>
					<th width="29%">标题名</th>
					<th width="13%">最后更新时间</th>
					<th width="8%">浏览数</th>
					<th width="8%">回复数</th>
					<th width="5%">状态</th>
					<th width="12%">打赏情况</th>
					<th width="12%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageInfo.getList()}" var="post" varStatus="vs">
					<tr>
						<td><fmt:formatDate value="${post.postTime}"
										pattern="yyyy-MM-dd  HH:mm" /></td>
						<td><p class="p-control" style="font-size: 14px;">${post.title}</p></td>
						<td><fmt:formatDate value="${post.updateTime}"
										pattern="yyyy-MM-dd  HH:mm" /></td>
						<td>${post.views}</td>
						<td>${post.replys}</td>
						<td><c:choose>
								<c:when test="${post.status==0}">未结帖</c:when>
								<c:when test="${post.status==1}">已结帖</c:when>
							</c:choose></td>
						<c:if test="${post.acceptId!=0}">
						<td>${post.rewards}  <span class="label label-success">已打赏</span></td>
						</c:if>
						<c:if test="${post.acceptId==0}">
						<td>${post.rewards}</td>
						</c:if>
						<td><div class="btn-toolbar">
								<button type="button" class="btn btn-default btn-xs"
									onclick="javascript:location.href='/bulletin/home/${post.id}'">查看</button>
								<c:choose>
									<c:when test="${post.status==0}"><button name="finish-btn" type="button"
									class="btn btn-danger btn-xs" p="${post.id}">结帖</button>
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
	
	<!-- 注册登录模态框头 -->
	<div class="modal fade" id="signModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modalLabel">登陆/注册</h4>
				</div>
				<div class="modal-body">
					<ul id="myTab1" class="nav nav-tabs">
						<li class="active"><a href="#signIn" data-toggle="tab">登陆</a></li>
						<li><a href="#signUp" data-toggle="tab">注册</a></li>
					</ul>
					<div id="myTabContent1" class="tab-content">
						<div class="tab-pane fade active in" id="signIn">
							<div style="height: 235px; padding: 0 30px 30px 30px;">
								<iframe frameborder="no" style="scrolling: auto; width: 100%; height: 100%;" src="/loginPage"></iframe>
							</div>
						</div>
						<div class="tab-pane fade" id="signUp">
							<div style="margin: 10px 30px 20px 30px">
								<form name="register_form" action="/signUp" method="post"
									class="form-horizontal">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<div class='form-group'>
										<label class="label-control">用户名</label> <input type="text" class="form-control" name="username">
									</div>
									<div class='form-group'>
										<label class="label-control">密码</label> <input type="password" class="form-control" name="password">
									</div>
									<div class='form-group'>
										<label class="label-control">确认密码</label> 
										<input type="password" class="form-control" name="confirm" placeholder="请再次输入密码">
									</div>
									<div class='form-group'>
										<label class="label-control">昵称</label> 
										<input type="text" class="form-control" name="nickname">
									</div>
									<div class='form-group'>
										<label class="label-control">性别</label> 
										<label class="checkbox-inline"><input type="radio" name="sex" value="0" checked>男</label> 
										<label class="checkbox-inline"><input type="radio" name="sex" value="1">女</label>
									</div>
									<div class='form-group'>
										<input type="submit" class="btn btn-block btn-success form-control" value="注册">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 注册登录模态框尾 -->

	<script defer type="text/javascript">
	$("button[name='finish-btn']").bind("click",function(){
		if(confirm("结帖将使得该帖子不再具有回复功能，确认结帖吗？")){
			var p = $(this).attr("p");
			var data = {"p":p};
			$.ajax({	
				async : false,
				type : "POST",
				url : "/bulletin/finish",
				data : $.param(data),
				datatype : 'json',
				success : function(result) {
					if (result != "fail") {
						toastr.success("结帖成功！");
						setTimeout(function(){location.reload();},1500);
					}else{
						toastr.error("结帖失败");
					}
				},
				error : function(jqXHR,textStatus,errorThrown) {
							alert(textStatus);
				}
			});
		}
	});
	
		function select() {
			var form = $("#search_form");
			form.attr("action", "/u/post");
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