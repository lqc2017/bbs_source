<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${question.title}" escapeXml="true"></c:out></title>
<link href="/css/question.css" rel="stylesheet">
<%@ include file="../source.jsp"%>
<script src="/ckeditor/ckeditor.js"></script>
</head>

<body>
	<div class="panel panel-info question">
		<div class="panel-heading">
			<h3> 
				<c:out value="${question.title}" escapeXml="true"></c:out>
			</h3>
		</div>
		<div class="panel-body div-control">${question.describe}</div>
		<div class="message">
			<c:if test="${tags.size()!=0}">
				<div class="questoin-tags">
					<c:forEach items="${tags}" var="tag" varStatus="vs">
						<a class="tag" href="">${tag}</a>
					</c:forEach>
				</div>
			</c:if>
			<div class="questoin-message">
				<label class="create-time">创建于：<fmt:formatDate
						value="${question.createTime}" pattern="yyyy-MM-dd" /></label><a
					class="test" href="">usernamennnnnnnnn</a>
			</div>
		</div>
		<%-- <div class="questoin-message">
			 <!-- <a class="test" href=""> -->${'username'}</a><label class="create-time">创建于：<fmt:formatDate
					value="${question.createTime}" pattern="yyyy-MM-dd" /></label>
		</div> --%>
	</div>
	<div class="middle">
		<label style="float: left">${answers.size()}个回答：</label>
		<a href="#input" style="float: right"
			class="btn btn-sm btn-success">填写答案</a>
	</div>
	<div class="panel panel-default answers">
		<c:forEach items="${answers}" var="answer" varStatus="vs">
			<div>
				<div class="panel-body div-control ">${answer.content}</div>
				<div class="answer-message">
					<a href="">${'username'}</a> <label class="create-time"><fmt:formatDate
							value="${answer.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
					<label class="helpful-block">
						<%-- <span <c:if test="${!helpEnable.get(vs.count-1)}">disable</c:if>>有用</span>
						<span <c:if test="${!helpEnable.get(vs.count-1)}">disable</c:if>>无用</span> --%>

						<span class="glyphicon  glyphicon-chevron-left default <c:if test="${helpEnable.get(vs.count-1)}">helpless</c:if>"
						a="${answer.id}" value="0" data-toggle="tooltip"
						data-placement="left" title="<c:if test="${helpEnable.get(vs.count-1)}">毫无作用</c:if><c:if test="${!helpEnable.get(vs.count-1)}">您已评价！</c:if>"></span>
						<span id="helpful">${answer.helpful}</span> 
						<span class="glyphicon glyphicon-chevron-right default <c:if test="${helpEnable.get(vs.count-1)}">helpful</c:if>"
						a="${answer.id}" value="1" data-toggle="tooltip"
						data-placement="right" title="<c:if test="${helpEnable.get(vs.count-1)}">对我有用</c:if><c:if test="${!helpEnable.get(vs.count-1)}">您已评价！</c:if>"></span>
					</label>
				</div>
			</div>
			<hr <c:if test="${vs.last}">style="border-top:0px;"</c:if>/>
		</c:forEach>
	</div>

	<div class="input" id="input">
		<form id="add_form" method="post" action="/answer">
			<textarea id="ckeditor" name="content" cols="20" rows="5"
				class="ckeditor"></textarea>
			<input type="hidden" name="questionId" value="${question.id}">
		</form>
	</div>
	<div class="bottom">
		<button  name='add-btn' style="float: right"
			class="btn btn-sm btn-success">提交</button>
	</div>

	<script defer type="text/javascript">
		$("img").parent("p").addClass("p-control");
		$("[data-toggle='tooltip']").tooltip();
		
		var editor=CKEDITOR.replace('ckeditor',{
	        customConfig : '/ckeditor/question_config.js'
	    });
		
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
			
			
			$.ajax({	
				async : false,
				type : "POST",
				url : "/answer/answer_help",
				data : $.param(data),
				datatype : 'json',
				success : function(result) {
					if (result != "fail") {
						toastr.success("评价成功！");
						$("#helpful").empty();
						$("#helpful").append(result);
						
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
				url : "/answer",
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
	</script>
</body>

</html>