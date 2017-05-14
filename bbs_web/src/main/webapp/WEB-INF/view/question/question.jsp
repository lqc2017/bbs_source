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
	<div class="question">
		<div class="div-control"><h2><c:out value="${question.title}" escapeXml="true"></c:out></h2></div>
		<hr/>
		<div class="div-control">${question.describe}</div>
		<div class="questoin-message">
			<a href="">${'username'}</a> 
			<label class="create-time">创建于：<fmt:formatDate value="${question.createTime}" pattern="yyyy-MM-dd" /></label>
		</div>
	</div>
	<div class="middle">
		<label style="float: left">${answers.size()}个回答：</label>
		<a href="#input" style="float: right"
			class="btn btn-sm btn-success">填写答案</a>
	</div>
	<div class="answers">
		<c:forEach items="${answers}" var="answer" varStatus="vs">
			<div>
				<div class="div-control">${answer.content}</div>
				<div class="answer-message">
					<a href="">${'username'}</a> <label class="create-time"><fmt:formatDate
							value="${answer.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
				</div>
			</div>
			<c:if test="${!vs.last}"><hr/></c:if>
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
		
		var editor=CKEDITOR.replace('ckeditor',{
	        customConfig : '/ckeditor/question_config.js'
	    });
		
		/* $("button[name='add-btn']").bind("click",function(){
			$("#add_form").submit();
		}) */
		
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