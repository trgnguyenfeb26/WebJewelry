<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@include file="/WEB-INF/views/include/taglib1.jsp"%>

<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lý bình luận</title>
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
		<h2>Quản lý bình luận</h2>
	   <form:form action="/JEWELRY6/admin/rated.htm?search" method ="post" modelAttribute ="rad" >
           <label  style =" margin-left: 55% !important;">Trạng thái </label>
           <form:select path="id" class="form-control"  style ="width: 170px !important;display: inline-block !important; margin-left: 10px !important;">
							<form:options itemLabel="label" itemValue="id"
								items="${cmb}" />
						  </form:select>
			<button class="btn btn-primary" style ="inline-block !important; margin-left: 10px">Lọc </button> 
		</form:form>
	   <form:form action="/JEWELRY6/admin/rated/fb.htm" method ="post" modelAttribute ="st">
		<table class="table table-hover" >
				<thead>
					<tr>
					    <th scope="col">Người dùng</th>
					    <th scope="col">Tên </th>
					    <th scope="col">Tên sản phẩm</th>
					    <th scope="col">Thời gian</th>
						<th scope="col">Nội dung</th>
						<th scope="col">Phản hồi</th>
					</tr>
				</thead>
				<tbody style ="font-size:14px !important">
					<c:forEach var="a" items="${pagedListHolder.pageList}" varStatus ="index">

						<tr>
                            <td>${a.getAccount().getId()}</td>
							<td>${a.getAccount().getFullName()} </td>
							<td>${a.getProduct().getName()}</td>
							<td><fmt:formatDate pattern="HH:mm - dd/MM/yyyy"
									value="${a.getDate()}" /></td>
							<td>${a.getContent()}</td>
							<td>
                                <form:textarea path ="fb${index.index}" class="form-control" rows="3" style ="font-size:14px !important" />
                               
							</td>
						</tr>
					</c:forEach> 
			
					
			
				</tbody>
			</table>
			<button type="submit" class="btn btn-primary" >Lưu</button>
		</form:form>
			<tg:paging2 pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
	</div>
</body>
</html>