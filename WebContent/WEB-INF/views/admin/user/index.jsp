<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<base href="${pageContext.servletContext.contextPath }/">

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lí người dùng</title>
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
 th,td{
	border: 0px solid #dddddd;
	text-align: center;
	padding: 8px;
}

</style>
</head>

<body>
	<div class="d-flex">
		<tag:slide />

		<div style="flex: 1" class="p-3">
		<tag:headerAdmin />
			<jsp:useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="admin/user.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<div class="d-flex align-items-center justify-content-between">
				<h2 class="my-2">Quản lý người dùng</h2>
					<form class="d-inline-flex" action="admin/user.htm" method="post" >
						<input name="searchInput" id="searchInput" class="form-control me-2" type="search"
							placeholder="Tên người dùng" aria-label="Search" required>
						<button name="btnsearch" id="btnseach"
							class="btn btn-outline-success" type="submit" >Search</button>
					</form>
			</div>
			<table class="table table-bordered  table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Tên</th>
						<th scope="col">Email</th>
						<th scope="col">Địa chỉ</th>
						<th scope="col">Avatar</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${pagedListHolder.pageList}">
						<tr>
							<td>${user.id}</td>
							<td>${user.firstname} ${user.lastname}</td>
							<td>${user.email}</td>
							<td>${user.address}</td>
							<td><img src="resources/images/${user.avatar}"
								style="width: 50px; height: 50px; object-fit: cover" /></td>
							<td>	<form action="admin/user/delete/${user.id}.htm" method="get"
									style="margin-bottom: 0;">
									<button type="submit" class="btn btn-danger">Khóa</button>
								</form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tag:pagingOrder pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
	</div>
	<script>
		
	</script>
</body>
</html>

