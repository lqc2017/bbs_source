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
	<div class="panel panel-info question">
		<div class="panel-heading">
			<h3> 
				<c:out value="${question.title}" escapeXml="true"></c:out>
			</h3><c:if test="${question.status==20}"><span class="label label-default">已解决</span></c:if>
		</div>
		<div class="panel-body div-control">${question.describe}</div>
		<div class="message">
			<c:if test="${tags.size()!=0}">
				<div class="questoin-tags">
					<c:forEach items="${tags}" var="tag" varStatus="vs">
						<a class="tag" href="/question/home?tagIndex=${tag.index}">${tag.name}</a>
					</c:forEach>
				</div>
			</c:if>
			<div class="questoin-message">
				<label class="create-time">创建于：<fmt:formatDate
						value="${question.createTime}" pattern="yyyy-MM-dd" /></label><a
					class="test" href="/u/${question.createBy}">${question.name}</a>
			</div>
		</div>
	</div>
	<div class="middle">
		<label style="float: left">${answers.size()}个回答：</label>
		<c:if test="${answers.size()!=0}">
		<div class="btn-group">
			<a href="/question?q=${question.id}&order=0" class="btn btn-default btn-xs <c:if test="${order==null||order.order==0}">active</c:if>">时间</a>
			<a href="/question?q=${question.id}&order=10" class="btn btn-default btn-xs <c:if test="${order==10}">active</c:if>">有用</a>
		</div>
		</c:if>
		<c:if test="${question.status==10}">
			<c:if test="${user.id==question.createBy}">
				<a name="solved-btn" href="javascipt:;" style="float: right"
					class="btn btn-sm btn-success">问题解决</a>
			</c:if>
			<c:if test="${user.id==null||user.id!=question.createBy}">
				<a href="#input" style="float: right" class="btn btn-sm btn-success">填写答案</a>
			</c:if>
		</c:if>
	</div>
	<div class="panel panel-default answers">
		<c:forEach items="${answers}" var="answer" varStatus="vs">
			<div>
				<div class="panel-body div-control content">${answer.content}</div>
				<div class="answer-message">
					<a href="/u/${answer.createBy}">${answer.name}</a> <label class="create-time"><fmt:formatDate
							value="${answer.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
					<label class="helpful-block">
						<span class="glyphicon  glyphicon-chevron-left default <c:if test="${helpEnable.get(vs.count-1)}">helpless</c:if>"
						a="${answer.id}" value="0" data-toggle="tooltip"
						data-placement="left" title="<c:if test="${helpEnable.get(vs.count-1)}">毫无作用</c:if><c:if test="${!helpEnable.get(vs.count-1)}">您已评价！</c:if>"></span>
						<span name="helpful">${answer.helpful}</span> 
						<span class="glyphicon glyphicon-chevron-right default <c:if test="${helpEnable.get(vs.count-1)}">helpful</c:if>"
						a="${answer.id}" value="1" data-toggle="tooltip"
						data-placement="right" title="<c:if test="${helpEnable.get(vs.count-1)}">对我有用</c:if><c:if test="${!helpEnable.get(vs.count-1)}">您已评价！</c:if>"></span>
					</label>
				</div>
			</div>
			<hr <c:if test="${vs.last}">style="border-top:0px;"</c:if>/>
		</c:forEach>
	</div>
	<c:if test="${question.status==10&&user.id!=question.createBy}">
	<div class="input" id="input">
		<form id="add_form" method="post" action="/answer">
			<input type="hidden" name="name" value="${user.nickname}"/>
			<input type="hidden" name="createBy" value="${user.id}"/>
			<textarea id="ckeditor" name="content" cols="20" rows="5"
				class="ckeditor"></textarea>
			<input type="hidden" name="questionId" value="${question.id}">
		</form>
	</div>
	<div class="bottom">
		<button  name='add-btn' style="float: right"
			class="btn btn-sm btn-success">提交</button>
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
	</c:if>

	<c:if test="${question.status==10&&user.id!=question.createBy}">
	<script src="/js/commons/editorInit.js"></script>
	</c:if>
	<script defer type="text/javascript">
		$("img").parent("p").addClass("p-control");
		$("[data-toggle='tooltip']").tooltip();
		
		
		$(".helpful").bind("mouseover",function(){
			$(this).addClass("success");
		});
		
		$(".helpful").bind("mouseout",function(){
			$(this).removeClass("success");
		});
		
		$(".helpless").bind("mouseover",function(){
			$(this).addClass("fail");
		});
		
		$(".helpless").bind("mouseout",function(){
			$(this).removeClass("fail");
		});
		
		$(".helpful,.helpless").bind("click",function(){
			var value = $(this).attr("value");
			var a = $(this).attr("a");
			var data = {"value":value,"a":a};
			
			var helpfulBtn = $(this).parent("label").children(".helpful");
			var helplessBtn = $(this).parent("label").children(".helpless");
			var helpfulSp = $(this).parent("label").children("[name='helpful']")
			
			
			$.ajax({	
				async : false,
				type : "POST",
				url : "/answer_help",
				data : $.param(data),
				datatype : 'json',
				success : function(result) {
					if (result != "fail") {
						toastr.success("评价成功！");
						
						helpfulSp.empty();
						helpfulSp.append(result);
						
						helpfulBtn.unbind("mouseout mouseover click");
						helpfulBtn.removeClass("success");
						helpfulBtn.attr("data-original-title","您已评价！")
						helplessBtn.unbind("mouseout mouseover click");
						helplessBtn.removeClass("fail");
						helplessBtn.attr("data-original-title","您已评价！")
					}else{
						toastr.error("评价失败");
					}
				},
				error : function(jqXHR,textStatus,errorThrown) {
							alert(textStatus);
				}
			});
		});
		
		
		$("button[name='add-btn']").bind("click",function(){
			$("#ckeditor").val(editor.getData());
			$.ajax({	
				async : false,
				type : "POST",
				url : "/a",
				data : $("#add_form").serialize(),
				datatype : 'json',
				success : function(result) {
					if (result == "success") {
						toastr.success("提交成功！");
						setTimeout(function(){location.reload();},1500);
					}
					if (result == "fail")
						toastr.error("提交失败");
					},
				error : function(jqXHR,textStatus,errorThrown) {
							alert(textStatus);
				}
			});
		})
		
		$("a[name='solved-btn']").bind("click",function(){
			$.ajax({	
				async : false,
				type : "get",
				url : "/question/solved?q=${question.id}&u=${user.id}",
				datatype : 'json',
				success : function(result) {
					if (result == "success") {
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