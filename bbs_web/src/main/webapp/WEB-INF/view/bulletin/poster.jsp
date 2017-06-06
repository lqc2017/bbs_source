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
	<div align="center">
		<div class="content" id="test" style="width: 800px">
			<div class="panel panel-default">
				<div class="panel-heading" align="left">${post.title}
					<c:if test="${post.status==1}"><span class="label label-default">已结帖</span></c:if>
					<c:if test="${post.postUser==user.id&&post.status==0}"><button p="${post.id}" name="finish-btn" type="button" class="btn btn-default">结帖</button></c:if>
					<br><br>
					<c:if test="${tags.size()!=0}">
					<div class="questoin-tags">
						<c:forEach items="${tags}" var="tag" varStatus="vs">
							<a class="tag" href="#">${tag.name}</a>
						</c:forEach>
					</div>
					</c:if>
				</div>
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
													src="${postuser.protraitUrl}"></td>
											</tr>
											<tr>
												<td  height="60px" align="center"><a href="/u/${post.postUser}" type="button"
														class="btn btn-info">关注</a></td>
											</tr>
											<tr>
												<td align="center"><a href="/u/${post.postUser}">${post.name}</a></td>
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
										回复数：${post.replys} 浏览数：${post.views}
										<div class="btn-group" style="float: right;">
											<button disabled="disabled" type="button" class="btn btn-default">收藏本帖</button>
											<a href="#input" type="button" class="btn btn-default">回复</a>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<c:set var="floor" scope="session" value="${0}"/>
					<c:forEach items="${replys}" var="replys" varStatus="vs">
					<c:set var="floor" scope="session" value="${floor+1}"/>
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td></td>
								<td><div style="display: inline">
										回复时间：${replys.replyTime}
										<div style="float: right;">${floor}楼    <c:if test="${post.acceptId==replys.id}"><span class="label label-success">被采纳</span></c:if></div>
									</div></td>
							</tr>
							<tr>
								<td align="center" style="width: 150px">
									<table style="border: 0;">
										<tbody>
											<tr>
												<td align="center"><img
													src="${replyusers[vs.count-1].protraitUrl}"></td>
											</tr>
											<tr>
												<td  height="60px" align="center"><a href="/u/${replys.replyUser}" type="button"
														class="btn btn-info">关注</a></td>
											</tr>
											<tr>
												<td align="center"><a href="/u/${replys.replyUser}">${replys.name}</a></td>
											</tr>
										</tbody>
									</table>
								</td>
								<td><c:if test="${replys.citeId!=null}"><p class="text-primary">引用${replys.citeFlorr}的回复：<br>${replys.citeContent}<br></p></c:if>${replys.content}</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div style="display: inline">
										<c:if test="${helpEnable.get(vs.count-1)==0}">
										<div class="btn-group" style="float: right;">
											<c:if test="${post.postUser==user.id&&post.acceptId==0}">
												<button r="${replys.id}" p="${post.id}" name="accept-btn" type="button" class="btn btn-default">采纳</button>
											</c:if>
											<c:if test="${post.postUser==user.id&&post.acceptId==replys.id}">
												<button disabled="disabled" type="button" class="btn btn-default">已采纳</button>
											</c:if>
											<button r="${replys.id}" value="1" name="helpful-btn" type="button" class="btn btn-default">赞一下</button>
											<button r="${replys.id}" value="2" name="helpless-btn" type="button" class="btn btn-default">踩一下</button>
										</div>
										</c:if>
										<c:if test="${helpEnable.get(vs.count-1)==1}">
										<div class="btn-group" style="float: right;">
											<c:if test="${post.postUser==user.id&&post.acceptId==0}">
											<button r="${replys.id}" p="${post.id}" name="accept-btn" type="button" class="btn btn-default">采纳</button>
											</c:if>
											<c:if test="${post.postUser==user.id&&post.acceptId==replys.id}">
												<button disabled="disabled" type="button" class="btn btn-default">已采纳</button>
											</c:if>
											<button disabled="disabled" type="button" class="btn btn-default">已赞</button>
										</div>
										</c:if>
										<c:if test="${helpEnable.get(vs.count-1)==2}">
										<div class="btn-group" style="float: right;">
											<c:if test="${post.postUser==user.id&&post.acceptId==0}">
												<button r="${replys.id}" p="${post.id}" name="accept-btn" type="button" class="btn btn-default">采纳</button>
											</c:if>
											<c:if test="${post.postUser==user.id&&post.acceptId==replys.id}">
												<button disabled="disabled" type="button" class="btn btn-default">已采纳</button>
											</c:if>
											<button disabled="disabled" type="button" class="btn btn-default">已踩</button>
										</div>
										</c:if>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${post.status==0}">
	<div align="center">
		<div style="width: 800px;" id="input">
			<form id="add_form" class="form-horizontal" action="/bulletin/reply" method="post">
			<c:if test="${post.replys!=0}">
			<div align="left">
				<div class="form-group">
					<label style="width: 225px;" class="col-sm-2 control-label">回复楼层（回复楼层范围1-${post.replys}） </label><div class="col-sm-2"><input class="form-control" placeholder="NULL" name="citeId" data-toggle="collapse" data-parent="#accordion"/></div>
				</div>
			</div>
			</c:if>
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
	</c:if>
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
	
	<script src="/js/commons/registerFormValidateInit.js"></script>
	<script src="/js/commons/editorInit.js"></script>
	<script defer type="text/javascript">
	    
		$('#add_form').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        trigger:'change',
	        fields: {
	            'citeId': {
	                validators: {
	                    callback: {
	                        message: '输入不合法，请输入所给范围内的整数数字',
	                        callback: function(value, validator) {
	                        	if(value==""||(/^\d+$/.test(value)&&parseInt(value,10)>0&&parseInt(value,10)<=${post.replys}))
	                        		return true
	                        	return false;
	                        }
	                    }
	                }
	            }
	        }
	    })
    
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
		
		$("button[name='helpless-btn'],button[name='helpful-btn']").bind("click",function(){
			var value = $(this).attr("value");
			var r = $(this).attr("r");
			var data = {"value":value,"r":r};
			
			$.ajax({	
				async : false,
				type : "POST",
				url : "/bulletin/reply_help",
				data : $.param(data),
				datatype : 'json',
				success : function(result) {
					if (result != "fail") {
						toastr.success("评价成功！");
						setTimeout(function(){location.reload();},1500);
					}else{
						toastr.error("评价失败");
					}
				},
				error : function(jqXHR,textStatus,errorThrown) {
							alert(textStatus);
				}
			});
		});
		
		$("button[name='accept-btn']").bind("click",function(){
			var r = $(this).attr("r");
			var p = $(this).attr("p");
			var data = {"r":r,"p":p};
			
			$.ajax({	
				async : false,
				type : "POST",
				url : "/bulletin/accept",
				data : $.param(data),
				datatype : 'json',
				success : function(result) {
					if (result != "fail") {
						toastr.success("采纳成功！");
						setTimeout(function(){location.reload();},1500);
					}else{
						toastr.error("采纳失败");
					}
				},
				error : function(jqXHR,textStatus,errorThrown) {
							alert(textStatus);
				}
			});
		});
		
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
		
		$("button[name='helpless-btn'],button[name='helpful-btn'],input[name='citeId']").bind("focus",function(){
			if ($("a.sign").text() != "") {
					toastr.info("请登录");
					$(".sign").trigger("click");
				}
		})
	</script>
</body>
</html>