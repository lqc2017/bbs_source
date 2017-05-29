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
											class="btn btn-info btn-xs" qId="${message.question.id}"
											uId="${currentUser.id}">关闭问题</button>
									</div>
								</td>
							</tr>
						</c:when>
					</c:choose>
				</c:forEach>
				<%-- <c:if test="${unreadMessages.size()>10}">
					<tr>
						<td colspan="2"><button class="btn btn-default btn-block">more...</button></td>
					</tr>
				</c:if> --%>
			</tbody>
		</table>
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
	</script>
</body>

</html>