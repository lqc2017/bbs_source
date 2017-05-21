<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<%-- <%@ include file="source.jsp"%> --%>
<script src="/js/plugins/Chart.bundle.min.js"></script>
<script src="/js/commons/global.js"></script>
</head>

<body>
	<div style="width:45%;display:inline-block;">
	<canvas id="major" width="400" height="200"></canvas>
	</div>
	<div style="width:45%;display:inline-block;">
	<canvas id="participate" width="400" height="200"></canvas>
	</div>
	<script>
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
							label:"percent",
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
	        
	        var ctx = document.getElementById("participate").getContext("2d");
	        window.myPie = new Chart(ctx, participateConfig);
	    };
	</script>
</body>

</html>