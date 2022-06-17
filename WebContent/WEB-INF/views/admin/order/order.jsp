<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Danh sách đơn hàng</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.ckeditor.com/ckeditor5/29.2.0/classic/ckeditor.js">
	src = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity = "sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin = "anonymous">
</script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<style>
.link {
	padding: 12px;
	text-decoration: none;
	transition: .15s;
}

.link:hover {
	text-decoration: underline;
}
.pagnext{
    line-height: 20.5px!important;

}
tag {
	min-width: 100px;
}

td, th {
	border: 0px solid #dddddd;
	text-align: center;
	padding: 8px;
}

table {
	margin-left: auto;
	margin-right: auto;
}

</style>
</head>
<body>
	<div class="d-flex">
		<tag:slide />
		
		<div style="flex: 1" class="p-3">
		<tag:headerAdmin />
			<div class="d-flex align-items-center justify-content-between">
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="admin/order.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
				<h2 class="my-2">Quản lí đơn hàng</h2>
					<div class="d-flex flex-row justify-content-between" >
				<div>

					<form class="d-inline-flex" action="admin/order.htm" method="post" >
						<input name="searchInput" id="searchInput" class="form-control me-2" type="date"
							placeholder="Search" aria-label="Search" required>
						<button name="btnsearch" id="btnseach"
							class="btn btn-outline-success" type="submit" >Search</button>
					</form>
				</div>
				 <div>
				</div>  



			</div>
			</div>
			<table class="table table-bordered  table-hover">
				<thead>
					<tr>
						<th scope="col">Mã đơn hàng</th>
						<th scope="col">Ngày đặt</th>
						<th scope="col">Ngày giao/ hủy</th>
						<th scope="col">Tổng tiền</th>
						<th scope="col">Khách hàng</th>
						<th scope="col">Địa chỉ nhận</th>
						<th scope="col">Số điện thoại</th>
						<th scope="col">Trạng thái đơn hàng</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dh" items="${pagedListHolder.pageList}">
						<tr>
							<td>${dh.id}</td>
							<td><f:formatDate value="${dh.buyDate}" pattern="dd-MM-yyyy"/>
							</td>
								<td><f:formatDate value="${dh.deliveryCancelDay}" pattern="dd-MM-yyyy"/>
							</td>
						
							
							<td><f:formatNumber value="${dh.getTotalPriceOrder()}"
									type="currency" currencySymbol="đ" maxFractionDigits="0" /></td>
									<td>${dh.user.getFullName()}</td>
							<td style="width: 154px">${dh.deliveryAddress }</td>
							<td>${ dh.user.getPhone()}</td>
							<td><c:if test="${dh.status == 0}">Chưa xử lý</c:if> <c:if
									test="${dh.status == 1}">Đã giao</c:if> <c:if
									test="${dh.status == -1}">Đã hủy</c:if> <c:if
									test="${dh.status == 2}">Đang giao</c:if></td>
							
							<td style=" width: 105px"><a href="admin/order/detail/${dh.id}.htm"
								class="btn btn-info" style="max-width: 90px;max-height: 60px; font-size: unset;">Chi tiết</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tag:pagingOrder pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
	</div>
</body>
</html>