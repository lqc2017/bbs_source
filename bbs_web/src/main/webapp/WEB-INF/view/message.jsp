<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息</title>
<%@ include file="source.jsp"%>
</head>

<body>
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid nav-container">
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
				<li class=""><a href="/bulletin/home">论坛</a></li>
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
							<li><a href="/u/${currentUser.id}?active=20">消息 <c:if test="${messageCnt!=0}"><span class="badge">新</span></c:if></a></li>
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
	
	<div class="table-responsive" style="width: 1024px; margin: 20px auto">
		<table class="table table-hover table-condensed" style="table-layout: fixed;">
			<caption>问题动态：${unreadMessages.size()}</caption>
			<tbody>
				<tr>
					<td width="10%">时间</td>
					<td width="15%">回复人</td>
					<td width="60%">问题</td>
					<td width="15%">操作</td>
				</tr>
			</tbody>
			<tbody>
				<c:if test="${unreadMessages.size()==0 }"><tr><td colspan="4">暂无</td></tr></c:if>
				<c:forEach items="${unreadMessages}" var="message">
					<c:choose>
						<c:when test="${message.type==10}">
							<tr>
								<td style="width: 10%">${format.getTime(message.createTime)}</td>
								<td><a href="/u/${message.user.id}">${message.user.nickname}</a></td>
								<td style="width: 90%"><p class="p-control" style="font-size: 14px;">
										${message.question.title}
								</p></td>
								<td>
								<div class="btn-toolbar">
										<button type="button" class="btn btn-default btn-xs"
											onclick="javascript:location.href='/q/${message.question.id}'">查看</button>
										<button name="solved-btn" type="button"
											class="btn btn-danger btn-xs" qId="${message.question.id}"
											uId="${currentUser.id}">关闭问题</button>
									</div>
								</td>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</tbody>
		</table>
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
	</script>
</body>

</html>