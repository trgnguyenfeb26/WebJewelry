<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">

<base href="${pageContext.servletContext.contextPath }/">
<title>Giỏ hàng</title>
<link href="<c:url value='/resources/themify-icons/themify-icons.css' />" rel="stylesheet">
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
#text {
	color: green;
	text-align: left;
}

#btnmua {
	text-align: right;
	min-width: 100px;
	text-align: center;
}

.btn-adjust {
	width: 38px;
	height: 38px;
}

.quantity {
	text-align: center;
	font-size: 18px;
	font-weight: 600;
	min-width: 50px;
}

.disabled {
  pointer-events: none;
  cursor: default;
}

.link {
	padding: 12px;
	text-decoration: none;
	transition: .15s;
}

.link:hover {
	text-decoration: underline;
}

.category {
	text-decoration: none;
	padding: 10px;
	background: #198754;
	color: #fff;
	border-radius: 6px;
	margin: 10px;
}

.product-list {
	grid-template-columns: repeat(4, 1fr);
	gap: 20px;
}

.btn-adjust {
	width: 38px;
	height: 38px;
}

.quantity {
	flex: 1;
	text-align: center;
	font-size: 18px;
	font-weight: 600;
}

.d-inline-flex{
                  margin-left: 250px;
}
.pagnext{
    line-height: 21px!important;

}
.disabled {
  pointer-events: none;
  cursor: default;
</style>
</head>
<body>
<div class="header" style="margin:120px;">
		<tag:headerLogin />
	</div>
	 <div class="container" >
<div class="bg-light p-10 rounded">
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="cart.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>

	<div class="container">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">SẢN PHẨM</th>
					<th scope="col">SỐ LƯỢNG</th>
					<th scope="col">GIÁ</th>
					<th scope="col">GIẢM GIÁ</th>
					<th scope="col">TẠM TÍNH</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="sp" items="${pagedListHolder.pageList}">
					<tr>
						<td>${sp.product.name}</td>
						<td>
							<div class="d-flex flex-row align-items-center">
								<a href="cart/decreaseQuantity.htm?productId=${sp.product.id }" class="btn btn-primary btn-adjust ${sp.quantity <= 1 ? 'disabled' : '' }">-</a>
								<div class="quantity">${sp.quantity}</div>
								<a href="cart/increaseQuantity.htm?productId=${sp.product.id }" class="btn btn-primary btn-adjust ${sp.quantity >= sp.product.quantity ? 'disabled' : '' }">+</a>
							</div>
						</td>
						<td><f:formatNumber value="${sp.product.price}"
								type="currency" currencySymbol="đ" maxFractionDigits="0" /></td>
						<td><c:if test="${sp.product.discount > 0}">
							${sp.product.discount}%
						</c:if></td>
						<td><f:formatNumber value="${sp.getTamTinh()}"
								type="currency" currencySymbol="đ" maxFractionDigits="0" /></td>
						<td><a href="cart/delete.htm?productId=${sp.product.id }"
							class="btn btn-danger">Xóa</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="d-flex flex-row align-items-center justify-content-between">
		<p style="font-size: 20px; font-weight: bold">
			Tổng tiền:
			<f:formatNumber value="${cart.getTotalPrice()}" type="currency"
				currencySymbol="đ" maxFractionDigits="0" />
		</p>


		<a href="createInvoice.htm" id="btnmua" class="btn btn-outline-success mb-3 ${pagedListHolder.pageList.size() == 0 ? 'disabled' : '' }">MUA</a>
		</div>

		<div>
			<a href="home.htm" class="btn btn-warning"><span
				class="glyphicon glyphicon-chevron-left"></span> Tiếp Tục Mua Hàng>></a>
		</div>
	</div>
	<tag:pagingOrder pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
	</div>
	</div>
	
</body>
</html>