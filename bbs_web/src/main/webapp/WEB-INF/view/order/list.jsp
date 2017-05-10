<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单列表</title>
<%@ include file="../source.jsp"%>
<script src="/js/commons/paginator.js"></script>
</head>

<body>
	<input id="totalPages" type="hidden" value="${pageInfo.getPages()}" />
	<input id="currentPn" type="hidden" value="${pageInfo.getPageNum()}" />
	
	<div style="width: 95%; margin: 0 auto 15px auto;">
		<form id="search_form" class="form-inline" method="get">
			<div class="form-group">
				<label for="name">姓名:</label> <input type="text" name="payer"
					class="form-control" value="${so.payer}">
			</div>

			<div class="form-group">
				<label for="status">状态:</label> <select name="status" id="status"
					class="form-control">
					<option value="">无</option>
					<option value="10" <c:if test="${so.status==10}">selected</c:if>>待缴费</option>
					<option value="20" <c:if test="${so.status==20}">selected</c:if>>待确认</option>
					<option value="30" <c:if test="${so.status==30}">selected</c:if>>已确认</option>
				</select>
			</div>

			<div class="form-group">
				<label for="createTime">创建时间：</label>
				<div class="input-group">
					<input type="text" readonly class="form-control form_date"
						name="createTime"
						value="<fmt:formatDate value="${so.createTime}" pattern="yyyy-MM-dd"/>">
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>

			<div class="form-group">
				<label for="payTime">支付时间：</label>
				<div class="input-group">
					<input type="text" readonly class="form-control form_date"
						name="payTime"
						value="<fmt:formatDate value="${so.payTime}" pattern="yyyy-MM-dd"/>">
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>

			<div class="form-group">
				<div class="btn-toolbar">
					<a href="javascript:;" onclick="select()" class="btn btn-default">查询</a>
					<a href="javascript:;" onclick="reset()" class="btn btn-default">清空</a>
				</div>
			</div>

			<input name="cashierId" type="hidden" value="${cashier.id}">
			<!-- 页码 -->
			<input name="pn" type="hidden" value="${pageInfo.getPageNum()}" />
		</form>
	</div>

	<div class="table-responsive" style="width: 95%; margin: 30px auto;">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="13%">姓名</th>
					<th width="12%">应缴金额</th>
					<th width="12%">优惠金额</th>
					<th width="12%">实缴金额</th>
					<th width="13%">创建时间</th>
					<th width="5%">状态</th>
					<th width="13%">支付时间</th>
					<th width="7%">支付方式</th>
					<th width="13%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageInfo.getList()}" var="ho" varStatus="vs">
					<tr>
						<td>${ho.payer}</td>
						<td><fmt:formatNumber type="currency"
								value="${ho.totalPrice}" currencyCode="CNY" /></td>
						<td><fmt:formatNumber type="currency" value="${ho.subsidy}"
								currencyCode="CNY" /></td>
						<td><fmt:formatNumber type="currency" value="${ho.paidPrice}"
								currencyCode="CNY" /></td>
						<td><fmt:formatDate value="${ho.createTime}"
								pattern="yyyy-MM-dd  HH:mm:ss" /></td>
						<td><c:choose>
								<c:when test="${ho.status==10}">待缴费</c:when>
								<c:when test="${ho.status==20}">待确认</c:when>
								<c:when test="${ho.status==30}">已确认</c:when>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${ho.status==20||ho.status==30}">
									<fmt:formatDate value="${ho.payTime}"
										pattern="yyyy-MM-dd  HH:mm:ss" />
								</c:when>
								<c:when test="${ho.status==10}">无</c:when>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${ho.status==20||ho.status==30}">
									<c:choose>
										<c:when test="${ho.payWay==10}">现金</c:when>
										<c:when test="${ho.payWay==20}">银行卡</c:when>
										<c:when test="${ho.payWay==30}">支付宝</c:when>
									</c:choose>
								</c:when>
								<c:when test="${ho.status==10}">无</c:when>
							</c:choose></td>
						<td><div class="btn-toolbar">
								<c:choose>
									<c:when test="${ho.status==10}">
										<button type="button" id="detail_btn"
											class="btn btn-sm btn-primary" hoId="${ho.id}">价目表</button>
									</c:when>
									<c:when test="${ho.status==20}">
										<button type="button" id="detail_btn"
											class="btn btn-sm btn-primary" hoId="${ho.id}">价目表</button>
										<button type="button" id="confirm_btn"
											class="btn btn-sm btn-primary" hoId="${ho.id}">确认</button>
									</c:when>
									<c:when test="${ho.status==30}">
										<button type="button" id="detail_btn"
											class="btn btn-sm btn-primary" hoId="${ho.id}">价目表</button>
										<button type="button" id="cancel_btn"
											class="btn btn-sm btn-primary" hoId="${ho.id}">取消确认</button>
									</c:when>
								</c:choose>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form id="information_form" method="post">
			<input name="orderId" type="hidden"> <input name="cashierId"
				type="hidden" value="${cashierId}">
		</form>
		<div id="paginator" style="width: 23%; margin: 10px auto;"></div>

	</div>

	<script defer type="text/javascript">
		$("button#detail_btn").bind('click', function() {
			var hoId = parseInt($(this).attr("hoId"));
			location.href = "/order/price_list?orderId=" + hoId;
		});

		$("button#confirm_btn")
				.bind(
						'click',
						function() {
							var hoId = parseInt($(this).attr("hoId"));
							var form = $("#search_form");
							form.attr("action", "/order/confirm_order");
							form
									.append("<input name='orderId' type='hidden' value='"+hoId+"'>");
							form.submit();
						});

		$("button#cancel_btn")
				.bind(
						'click',
						function() {
							var hoId = parseInt($(this).attr("hoId"));
							var form = $("#search_form");
							form.attr("action", "/order/cancel_order");
							form
									.append("<input name='orderId' type='hidden' value='"+hoId+"'>");
							form.submit();
						});

		function select() {
			var form = $("#search_form");
			form.attr("action", "/order/list");
			form.submit();
		}

		function reset() {
			var form = $("#search_form");
			form.find("input").val("");
			$("#status").find("option").attr("selected", false);
			$("#status").find("option:first").attr("selected", true);
		}
		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0,
			format : 'yyyy-mm-dd'
		});
	</script>
</body>

</html>