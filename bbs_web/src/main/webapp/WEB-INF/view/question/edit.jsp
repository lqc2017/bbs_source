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
				<li><a href="#">问答</a></li>
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
							<li><a href="#">消息</a></li>
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
	<div style="width: 800px; margin: 20px 200px 0 200px;">
		<form id="add_form" class="form-horizontal" action="/q/add" method="post">
			<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
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
		var tagList = new Array();
		
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
			/* alert("标题长度:"+getLen($("input[name='title']").val()));
			alert("描述长度:"+getLen(describe)); */
			
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
			if($.inArray(data,tagList)>-1){
				$(this).removeClass("select");
				$(this).children(".glyphicon-remove").remove();
				$(this).unbind("mouseout mouseover");
				var index = $.inArray(data,tagList);
				tagList.splice(index, 1);
				
				printTags();
			}else{
				if(tagList.length>=5){
					toastr.warning("最多添加5个标签！");
					return
				}
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
		
		$("input[name='title']").bind("focus",function(){
			if ($("a.sign").text() != "") {
					toastr.info("请登录");
					$(".sign").trigger("click");
				}
		})

	</script>
</body>

</html>