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
<title>个人信息</title>
<link href="/css/user-home.css" rel="stylesheet">
<%@ include file="source.jsp"%>
<script src="/js/plugins/Chart.bundle.min.js"></script>
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
							<li><p class="p-cur-user">当前用户：${currentUser.nickname}</p></li>
							<li class="divider"></li>
							<li><a href="#">个人信息</a></li>
							<li><a href="#">消息 <c:if test="${messageCnt!=null}"><span class="badge">新</span></c:if></a></li>
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
	<%--
		java.util.Enumeration<String> sessEnum = request.getSession().getAttributeNames();
		while (sessEnum.hasMoreElements()) {
			String s = sessEnum.nextElement();
			out.print(s);
			out.println("==" + request.getSession().getAttribute(s));
	--%>
	<!-- <br /> -->
	<%--
		}
	--%>
	<div class="container">
		<div class="left-panel">
			<img
				src="https://avatars0.githubusercontent.com/u/26128332?v=3&s=460"
				height="230" class="img-rounded img-responsive">
			<div>
				<div class="left-block">
					关注
					<p>${followList.get(0)}
					<p>
				</div>
				<div class="right-block">
					粉丝
					<p>${followList.get(1)}
					<p>
				</div>
			</div>
			<c:if
				test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null }">
				<c:choose>
					<c:when test="${user.id==currentUser.id }">
						<a class="btn btn-default btn-block btn-op">编辑</a>
					</c:when>
					<c:otherwise>
						<c:if test="${followed}">
							<a name="unfo-btn" class="btn btn-default btn-block btn-op">取消关注</a>
						</c:if>
						<c:if test="${!followed}">
							<a name="follow-btn" class="btn btn-default btn-block btn-op">关注</a>
						</c:if>
					</c:otherwise>
				</c:choose>
			</c:if>

			<c:if
				test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication==null }">
				<a name="follow-btn" class="btn btn-default btn-block btn-op">关注</a>
			</c:if>
		</div>
		<div class="main-panel">
			<ul id="myTab" class="nav nav-tabs">
				<li class="active"><a class="spacing" href="#information"
					data-toggle="tab">个人信息</a></li>
				<li><a class="spacing" href="#BBS" data-toggle="tab">论坛</a></li>
				<li><a class="spacing" href="#AQ" data-toggle="tab">问答</a></li>
				<li><a class="spacing" href="#message" data-toggle="tab">消息</a></li>
				<li><a class="spacing" href="#setting" data-toggle="tab">设置</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade  in active" id="information">
					<div class="content">用户已设为私密</div>
				</div>
				<div class="tab-pane fade" id="BBS">
					<div style="display: inline">
						<div class="content" id="test" style="float: left; width: 450px">
							<div class="panel panel-default">
								<div class="panel-heading">
									论坛热帖
									<div align="right">
										<button type="button" class="btn btn-info btn-sm">更多</button>
									</div>
								</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<td>帖子名称</td>
												<td>发布者</td>
												<td>发布时间</td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><a href="#">python自动运维实战</a></td>
												<td><a href="#">刘言石</a></td>
												<td>2016-5-24</td>
											</tr>
											<tr>
												<td><a href="#">python自动运维实战</a></td>
												<td><a href="#">张绍俊</a></td>
												<td>2016-5-24</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">论坛达人</div>
								<div class="panel-body">
									<table style="border: 0;">
										<tbody>
											<tr>
												<td style="width: 60px"><img
													src="https://avatars0.githubusercontent.com/u/26128332?v=3&s=460"
													height="50" width="50" class="img-rounded img-responsive">
												</td>
												<td style="width: 50px">
													<table style="border: 0;">
														<tbody>
															<tr>
																<td align="center"><a href="#">刘言石</a></td>
															</tr>
															<tr>
																<td align="center"><button type="button"
																		class="btn btn-info btn-xs">关注</button></td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="panel-footer">
									<div align="right">
										<button type="button" class="btn btn-info btn-sm">换一批</button>
									</div>
								</div>
							</div>
						</div>
						<div class="content" id="test" style="float: left; width: 270px">
							<div class="panel panel-default">
								<div class="panel-heading">公告栏</div>
								<div class="panel-body">
									<table class="table">
										<thead>
											<tr>
												<td><a href="#">python编程比赛报名</a></td>
											</tr>
											<tr>
												<td><a href="#">ACM算法比赛报名</a></td>
											</tr>
										</thead>
									</table>
								</div>
							</div>
							<div align="right">
								<button type="button" class="btn btn-success btn-block">我要发帖</button>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="AQ">
					<div class="content" id="test">
						<div class="panel panel-default">
							<div class="panel-heading">数据</div>
							<div class="panel-body">

								<div class="data-chart">
									<canvas id="major" width="400" height="200"></canvas>
									<div class="text">擅长领域</div>
								</div>
								<div class="data-chart">
									<canvas id="participate" width="400" height="200"></canvas>
								</div>
							</div>
						</div>

						<div class="panel panel-default" style="width: 70%;">
							<div class="panel-heading">动态(最近十条)</div>
							<div class="panel-body">
								<c:forEach items="${activities}" var="activity">
									<p class="p-control">
										<fmt:formatDate value="${activity.createTime}"
											pattern="yyyy-MM-dd HH:mm:ss" />
										&nbsp
										<c:choose>
											<c:when test="${activity.type==10}">
												<a href="/u">${user.nickname}</a>提出了问题 <a
													title="${activity.question.title}"
													href="/question?q=${activity.question.id}">${activity.question.title}</a>
											</c:when>
											<c:when test="${activity.type==20}">
												<a href="/u">${user.nickname}</a>回答了<a href="/u">${activity.user.nickname}</a>提出的<a
													title="${activity.question.title}"
													href="/question?q=${activity.question.id}">${activity.question.title}</a>
											</c:when>
											<c:when test="${activity.type==30}">
												<a href="/u">${user.nickname}</a>
												<c:if test="${activity.isHelpful==1}">赞成</c:if>
												<c:if test="${activity.isHelpful==0}">反对</c:if>
												<a href="/u">${activity.user.nickname}</a>在<a
													title="${activity.question.title}"
													href="/question?q=${activity.question.id}">${activity.question.title}</a>
									上的回答</c:when>
										</c:choose>
									</p>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>

				<div class="tab-pane fade" id="message">
					<div class="content">
						
					</div>
				</div>
				
				<div class="tab-pane fade" id="setting">
					<div class="content">ceshi</div>
				</div>
			</div>
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
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />

									<div class="form-actions" style="width: 95%; margin: 0 auto;">
										<input type="submit" class="btn btn-block btn-primary"
											value="登陆">
									</div>
								</form>
							</div>
						</div>
						<div class="tab-pane fade" id="BBS1">
							<div style="margin: 10px 30px 20px 30px">
								<form action="/signUp" method="post" class="form-horizontal">
									<label class="label-control" for="username">用户名</label> <input
										type="text" class="form-control" name="username"
										placeholder="用户名" required> <label
										class="label-control" for="password">密码</label> <input
										type="password" class="form-control" name="password"
										placeholder="密码" required> <input type="submit"
										class="btn btn-block btn-success form-control"
										style="margin: 15px 0 0 0;" value="注册">
								</form>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	</div>
	<script type="text/javascript">
	var majorConfig = {
			type: 'pie',
			data : {
			    labels: [
			    	<c:if test="${majars.size()==0}">'暂无'</c:if>
			        <c:forEach items="${majars}" var="major">"${tagMap[major.index].name}"<c:if test="${!vs.last}">,</c:if></c:forEach>
			    ],
			    datasets: [
			        {
			            data: [<c:if test="${majars.size()==0}">1</c:if>
			            	<c:forEach items="${majars}" var="major">${major.percent}<c:if test="${!vs.last}">,</c:if></c:forEach>
						],
			            backgroundColor: [
			            	<c:if test="${majars.size()==0}">'#999999'</c:if>
			            	<c:forEach items="${majars}" var="major">"${tagMap[major.index].color}"<c:if test="${!vs.last}">,</c:if></c:forEach>
			            ],
			            hoverBackgroundColor: [
			            	<c:forEach items="${majars}" var="major">"${tagMap[major.index].color}"<c:if test="${!vs.last}">,</c:if></c:forEach>
			            ]
			        }]
			},
			options : {
				animation:{
					animateScale:true
				}
			}
	    };
	var participateConfig = {
			type: 'horizontalBar',
			data : {
				labels: [
					<c:forEach items="${participates}" var="participate">"${tagMap[participate.index].name}"<c:if test="${!vs.last}">,</c:if></c:forEach>
				],
				datasets: [
					{
						label:"参与比例",
						backgroundColor: [
							<c:forEach items="${participates}" var="participate">"${tagMap[participate.index].color}"<c:if test="${!vs.last}">,</c:if></c:forEach>
			            ],
						data: [
							<c:forEach items="${participates}" var="participate">${participate.percent}<c:if test="${!vs.last}">,</c:if></c:forEach>
						]
					}
				]
			},
			options : {
				scales: {
					xAxes: [{
						stacked: true
					}],
					yAxes: [{
						stacked: true
					}]
				}
			}
	    };
	window.onload = function() {
        $(".content").height(700);
    };
    
   $("a[href='#AQ']").bind("click",loadChart);
   
    
    function loadChart(){
    	 $("#AQ").addClass("active");
    	 var ctx = document.getElementById("major").getContext("2d");
         window.myPie = new Chart(ctx, majorConfig);
         
         ctx = document.getElementById("participate").getContext("2d");
         window.myBar = new Chart(ctx, participateConfig);
         
    }
    
    $(".btn-op").bind("click",function(){
		if ($("a.sign").text() != "") {
				toastr.info("请登录");
				$(".sign").trigger("click");
			}
	})
	
	$("[name='follow-btn']").bind("click",function(){
		<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null }">
		var data = {"userId":${user.id},"followerId":${currentUser.id}};
		var handle = $(this);
		$.ajax({	
			async : false,
			type : "POST",
			url : "/follow",
			data : $.param(data),
			datatype : 'json',
			success : function(result) {
				if (result != "fail") {
					toastr.success("关注成功！");
					setTimeout(function(){location.reload();},1200);
				}else{
					toastr.error("关注失败");
				}
			},
			error : function(jqXHR,textStatus,errorThrown) {
						alert(textStatus);
			}
		});</c:if>
	})
	
	$("[name='unfo-btn']").bind("click",function(){
		<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null }">
		var data = {"userId":${user.id},"followerId":${currentUser.id}};
		var handle = $(this);
		$.ajax({	
			async : false,
			type : "POST",
			url : "/unfo",
			data : $.param(data),
			datatype : 'json',
			success : function(result) {
				if (result != "fail") {
					toastr.success("取关成功！");
					setTimeout(function(){location.reload();},1200);
				}else{
					toastr.error("取关失败");
				}
			},
			error : function(jqXHR,textStatus,errorThrown) {
						alert(textStatus);
			}
		});</c:if>
	})
	
	</script>
</body>

</html>
