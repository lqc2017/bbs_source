<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑</title>
<%@ include file="../source.jsp"%>
<script src="/ckeditor/ckeditor.js"></script>
</head>

<body>
	<div style="width: 800px; margin: 20px 200px 0 200px;">
		<form id="add_form" class="form-horizontal" action="/question/add" method="post">
			<div class="form-group">
				<label class="col-sm-2 control-label">标题</label><div class="col-sm-6"><input class="form-control" name="title" /></div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">标签</label><div class="col-sm-6"><input class="form-control" name="" /></div>
			</div>

				<div style="width: 680px;margin: 0 0 0 75px;">
					<textarea id="ckeditor" name="describe" cols="20" rows="2"
						class="ckeditor"></textarea>
				</div>
		</form>
	</div>

	<div style="width: 800px;hight：auto; margin: 10px 200px;">
		<div style="width: 680px;hight：auto; margin: 0 0 0 75px;">
			<button name="add-btn" style="float:right" class="btn btn-sm btn-success">提交</button>
		</div>
	</div>


	<script defer type="text/javascript">
		CKEDITOR.replace('ckeditor',{
	        customConfig : '/ckeditor/question_config.js'
	    });
		
		$("button[name='add-btn']").bind("click",function(){
			$("#add_form").submit();
		})
	</script>
</body>

</html>