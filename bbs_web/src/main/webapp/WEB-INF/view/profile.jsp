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
							src="${currentUser.protraitUrl}"
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
			<img src="${user.protraitUrl}" height="200" width="100%" class="img-rounded">
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
						<a class="btn btn-default btn-block btn-op" data-toggle="modal" data-target="#modal">编辑头像</a>
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
					<div class="content"><!-- 用户已设为私密 -->
					<div>
					<div style="width:50%;display:inline-block;">
						<div class="panel panel-default">
							<div class="panel-heading">
								基本信息
								<c:if test="${user.id==currentUser.id }">
								<div id="base-btn-grp" class="btn-group" style="float: right;">
									<button name="edit" class="btn btn-default btn-xs">编辑</button>
								</div>
								</c:if>
							</div>
							<div class="panel-body">
								<form name="base_form" action="/u/update" method="post">
								<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
								<p>昵称：<span ref="nickname">${user.nickname}</span></p>
								<p>性别：<span ref="sex"><c:if test="${user.sex==0}">男</c:if><c:if test="${user.sex==1}">女</c:if></span></p>
									<p>出生日期：<span ref="birthday"><c:if test="${user.birthday==null}">未填写</c:if>
									<c:if test="${user.birthday!=null}"><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/></c:if></span>
									</p>
								</form>
							</div>
						</div>
						
						<div class="panel panel-default">
							<div class="panel-heading">
								联系方式
								<c:if test="${user.id==currentUser.id }">
								<div id="contact-btn-grp" class="btn-group" style="float: right;">
									<button name="edit" class="btn btn-default btn-xs">编辑</button>
								</div>
								</c:if>
							</div>
							<div class="panel-body">
								<form name="contact_form" action="/u/update" method="post">
								<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
									<p>邮箱：<span ref="email"><c:if test="${contactInfo.email==null||contactInfo.email==''}">未填写</c:if>${contactInfo.email}</span></p>
									<p>手机：<span ref="mobilephone"><c:if test="${contactInfo.mobilephone==null||contactInfo.mobilephone==''}">未填写</c:if>${contactInfo.mobilephone}</span></p>
									<p>QQ：<span ref="qq"><c:if test="${contactInfo.qq==null||contactInfo.qq==''}">未填写</c:if>${contactInfo.qq}</span></p>
								</form>
							</div>
						</div>
						
					</div>
					</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								联系地址
								<c:if test="${user.id==currentUser.id }">
								<div id="address-btn-grp" class="btn-group" style="float: right;">
									<button name="edit" class="btn btn-default btn-xs">编辑</button>
								</div>
								</c:if>
							</div>
							<div class="panel-body">
								<form name="address_form" action="/u/update" method="post">
								<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
									<p>学校：<span ref="school"><c:if test="${addressInfo.school==null||addressInfo.school==''}">未填写</c:if>${addressInfo.school}</span></p>
									<p>公司：<span ref="company"><c:if test="${addressInfo.company==null||addressInfo.company==''}">未填写</c:if>${addressInfo.company}</span></p>
									<p>住址：<span ref="home"><c:if test="${addressInfo.home==null||addressInfo.home==''}">未填写</c:if>${addressInfo.home}</span></p>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="BBS">
					<div class="content" id="test">
						<div class="panel panel-default">
							<div class="panel-heading">我的帖子（最新动态十条）</div>
							<div class="panel-body">
								<table class="table">
									<thead>
										<tr>
											<td>帖子名称</td>
											<td>发布者</td>
											<td>发布时间</td>
											<td>最后回复时间</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><a href="#">python自动运维实战</a></td>
											<td><a href="#">刘言石</a></td>
											<td>2017-5-23</td>
											<td>2017-5-25</td>
										</tr>
										<tr>
											<td><a href="#">python自动运维实战</a></td>
											<td><a href="#">张绍俊</a></td>
											<td>2017-5-23</td>
											<td>2017-5-24</td>
										</tr>
									</tbody>
								</table>
							</div>
					</div>
					<div class="panel panel-default">
							<div class="panel-heading">最近浏览（最近五条）</div>
							<div class="panel-body">
								<table class="table">
									<thead>
										<tr>
											<td>帖子名称</td>
											<td>发布者</td>
											<td>发布时间</td>
											<td>最后浏览时间</td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><a href="#">python自动运维实战</a></td>
											<td><a href="#">刘言石</a></td>
											<td>2017-5-23</td>
											<td>2017-5-25</td>
										</tr>
										<tr>
											<td><a href="#">python自动运维实战</a></td>
											<td><a href="#">张绍俊</a></td>
											<td>2017-5-23</td>
											<td>2017-5-24</td>
										</tr>
									</tbody>
								</table>
							</div>
					</div>
					<div class="panel panel-default" style="width: 450px">
								<div class="panel-heading">推荐达人</div>
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
							<div align="center">
								<button type="button" class="btn btn-success btn-lg">我要发帖</button>
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
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
	
	<c:if test="${user.id==currentUser.id }">
	<div class="modal fade" id="portraitModal" tabindex="-1" role="dialog" aria-labelledby="portraitModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="portraitModalLabel">上传头像</h4>
				</div>
				<div class="modal-body">
<!-- 						<input name="myFile" id="portrait" type="file" class="file" data-show-preview="true" data-allowed-file-extensions='["jepg","jpg", "png", "bmp"]'>
 -->			
 				</div>
			</div>
		</div>
	</div></c:if>
	
	<!-- Modal -->
    <div class="modal fade" id="modal" aria-labelledby="modalLabel" role="dialog" tabindex="-1">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
           <h4 class="modal-title" id="modalLabel">上传头像</h4>
          </div>
          <div class="modal-body">
            <div>
              <div class="avatar-upload">
                  <input type="hidden" class="avatar-src" name="avatar_src">
                  <input type="hidden" class="avatar-data" name="avatar_data">
                  <label for="avatarInput">Local upload</label>
                  <input type="file" class="avatar-input" id="avatarInput" name="avatar_file">
                </div>

                
                <img id="image" src="${user.protraitUrl}">
            </div>
          </div>
          <div class="modal-footer">
          <button id="upload" type="button" class="btn btn-default">upload</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
	<script type="text/javascript">
	var infoBuffer = new Array()
	var contactBuffer = new Array()
	var addressBuffer = new Array()
	
		  var $image = $('#image');
	      var cropBoxData;
	      var canvasData;
	      var url;

	      $('#modal').on('shown.bs.modal', function () {
	        $image.cropper({
	        	aspectRatio: 1 / 1,
	  		  modal: false,
	          ready: function () {
	            $image.cropper('setCanvasData', canvasData);
	            $image.cropper('setCropBoxData', cropBoxData);
	          }
	        });
	      }).on('hidden.bs.modal', function () {
	        cropBoxData = $image.cropper('getCropBoxData');
	        canvasData = $image.cropper('getCanvasData');
	        $image.cropper('destroy');
	      });
	      
	      $("#avatarInput").bind("change",function(){
	    	  console.log('something');
	    	  var files;
	    	  var file;
	    	  if (!!$('<input type="file">').prop('files')&&!!window.URL && URL.createObjectURL) {
	    	        files = $(this).prop('files');

	    	        if (files.length > 0) {
	    	          file = files[0];
	    	          if (file.size > 1048576){
	    	        	  toastr.error("图片不能大于1MB");
	    	        	  return;
	    	          }
	    	          if (!isImageFile(file)){
	    	        	  toastr.error("格式错误");
	    	        	  return;
	    	          }
	    	          if (url) {
	    	             URL.revokeObjectURL(url); // Revoke the old one
	    	          }

	    	          url = URL.createObjectURL(file);
	    	          $image.cropper('replace',url);
	    	        }
	    	      }
	      })
	      
	      function isImageFile(file) {
      		if (file.type) {
        	return /^image\/\w+$/.test(file.type);
      		} else {
        		return /\.(jpg|jpeg|png|gif)$/.test(file);
      		}
    	}
	      
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
	
    $("#base-btn-grp").find("button[name='edit']").bind("click",{p:10},editCallback)
    $("#contact-btn-grp").find("button[name='edit']").bind("click",{p:20},editCallback)
    $("#address-btn-grp").find("button[name='edit']").bind("click",{p:30},editCallback)
    
    function cancelCallback(event){
    	var btnGrp = $(this).parent("div");
    	var _p = event.data.p;
    	
    	btnGrp.children().remove();
    	btnGrp.append("<button name='edit' class='btn btn-default btn-xs'>编辑</button>");
    	btnGrp.find("button[name='edit']").bind("click",{p:_p},editCallback);
    	
    	if(_p==10){
	    	var form = $("form[name='base_form']");
	    	var spans = form.find("span");
	    	spans.each(function(i){
	    		$(this).children().remove();
	    		$(this).append(infoBuffer[i]);
	    	})
	    	infoBuffer = [];
    	}else if(_p==20){
    		var form = $("form[name='contact_form']");
	    	var spans = form.find("span");
	    	spans.each(function(i){
	    		$(this).children().remove();
	    		$(this).append(contactBuffer[i]);
	    	})
	    	contactBuffer = [];
    	}else if(_p==30){
    		var form = $("form[name='address_form']");
	    	var spans = form.find("span");
	    	spans.each(function(i){
	    		$(this).children().remove();
	    		$(this).append(addressBuffer[i]);
	    	})
	    	addressBuffer = [];
    	}
    }
    
    function editCallback(event){
    	var form;
    	var _p = event.data.p;
    	if(_p==10){
    		initBaseForm();
    		form = $("form[name='base_form']");
    		$("form[name='base_form']").bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                trigger:'change',
                fields: {
                	 'nickname': {
                        validators: {
                            notEmpty: { message: '昵称不能为空'},
                            callback: {
                                message: '超过输入限制,请控制在12个字符以内',
                                callback: function(value, validator) {
                                	if(getLen(value)<=12)
                                		return true
                                	return false;
                                }
                            }
                        }
                    } 
                }
            })
    	}else if(_p==20){
    		initContactForm();
    		form = $("form[name='contact_form']");
    	   	$("form[name='contact_form']").bootstrapValidator({
    	           message: 'This value is not valid',
    	           feedbackIcons: {
    	               valid: 'glyphicon glyphicon-ok',
    	               invalid: 'glyphicon glyphicon-remove',
    	               validating: 'glyphicon glyphicon-refresh'
    	           },
    	           trigger:'change',
    	           fields: {
    	           	 'email': {
    	                   validators: {
    	                	   emailAddress: {
    	                            message: '邮箱地址格式有误'
    	                        },
    	                       callback: {
    	                           message: '超过输入限制,请控制在25个字符以内',
    	                           callback: function(value, validator) {
    	                           	if(getLen(value)<=25)
    	                           		return true
    	                           	return false;
    	                           }
    	                       }
    	                   }
    	               },
    	               'mobilephone': {
    	                   validators: {
    	                	   callback: {
    	                           message: '请输入正确的电话号码',
    	                           callback: function(value, validator) {
    	                        	if(getLen(value)==0)
          	                           		return true;
    	                           	if(getLen(value)<=15)
    	                           		if(/^1[34578]\d{9}$/.test(value))
    	                           			return true;
    	                           	return false;
    	                           }
    	                       }
    	                   }
    	               },
    	               'qq': {
    	                   validators: {
    	                	   numeric: {
    	                		   message: '请输入正确的qq号'
    	                	   },
    	                	   callback: {
    	                           message: 'qq号位数错误',
    	                           callback: function(value, validator) {
    	                        	if(getLen(value)==0)
       	                           		return true;
    	                           	if(getLen(value)>11||getLen(value)<6)
    	                           		return false;
    	                           	return true;
    	                           }
    	                       }
    	                   }
    	               }
    	           }
    	       })
    	}else if(_p==30){
    		initAddressForm();
    		form = $("form[name='address_form']");
    		form.bootstrapValidator({
 	           message: 'This value is not valid',
 	           feedbackIcons: {
 	               valid: 'glyphicon glyphicon-ok',
 	               invalid: 'glyphicon glyphicon-remove',
 	               validating: 'glyphicon glyphicon-refresh'
 	           },
 	           trigger:'change',
 	           fields: {
 	           	 'school': {
 	                   validators: {
 	                       callback: {
 	                           message: '超过输入限制,请控制在80个字符以内',
 	                           callback: function(value, validator) {
 	                           	if(getLen(value)<=80)
 	                           		return true
 	                           	return false;
 	                           }
 	                       }
 	                   }
 	               },
 	               'company': {
 	                   validators: {
 	                	   callback: {
 	                           message: '超过输入限制,请控制在100个字符以内',
 	                           callback: function(value, validator) {
 	                           	if(getLen(value)<=100)
 	                           		return true
 	                           	return false;
 	                           }
 	                       }
 	                   }
 	               },
 	               'home': {
 	                   validators: {
 	                	   callback: {
 	                		  message: '超过输入限制,请控制在120个字符以内',
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
    	}
    	
         /* 初始化button */
    	var btnGrp = $(this).parent("div");
    	btnGrp.children().remove();
    	btnGrp.append("<button name='save' class='btn btn-success btn-xs'>保存</button><button name='cancel' class='btn btn-default btn-xs'>取消</button>");
    	btnGrp.find("button[name='cancel']").bind("click",{p:_p},cancelCallback);
    	btnGrp.find("button[name='save']").bind("click",function(){
    		form.data('bootstrapValidator').defaultSubmit();
    	});
    }
    
    function initBaseForm(){
    	 /* 初始化表单控件 */
    	var form = $("form[name='base_form']");
    	var spans = form.find("span");
    	spans.each(function(){
    	   var text = $(this).text();
    	   infoBuffer.push(text);
    	   $(this).empty();
    	   if($(this).attr("ref")=='nickname'){
        	   $(this).append("<div class='form-group'><input class='form-control' name='nickname' value='"+text+"'></div>");
    	   }
    	   else if($(this).attr("ref")=='sex'){
        	   $(this).append("<select name='sex' style='width: 100px;' class='form-control'><option value='0'>男</option><option value='1'>女</option></select>");
        	   if(text=='男')
        		   $(this).find("option[value='0']").attr("selected", true);
        	   else if(text=='女')
        		   $(this).find("option[value='1']").attr("selected", true);
    	   }
    	   else if($(this).attr("ref")=='birthday'){
        	   $(this).append("<div class='input-group date'><input name='birthday' size='20' class='form-control form_date' readonly value='"+text+"'><span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span></div>");
        	   
        	   if(text.trim()=='未填写')
        		   $(this).find("input").val('');
        	   else
        		   $(this).find("input").val(text.trim());
        	   //初始化日期控件 
        	   $('.form_date').datetimepicker({
       			language : 'zh-CN',
       			weekStart : 1,
       			todayBtn : 0,
       			autoclose : 1,
       			todayHighlight : 1,
       			startView : 2,
       			minView : 2,
       			forceParse : 0,
       			format : 'yyyy-mm-dd'
       			});
        	   $(this).find(".glyphicon-remove").bind("click",function(){
        		   $("input[name='birthday']").val('');
        	   })
    	   }
    	});
    }
    
    function initContactForm(){
	   	/* 初始化表单控件 */
	   	var form = $("form[name='contact_form']");
	   	var spans = form.find("span");
	   	spans.each(function(){
	   	   var text = $(this).text();
	   	   contactBuffer.push(text);
	   	   $(this).empty();
	   	   if($(this).attr("ref")=='email'){
	       	 $(this).append("<div class='form-group'><input class='form-control' name='email' value='"+text+"'></div>");
	       	if(text.trim()=='未填写')
     		   $(this).find("input").val('');
	   	   }
	   	   else if($(this).attr("ref")=='mobilephone'){
	   		 $(this).append("<div class='form-group'><input class='form-control' name='mobilephone' value='"+text+"'></div>");
	   		if(text.trim()=='未填写')
     		   $(this).find("input").val('');
	   	   }
	   	   else if($(this).attr("ref")=='qq'){
		   	 $(this).append("<div class='form-group'><input class='form-control' name='qq' value='"+text+"'></div>");
		   	if(text.trim()=='未填写')
	     		   $(this).find("input").val('');
	   	   }
	   	});
   }
    
    function initAddressForm(){
	   	/* 初始化表单控件 */
	   	var form = $("form[name='address_form']");
	   	var spans = form.find("span");
	   	spans.each(function(){
	   	   var text = $(this).text();
	   	   addressBuffer.push(text);
	   	   $(this).empty();
	   	   if($(this).attr("ref")=='school'){
	       	 $(this).append("<div class='form-group'><input class='form-control' name='school' value='"+text+"'></div>");
	       	if(text.trim()=='未填写')
     		   $(this).find("input").val('');
	   	   }
	   	   else if($(this).attr("ref")=='company'){
	   		 $(this).append("<div class='form-group'><input class='form-control' name='company' value='"+text+"'></div>");
	   		if(text.trim()=='未填写')
     		   $(this).find("input").val('');
	   	   }
	   	   else if($(this).attr("ref")=='home'){
		   	 $(this).append("<div class='form-group'><input class='form-control' name='home' value='"+text+"'></div>");
		   	if(text.trim()=='未填写')
	     		   $(this).find("input").val('');
	   	   }
	   	});
   }
    
    /* $('#portrait').fileinput({
        language: 'zh',
        uploadUrl : "/upload/portrait",
        autoReplace : true,
        browseClass: "btn btn-default",
        showRemove: false,
        showClose: false,
        showPreview: true,
        maxFileCount : 1,
        maxFileSize: 1024,
        enctype: 'multipart/form-data',
        initialPreview: ['<img src="${user.protraitUrl}" height="200" width="200">'],
   }).on("fileuploaded", function(e, data) {
        var res = data.response;
        //alert(res.success);
        toastr.success("上传成功！");
        setTimeout(function(){location.reload();},1000);
    }) */
    $("#upload").bind("click",function(){
    	console.log("caonia");
    	$image.cropper('getCroppedCanvas').toBlob(function (blob) {
      	  var formData = new FormData();
      	  formData.append('myFile', blob);
      	  $.ajax('/upload/portrait', {
      	    method: "POST",
      	    data: formData,
      	    processData: false,
      	    contentType: false,
      	    success: function () {
      	    	toastr.success("上传成功！");
      	    },
      	    error: function () {
      	    	toastr.error("上传失败！");
      	    }
      	  });
      	});
    })
	</script>
</body>

</html>
