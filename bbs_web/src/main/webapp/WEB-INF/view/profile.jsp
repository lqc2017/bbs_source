<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link href="/css/user-home.css" rel="stylesheet">
<%@ include file="source.jsp"%>
<script src="/js/plugins/Chart.bundle.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="left-panel">
			<img /><img
				src="https://avatars0.githubusercontent.com/u/26128332?v=3&s=460"
				height="230" class="img-rounded img-responsive">
			<a class="btn btn-default btn-block btn-op">Edit profile</a>
		</div>
		<div class="main-panel">
			<ul id="myTab" class="nav nav-tabs">
				<li><a class="spacing" href="#information" data-toggle="tab">个人信息</a></li>
				<li><a class="spacing" href="#BBS" data-toggle="tab">论坛</a></li>
				<li class="active"><a class="spacing" href="#AQ"
					data-toggle="tab">问答</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade" id="information">
					<div class="content">用户已设为私密</div></div>
					<div class="tab-pane fade" id="BBS">
					<div class="content">ceshi</div>
				</div>
				<div class="tab-pane fade in active" id="AQ">
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
										<fmt:formatDate value="${activity.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										&nbsp<c:choose>
											<c:when test="${activity.type==10}"><a href="/u">${user.nickname}</a>提出了问题 <a title="${activity.question.title}" href="/question?q=${activity.question.id}">${activity.question.title}</a>
											</c:when>
											<c:when test="${activity.type==20}"><a href="/u">${user.nickname}</a>回答了<a href="/u">${activity.user.nickname}</a>提出的<a title="${activity.question.title}" href="/question?q=${activity.question.id}">${activity.question.title}</a>
											</c:when>
											<c:when test="${activity.type==30}"><a href="/u">${user.nickname}</a><c:if test="${activity.isHelpful==1}">赞成</c:if><c:if test="${activity.isHelpful==0}">反对</c:if>
												<a href="/u">${activity.user.nickname}</a>在<a title="${activity.question.title}" href="/question?q=${activity.question.id}">${activity.question.title}</a>
									上的回答</c:when>
										</c:choose>
									</p>
								</c:forEach>
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
			        <c:forEach items="${majars}" var="major">"${tagMap[major.index].name}"<c:if test="${!vs.last}">,</c:if></c:forEach>
			    ],
			    datasets: [
			        {
			            data: [
			            	<c:forEach items="${majars}" var="major">${major.percent}<c:if test="${!vs.last}">,</c:if></c:forEach>
						],
			            backgroundColor: [
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
        var ctx = document.getElementById("major").getContext("2d");
        window.myPie = new Chart(ctx, majorConfig);
        
        ctx = document.getElementById("participate").getContext("2d");
        window.myBar = new Chart(ctx, participateConfig);
        
		/* 防止标签页因为高度不一抖动 */
        var maxH = 0;
        $(".content").each(function(){
        	if($(this).height()>maxH)
        		maxH = $(this).height();
        });
        $(".content").height(maxH);
    };
    
   $("a[href='#AQ']").bind("click",loadChart);
   
    
    function loadChart(){
    	 $("#AQ").addClass("active");
    	 var ctx = document.getElementById("major").getContext("2d");
         window.myPie = new Chart(ctx, majorConfig);
         
         ctx = document.getElementById("participate").getContext("2d");
         window.myBar = new Chart(ctx, participateConfig);
         
    }
    
	</script>
</body>

</html>
