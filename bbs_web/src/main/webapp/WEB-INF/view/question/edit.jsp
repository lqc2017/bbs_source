<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑</title>
<link href="/css/tag.css" rel="stylesheet">
<%@ include file="../source.jsp"%>
<script src="/ckeditor/ckeditor.js"></script>
</head>

<body>
	<div style="width: 800px; margin: 20px 200px 0 200px;">
		<form id="add_form" class="form-horizontal" action="/question/add" method="post">
			<input type="hidden" name="name" value="${user.nickname}"/>
			<input type="hidden" name="createBy" value="${user.id}"/>
			<input type="hidden" name="updateBy" value="${user.id}"/>
			<div class="form-group">
				<label class="col-sm-2 control-label">标题</label><div class="col-sm-6"><input class="form-control" name="title" data-toggle="collapse" data-parent="#accordion"/></div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">标签</label>
				<div class="col-sm-5">
					<input class="form-control" name="tags_name" disabled />
					<input type="hidden" name="tags" />
				</div>
				<a data-toggle="collapse" data-parent="#accordion"
					href="#collapseOne" class="btn btn-default">展开</a>
				<div id="collapseOne" class="panel-collapse collapse">
					<div class="panel panel-default"
						style="width: 600px; margin: 5px 0 0 145px;">
						<div class="panel-body">
							<div class="panel panel-default">
							<div class="panel-heading">语言</div>
								<div class="panel-body">
								<ul class="tag-list__itembody taglist--inline multi">
								<li><a class="tag" data="10000"><span class="glyphicon glyphicon-tag"></span>c</a></li>
								<li><a class="tag" data="10025"><span class="glyphicon glyphicon-tag"></span>c++</a></li>
								<li><a class="tag" data="10050"><span class="glyphicon glyphicon-tag"></span>java</a></li>
								<li><a class="tag" data="10100"><span class="glyphicon glyphicon-tag"></span>javascript</a></li>
								<li><a class="tag" data="10150"><span class="glyphicon glyphicon-tag"></span>php</a></li>
								<li><a class="tag" data="10200"><span class="glyphicon glyphicon-tag"></span>perl</a></li>
								<li><a class="tag" data="10250"><span class="glyphicon glyphicon-tag"></span>python</a></li>
								<li><a class="tag" data="10300"><span class="glyphicon glyphicon-tag"></span>ruby</a></li>
								<li><a class="tag" data="10350"><span class="glyphicon glyphicon-tag"></span>objective-c</a></li>
								<li><a class="tag" data="10400"><span class="glyphicon glyphicon-tag"></span>go</a></li>
								<li><a class="tag" data="10450"><span class="glyphicon glyphicon-tag"></span>node.js</a></li>
								<li><a class="tag" data="10500"><span class="glyphicon glyphicon-tag"></span>erlang</a></li>
								<li><a class="tag" data="10550"><span class="glyphicon glyphicon-tag"></span>scala</a></li>
								<li><a class="tag" data="10600"><span class="glyphicon glyphicon-tag"></span>bash</a></li>
								<li><a class="tag" data="10650"><span class="glyphicon glyphicon-tag"></span>actionscipt</a></li>
								</ul>
							</div>
							</div>
							<div class="panel panel-default">
								<div class="panel-heading">系统</div>
								<div class="panel-body">
								<ul class="tag-list__itembody taglist--inline multi">
								<li><a class="tag" data="15000"><span class="glyphicon glyphicon-tag"></span>ios</a></li>
								<li><a class="tag" data="15050"><span class="glyphicon glyphicon-tag"></span>android</a></li>
								<li><a class="tag" data="15100"><span class="glyphicon glyphicon-tag"></span>windows</a></li>
								<li><a class="tag" data="15150"><span class="glyphicon glyphicon-tag"></span>linux</a></li>
								<li><a class="tag" data="15200"><span class="glyphicon glyphicon-tag"></span>unix</a></li>
								</ul>
								</div>
							</div>
							<div class="panel panel-default">
							<div class="panel-heading">数据库</div>
								<div class="panel-body">
								<ul class="tag-list__itembody taglist--inline multi">
								<li><a class="tag" data="20000"><span class="glyphicon glyphicon-tag"></span>mysql</a></li>
								<li><a class="tag" data="20050"><span class="glyphicon glyphicon-tag"></span>sqlite</a></li>
								<li><a class="tag" data="20100"><span class="glyphicon glyphicon-tag"></span>oracle</a></li>
								<li><a class="tag" data="20150"><span class="glyphicon glyphicon-tag"></span>nosql</a></li>
								<li><a class="tag" data="20200"><span class="glyphicon glyphicon-tag"></span>mongodb</a></li>
								<li><a class="tag" data="20250"><span class="glyphicon glyphicon-tag"></span>redis</a></li>
								</ul>
								</div>
							</div>
							<div class="panel panel-default">
							<div class="panel-heading">服务器</div>
								<div class="panel-body">
								<ul class="tag-list__itembody taglist--inline multi">
								<li><a class="tag" data="25000"><span class="glyphicon glyphicon-tag"></span>apache</a></li>
								<li><a class="tag" data="25050"><span class="glyphicon glyphicon-tag"></span>nginx</a></li>
								</ul>
								</div>
							</div>
							<div class="panel panel-default">
							<div class="panel-heading">前端</div>
								<div class="panel-body">
								<ul class="tag-list__itembody taglist--inline multi">
								<li><a class="tag" data="30000"><span class="glyphicon glyphicon-tag"></span>html</a></li>
								<li><a class="tag" data="30050"><span class="glyphicon glyphicon-tag"></span>html5</a></li>
								<li><a class="tag" data="30100"><span class="glyphicon glyphicon-tag"></span>css</a></li>
								<li><a class="tag" data="30150"><span class="glyphicon glyphicon-tag"></span>css3</a></li>
								<li><a class="tag" data="30200"><span class="glyphicon glyphicon-tag"></span>jquery</a></li>
								<li><a class="tag" data="30250"><span class="glyphicon glyphicon-tag"></span>json</a></li>
								<li><a class="tag" data="30300"><span class="glyphicon glyphicon-tag"></span>ajax</a></li>
								<li><a class="tag" data="30350"><span class="glyphicon glyphicon-tag"></span>bootstrap</a></li>
								</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div style="width: 680px; margin: 0 0 0 75px;">
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
		var tagList = new Array();
		var editor = CKEDITOR.replace('ckeditor',{
	        customConfig : '/ckeditor/question_config.js'
	    })
		
		$('#add_form').bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        trigger:'change',
	        fields: {
	        	'title': {
	                validators: {
	                    notEmpty: { message: '标题不能为空'},
	                    callback: {
	                        message: '超过输入限制,请控制在60个汉字以内',
	                        callback: function(value, validator) {
	                        	if(getLen(value)<=120)
	                        		return true
	                        	return false;
	                        }
	                    }
	                }
	            }
	        }
	    })
	    
	    $("a[href='#collapseOne']").bind("click",function(){
	    	if($(this).text().trim()=="展开"){
	    		$(this).text("收起");
	    	}
	    	else
	    		$(this).text("展开");
	    })
		
		$("button[name='add-btn']").bind("click",function(){
			var validator = $('#add_form').data('bootstrapValidator');
			var describe = editor.getData().toString();
			alert("标题长度:"+getLen($("input[name='title']").val()));
			alert("描述长度:"+getLen(describe));
			
			validator.validate();
			if (!validator.isValid()) {
				return;
			}
			if(getLen(describe)>2000){
				toastr.warning("描述超过最大长度限制");
				return;
			}
			
			/* 初始化tags属性 */
			var tags = JSON.stringify(tagList);
			$("input[name='tags']").val(tags);
			$("#add_form").submit();
			validator.defaultSubmit();;
		})
		
		$(".tag").bind("click",function(){
			var data = $(this).attr("data");
			if(tagList.length>=5){
				toastr.warning("最多添加5个标签！");
				return
			}
			if($.inArray(data,tagList)>-1){
				$(this).removeClass("select");
				$(this).children(".glyphicon-remove").remove();
				$(this).unbind("mouseout mouseover");
				var index = $.inArray(data,tagList);
				tagList.splice(index, 1);
				
				printTags();
			}else{
				tagList.push(data);
				$(this).bind("mouseover",function(){
					$(this).append("<span class='glyphicon glyphicon-remove'></span>")
				});
				$(this).bind("mouseout",function(){
					$(this).children(".glyphicon-remove").remove();
				});
				$(this).addClass("select");
				toastr.success("添加标签成功！");
			}
			
			printTags()
		})
		
		function printTags(){
			var str = "";
			tagList.forEach(function(value,index){
				if(index!=0)
					str += ",";
				str = str + $("a[data='"+value+"']").text();
			})
			$("input[name='tags_name']").val(str);
		}
	</script>
</body>

</html>