<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath}/">
<title>Chi tiết đơn hàng</title>
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
	
	<div class="container">
		<h5 style="padding-top: 30px">Chi tiết đơn hàng</h5>
		<table class="table mb-3">
			<thead>
				<tr>
					<th scope="col">Sản phẩm</th>
					<th scope="col">Giá</th>
					<th scope="col">Số lượng</th>
					<th scope="col">Giảm giá</th>
					<th scope="col">Tạm tính</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sp" items="${order.cartItems}">
					<tr>
						<td>${sp.product.name}</td>
						<td><f:formatNumber value="${sp.product.price}"
								type="currency" currencySymbol="đ" maxFractionDigits="0" /></td>
						<td>${sp.quantity}</td>
						<td><c:if test="${sp.product.discount > 0}">
							${sp.product.discount}%
						</c:if></td>
						<td><f:formatNumber value="${sp.getPrice()}"
								type="currency" currencySymbol="đ" maxFractionDigits="0" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<p style="font-size: 20px; font-weight: bold">
			Tổng tiền:
			<f:formatNumber value="${order.getTotalPriceOrder()}" type="currency"
				currencySymbol="đ" maxFractionDigits="0" />
		</p>

		<c:if test="${order.status == 0 }">

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-danger" data-bs-toggle="modal"
				data-bs-target="#huy">Hủy đơn hàng</button>

			<!-- Modal -->
			<div class="modal fade" id="huy" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="huy">Xác nhận</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">Bạn chắc chắc muốn hủy đơn này?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Hủy</button>
							<a
								href="${pageContext.request.contextPath}/admin/order/${order.id}/huy.htm"
								type="button" href class="btn btn-primary">Đồng ý </a>
						</div>
					</div>
				</div>
			</div>

		
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#danggiaohang">Giao hàng</button>

				<!-- Modal -->
				<div class="modal fade" id="danggiaohang" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="danggiaohang">Xác nhận</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">Bạn chắc chắc muốn giao đơn hàng
								này?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Hủy</button>
								<a
									href="${pageContext.request.contextPath}/admin/order/${order.id}/danggiaohang.htm"
									type="button" href class="btn btn-primary">Đồng ý </a>
							</div>
						</div>
					</div>
				</div>
	
		</c:if>	

		<c:if test="${order.status == 2 }">
		
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-success" data-bs-toggle="modal"
					data-bs-target="#giaohang">Đã giao hàng</button>
		


			<!-- Modal -->
			<div class="modal fade" id="giaohang" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="giaohang">Xác nhận</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						
						<div class="modal-body">Bạn đã giao hàng thành công cho
							khách hàng?</div>
					
					
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Hủy</button>
							<a
								href="${pageContext.request.contextPath}/admin/order/${order.id}/giaohang.htm"
								type="button" href class="btn btn-primary">Đồng ý </a>
						</div>
					</div>
				</div>
			</div>



		</c:if>
	</div>
	</div>
	</div>
</body>
</html>