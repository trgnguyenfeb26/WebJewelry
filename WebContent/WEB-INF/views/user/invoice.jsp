<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath }/">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value='/resources/themify-icons/themify-icons.css' />" rel="stylesheet">
<title>Hoá đơn</title>
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
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="<c:url value='/resources/ckeditor/ckeditor.js' />">
	
</script>
<script type="text/javascript"
	src="<c:url value='/resources/ckfinder/ckfinder.js'  />">
	
</script>

<style>
.link {
	padding: 12px;
	text-decoration: none;
	transition: .15s;
}

.link:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="header" style="margin:120px;">
		<tag:headerLogin />
	</div>
	<form:form action="createOrder.htm">
		<div class="container mt-5">
		<div class="bg-light p-5 rounded">
			<div class="d-flex justify-content-center row">
				<div class="col-md-8">
				
						<div class="row">
							<div class="col-md-6">
								<h1 class="text-uppercase">Hoá đơn</h1>
								<div class="billed">
									<span class="font-weight-bold text">Họ tên
										khách hàng:</span><span class="ml-1"><b> ${user.getLastname()} ${user.getFirstname()}</b>
										</span>
								</div>
								<div class="billed">
									<span class="font-weight-bold text">Ngày mua:</span><span
										class="ml-1"><b> ${date}</b> </span>
								</div>
								<!--   <div class="billed"><span class="font-weight-bold text-uppercase">Địa chỉ:</span><span class="ml-1"> ${user.getAddress()}</span></div> -->
								<div class="billed">
									<span class="font-weight-bold text">Số điện
										thoại: </span><span class="ml-1"> <b> ${user.getPhone()}</b></span>
								</div>
								<div class="form-outline">
									<label class="form-label" for="address">Địa chỉ nhận:</label> 
									<input
										name="address" type="text" id="validationDefault01" class="form-control"
										value="${user.getAddress()}"
										required>

								</div>
								<!--     <div class="billed"><span class="font-weight-bold text-uppercase">Order ID:</span><span class="ml-1">#1345345</span></div>-->
							</div>

						</div>
						<div class="mt-3">
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>Sản phẩm</th>
											<th>Số lượng</th>
											<th>Đơn giá</th>
											<th>Giảm giá</th>
											<th>Tạm tính</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="sp" items="${cart.cartItems}">
											<tr>
												<td>${sp.product.name}</td>
												<td>${sp.quantity}</td>

												<td><f:formatNumber value="${sp.product.getPrice()}"
														type="currency" currencySymbol="đ" maxFractionDigits="0" /></td>
												<td><c:if test="${sp.product.discount > 0}">
							${sp.product.discount}%
						</c:if></td>
												<td><f:formatNumber value="${sp.getTamTinh()}"
														type="currency" currencySymbol="đ" maxFractionDigits="0" /></td>
											</tr>
										</c:forEach>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td>Tổng cộng</td>
											<td><f:formatNumber value="${cart.getTotalPrice()}"
													type="currency" currencySymbol="đ" maxFractionDigits="0" /></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col-12">
							<button class="btn btn-primary" type="submit">Đặt hàng</button>
								<a class="btn btn-danger" href="cart.htm">Huỷ</a>
						</div>

					</div>
				</div>
			
			</div>
		</div>
	</form:form>
</body>
</html>