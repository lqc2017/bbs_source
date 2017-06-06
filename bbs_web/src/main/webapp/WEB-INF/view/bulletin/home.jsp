<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帖子</title>
<link href="/css/question-home.css" rel="stylesheet">
<%@ include file="../source.jsp"%>
<script src="/js/commons/paginator.js"></script>
</head>

<body>
	<!-- 导航头 -->
	<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid nav-container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
				<span class="sr-only">切换导航</span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">codeground</a>
		</div>
		<div class="collapse navbar-collapse" id="example-navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/bulletin/home">论坛</a></li>
				<li><a href="/q">问答</a></li>
			</ul>
			<ul class="nav navbar-nav float-right">
				<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication==null }">
					<li><a class="sign" href="javascript:;" data-toggle="modal" data-target="#signModal">登陆/注册</a></li>
				</c:if>
				
				<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication!=null }">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img src="${currentUser.protraitUrl}" height="20" width="20"><b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><p class="p-cur-user">当前用户：${currentUser.nickname}</p></li>
							<li class="divider"></li>
							
							<li><a href="/u/${currentUser.id}?active=10">个人信息</a></li>
							<li><a href="/u/post">我的帖子</a></li>
							<li><a href="/myq">我的问题</a></li>
							<li class="divider"></li>
							
							<li><a href="/u/${currentUser.id}?active=20">消息
							<c:if test="${messageCnt!=0}">
								<span class="badge">新</span>
							</c:if></a></li>
							<li class="divider"></li>
							
							<li><a href="/logout">登出</a></li>
							<li class="divider"></li>
							
							<li><a href="/u/${currentUser.id}?active=30">设置</a></li>
						</ul></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>
	<!-- 导航尾 -->
	<div style="display: inline">
	<div style="float: left; width: 900px">
	<input id="totalPages" type="hidden" value="${pageInfo.getPages()}" />
	<input id="currentPn" type="hidden" value="${pageInfo.getPageNum()}" />
	<div class="search-panel">
		<div class="search-div">
			<input size="10" placeholder="关键字">
			<button name="search" class="btn btn-default btn-sm">search</button> 
		</div>
	</div>
	<div class="select-panel">
		<ol class="breadcrumb">
			<c:if test="${postSo.status==null}">
				<li class="active">全部</li>
				<li><a name="wait-btn" href="javascript:;">未结帖</a></li>
				<li><a name="solved-btn" href="javascript:;">已结帖</a></li>
			</c:if>
			<c:if test="${postSo.status==0}">
				<li><a name="all-btn" href="javascript:;">全部</a></li>
				<li class="active">未结贴</li>
				<li><a name="solved-btn" href="javascript:;">已结帖</a></li>
			</c:if>
			<c:if test="${postSo.status==1}">
				<li><a name="all-btn" href="javascript:;">全部</a></li>
				<li><a name="wait-btn" href="javascript:;">未结贴</a></li>
				<li class="active">已结帖</li>
			</c:if>
		</ol>
		<c:if test="${s_tag!=null}">
			<div  class="select-item">
				<a name="tag-btn" class="tag" href="javascript:;"><span
					class="glyphicon glyphicon-tag"></span>${s_tag.name}<span
					class="glyphicon glyphicon-remove remove"></span></a>
			</div>
		</c:if>
		<c:if test="${postSo.keywords!=null && postSo.keywords!=''}">
			<div class="select-item">
				<a name="keywords-btn" class="tag" href="javascript:;"><span
					class="glyphicon glyphicon-search"></span>${postSo.keywords}<span
					class="glyphicon glyphicon-remove remove"></span></a>
			</div>
		</c:if>
		<div class="select-item-right">
			<div class="btn-group">
				<a name="time-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${postSo.order==null||postSo.order==0}">active</c:if>">时间</a>
				<a name="hot-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${postSo.order==1}">active</c:if>">热度</a>
			</div>
			
			<div class="btn-group">
				<a name="day-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${postSo.timeFrame==10}">active</c:if>">本日</a>
				<a name="week-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${postSo.timeFrame==20}">active</c:if>">本周</a>
				<a name="month-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${postSo.timeFrame==30}">active</c:if>">本月</a>
				<a name="alltime-btn" href="javascript:;"
					class="btn btn-default btn-xs <c:if test="${postSo.timeFrame==null}">active</c:if>">全部</a>
			</div>
		</div>
	</div>

	<div class="main">
	<c:if test="${pageInfo.getList().size()==0}"><hr/>找不到相关帖子</c:if>
	<c:forEach items="${pageInfo.getList()}" var="post">
		<div class="question-list">
				<div class="attr">
					浏览
					<div class="number">
						<c:if test="${post.views<=999}">${post.views}</c:if>
						<c:if test="${post.views>999}">999+</c:if>
					</div>
				</div>
				<div class="attr" <c:if test="${post.status==1}">style="background-color:#d4cece;"</c:if>>
					回复
					<div class="number">
						<div class="number">
							<c:if test="${post.replys<=999}">${post.replys}</c:if>
							<c:if test="${post.replys>999}">999+</c:if>
						</div>
					</div>
				</div>
				<div class="info">
				<div class="title"><a href="/bulletin/home/${post.id}">${post.title}</a></div>
				<div class="status"><c:if test="${post.status==1}"><span class="label label-default">已解决</span></c:if>
				<c:if test="${post.acceptId!=0}"><span class="label label-success">已打赏(${post.rewards})</span></c:if>
				<c:if test="${post.acceptId==0}"><span class="label label-info">未打赏(${post.rewards})</span></c:if>
				</div>
					<div class="tag">
					<c:set var="key" value="${post.id.toString()}"/>
					<c:forEach items="${tagsMap[key]}" var="tag">
						<a data="${tag.index}" href="javascript:;">${tag.name}</a>
						</c:forEach>
					</div>
					<div class="message">
					<div class="username">
						<a href="/u/${post.postUser}">${post.name}</a>
					</div>
					<div>|</div>
					<div class="createtime">${format.getTime(post.postTime)}</div>
				</div>
			</div>
		</div>
		</c:forEach>
		<div id="paginator" style="width: 30%; margin: 10px auto;"></div>
	</div>

	<form id="search_form" class="form-inline" method="get" action="/bulletin/home">
		<input name="keywords" type="hidden" value="${postSo.keywords}" />
		<input name="tagIndex" type="hidden" value="${postSo.tagIndex}" />
		<input name="timeFrame" type="hidden" value="${postSo.timeFrame}" />
		<input name="status" type="hidden" value="${postSo.status}" />
		<input name="order" type="hidden" value="${postSo.order}" />
		<input name="pn" type="hidden" value="${pageInfo.getPageNum()}" />
	</form>
	</div>
	<%-- <div style="display: inline">
		<div style="float: left; width: 900px">
		<table class="table table-striped">
		<caption><div align="center">帖子（按照发布时间时间排序）</div></caption>
			<thead>
				<tr>
					<td>帖子名称</td>
					<td>发布者</td>
					<td>发布时间</td>
					<td>最后回复时间</td>
					<td>悬赏分</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="post" items="${post}">
					<tr>
						<td><a href="/bulletin/home/${post.id}">${post.title}  </a><c:if test="${post.status==1}"><span class="label label-default">已结帖</span></c:if></td>
						<td><a href="/u/${post.postUser}">${post.name}</a></td>
						<td>${post.postTime}</td>
						<td>${post.updateTime}</td>
						<c:if test="${post.acceptId!=0}">
						<td>${post.rewards}  <span class="label label-success">已打赏</span></td>
						</c:if>
						<c:if test="${post.acceptId==0}">
						<td>${post.rewards}</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</div> --%>
		<div style="float: right; width: 300px">
		<table class="table table-condensed">
		<caption><div align="center">排行榜（只显示前十名）</div></caption>
			<thead>
				<tr>
					<td><div align="center">用户昵称</div></td>
					<td><div align="center">用户积分</div></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rankuser" items="${rankuser}" varStatus="vs" >
				<c:if test="${vs.count<=10}">
					<tr>
						<td><div align="center"><a href="/u/${rankuser.id}">${rankuser.nickname}</a></div></td>
						<td><div align="center">${rankuser.score}</div></td>
					</tr>
				</c:if>
				</c:forEach>
			</tbody>
		</table>
		<div align="center">
		<a href="/bulletin/post" type="button" class="btn btn-success btn-sm">我要发帖</a>
		</div>
		</div>
	</div>
	<!-- 注册登录模态框头 -->
	<div class="modal fade" id="signModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 400px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="modalLabel">登陆/注册</h4>
				</div>
				<div class="modal-body">
					<ul id="myTab1" class="nav nav-tabs">
						<li class="active"><a href="#signIn" data-toggle="tab">登陆</a></li>
						<li><a href="#signUp" data-toggle="tab">注册</a></li>
					</ul>
					<div id="myTabContent1" class="tab-content">
						<div class="tab-pane fade active in" id="signIn">
							<div style="height: 235px; padding: 0 30px 30px 30px;">
								<iframe frameborder="no" style="scrolling: auto; width: 100%; height: 100%;" src="/loginPage"></iframe>
							</div>
						</div>
						<div class="tab-pane fade" id="signUp">
							<div style="margin: 10px 30px 20px 30px">
								<form name="register_form" action="/signUp" method="post"
									class="form-horizontal">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<div class='form-group'>
										<label class="label-control">用户名</label> <input type="text" class="form-control" name="username">
									</div>
									<div class='form-group'>
										<label class="label-control">密码</label> <input type="password" class="form-control" name="password">
									</div>
									<div class='form-group'>
										<label class="label-control">确认密码</label> 
										<input type="password" class="form-control" name="confirm" placeholder="请再次输入密码">
									</div>
									<div class='form-group'>
										<label class="label-control">昵称</label> 
										<input type="text" class="form-control" name="nickname">
									</div>
									<div class='form-group'>
										<label class="label-control">性别</label> 
										<label class="checkbox-inline"><input type="radio" name="sex" value="0" checked>男</label> 
										<label class="checkbox-inline"><input type="radio" name="sex" value="1">女</label>
									</div>
									<div class='form-group'>
										<input type="submit" class="btn btn-block btn-success form-control" value="注册">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 注册登录模态框尾 -->
	
	<script src="/js/commons/registerFormValidateInit.js"></script>
	<script type="text/javascript">
		$(".tag").find("a").bind("click",function(){
			var tagIndex = $(this).attr("data");
			$("input[name='tagIndex']").val(tagIndex);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='all-btn']").bind("click",function(){
			$("input[name='status']").val("");
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='wait-btn']").bind("click",function(){
			$("input[name='status']").val(0);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='solved-btn']").bind("click",function(){
			$("input[name='status']").val(1);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='time-btn']").bind("click",function(){
			$("input[name='order']").val(0);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='hot-btn']").bind("click",function(){
			$("input[name='order']").val(1);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='day-btn']").bind("click",function(){
			$("input[name='timeFrame']").val(10);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='week-btn']").bind("click",function(){
			$("input[name='timeFrame']").val(20);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='month-btn']").bind("click",function(){
			$("input[name='timeFrame']").val(30);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='alltime-btn']").bind("click",function(){
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("button[name='search']").bind("click",function(){
			$("input[name='timeFrame']").val("");
			$("input[name='tagIndex']").val("");
			$("input[name='status']").val("");
			$("input[name='order']").val("0");
			
			var keywords = $(this).parent("div").children("input").val();
			$("input[name='keywords']").val(keywords);
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='tag-btn']").bind("click",function(){
			$("input[name='tagIndex']").val("");
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
		
		$("a[name='keywords-btn']").bind("click",function(){
			$("input[name='keywords']").val("");
			$("input[name='pn']").val(1);
			$("#search_form").submit();
		})
	</script>
</body>

</html>