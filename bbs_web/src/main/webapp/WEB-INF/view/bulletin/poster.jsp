<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title><c:out value="${question.title}" escapeXml="true"></c:out></title>
<link href="/css/question.css" rel="stylesheet">
<%@ include file="../source.jsp"%>
<script src="/ckeditor/ckeditor.js"></script>
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
							src="https://avatars0.githubusercontent.com/u/26128332?v=3&s=460"
							height="20" width="20"><b class="caret"></b> </a>
						<ul class="dropdown-menu">
							<li><a href="#">个人信息</a></li>
							<li><a href="#">EJB</a></li>
							<li class="divider"></li>
							<li><a href="/logout">登出</a></li>
							<li class="divider"></li>
							<li><a href="#">设置</a></li>
						</ul></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>
	<div align="center">
		<div class="content" id="test" style="width: 800px">
			<div class="panel panel-default">
				<div class="panel-heading" align="left">${post.title}</div>
				<div class="panel-body">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td></td>
								<td><div style="display: inline">
										发表时间：${post.postTime}
										<div style="float: right;">楼主</div>
									</div></td>
							</tr>
							<tr>
								<td align="center" style="width: 150px">
									<table style="border: 0;">
										<tbody>
											<tr>
												<td align="center"><img
													src="https://avatars0.githubusercontent.com/u/26128332?v=3&s=460"
													height="100" width="100" class="img-rounded img-responsive"></td>
											</tr>
											<tr>
												<td  height="60px" align="center"><button type="button"
														class="btn btn-info">关注</button></td>
											</tr>
											<tr>
												<td align="center"><a href="#">刘言石</a></td>
											</tr>
										</tbody>
									</table>
								</td>
								<td>${post.content}</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div style="display: inline">
										回复数：${post.replys}
										<div class="btn-group" style="float: right;">
											<button type="button" class="btn btn-default">赞一下</button>
											<button type="button" class="btn btn-default">踩一下</button>
											<button type="button" class="btn btn-default">引用</button>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div align="center">
		<div style="width: 800px;">
			<form id="add_form" class="form-horizontal" action="/bulletin/reply" method="post">
				<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
				<input type="hidden" name="replyUser" value="${user.id}"/>
				<input type="hidden" name="name" value="${user.nickname}"/>
				<input type="hidden" name="postId" value="${post.id}"/>
				<div style="width: 800px;">
					<textarea id="ckeditor" name="content" cols="20" rows="2"
						class="ckeditor"></textarea>
				</div>
			</form>	
		</div>
	</div>

	<div style="width: 800px;hight：auto; margin: 10px 200px;">
		<div style="width: 680px;hight：auto; margin: 0 0 0 200px;">
			<button name="add-btn" style="float:right" class="btn btn-success">回复</button>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">登陆/注册</h4>
				</div>
				<div class="modal-body">
					<ul id="myTab1" class="nav nav-tabs">
						<li class="active"><a href="#information1" data-toggle="tab">登陆</a></li>
						<li><a href="#BBS1" data-toggle="tab">注册</a></li>
					</ul>
					<div id="myTabContent1" class="tab-content">
						<div class="tab-pane fade active in" id="information1">
							<div style="margin: 10px 30px 20px 30px">
								<form action="/login" method="post" class="form-horizontal">
									<div class="input-group input-sm">
										<label class="input-group-addon" for="username"><span
											class="glyphicon glyphicon-user"></span></label> <input type="text"
											class="form-control" id="username" name="ssoId"
											placeholder="用户名" required>
									</div>
									<div class="input-group input-sm">
										<label class="input-group-addon" for="password"><span
											class="glyphicon glyphicon-lock"></span></label> <input
											type="password" class="form-control" id="password"
											name="password" placeholder="密码" required>
									</div>
									<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

									<div class="form-actions" style="width: 95%; margin: 0 auto;">
										<input type="submit"
											class="btn btn-block btn-primary" value="登陆">
									</div>
								</form>
							</div>
						</div>
						<div class="tab-pane fade" id="BBS1">
							<div style="margin: 10px 30px 20px 30px">
								<form action="/signUp" method="post" class="form-horizontal">
										<label class="label-control" for="username">用户名</label> <input type="text"
											class="form-control" name="username"
											placeholder="用户名" required>
										<label class="label-control" for="password">密码</label> <input
											type="password" class="form-control"
											name="password" placeholder="密码" required>
											<input type="submit"
											class="btn btn-block btn-success form-control" style="margin:15px 0 0 0;" value="注册">
											</form>
									</div>
									<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

	<script src="/js/commons/editorInit.js"></script>
	<script defer type="text/javascript">

		$("button[name='add-btn']").bind("click",function(){
			$("#ckeditor").val(editor.getData());
			$.ajax({	
				async : false,
				type : "POST",
				url : "/bulletin/reply",
				data : $("#add_form").serialize(),
				datatype : 'json',
				success : function(result) {
					if (result == "success") {
						toastr.success("回复成功！");
						setTimeout(function(){location.reload();},1500);
					}
					if (result == "fail")
						toastr.error("回复失败");
					},
				error : function(jqXHR,textStatus,errorThrown) {
							alert(textStatus);
				}
			});
		})
	</script>
</body>
</html>