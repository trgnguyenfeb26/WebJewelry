<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<base href="${pageContext.servletContext.contextPath }/">

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thêm sản phẩm</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
	<form:form action="admin/product/add.htm" modelAttribute="product" method="POST" enctype="multipart/form-data">
<div class="d-flex">
	<tag:slide />

		<div style="flex: 1" class="p-3">
		<tag:headerAdmin />
	
	<div class="container-xl">
		<h2 class="py-2">Thêm sản phẩm</h2>

	
			<div class="mb-3">
				<label class="form-label" for="fullName">Tên</label>
				<form:input path="name" name="name" id="name" class="form-control"
					required="required"  />
			</div>
			
			<div class="mb-3">
				<label class="form-label" for="unit">Chất liệu</label>
				<form:input path="unit" name="unit" id="unit" class="form-control"
					required="required"  />
			</div>

	  	<div class="mb-3">
	  			<label class="form-label" for="unit">Ảnh</label>
				<input name="file" id="file" class="form-control"
					required="required" type="file" />
			</div>
			
			<div class="mb-3">
				<label class="form-label" for="price">Giá</label>
				<form:input path="price" name="price" type="number" id="price" class="form-control"
					required="required"  min="1"/>
			</div>
			
			<div class="mb-3">
				<label class="form-label" for="discount">Giảm giá (%)</label>
				<form:input path="discount" name="discount" type="number" id="discount" class="form-control"/>
			</div>
			
			<div class="mb-3">
				<label class="form-label" for="quantity">Số lượng tồn</label>
				<form:input path="quantity" name="quantity" type="number" id="quantity" class="form-control"
					required="required" />
			</div>
			
			<div class="mb-3">
				<label class="form-label" for="unit">Mô tả</label>
				<form:input path="content" name="content" id="content" class="form-control"
					required="required"  />
			</div>
			

			
			<div class="mb-3">
				<label class="form-label" for="fullName">Nhóm sản phẩm</label>
				<form:select path="category.id" items="${categories}" itemValue="id"
					id="categoryId" itemLabel="name" class="form-select"
					required="required" />
			</div>

			<button class="btn btn-primary">Thêm</button>
		</div>
	</div>
	</div>
		</form:form>
	
	<script>
		
	</script>
</body>
</html>

