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
<title>Thống kê</title>
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
		   <h2>Thống kê sản phẩm</h2>
         <form:form action="/JEWELRY6/admin/stats.htm?stats" method ="post" modelAttribute ="stats" >
           <label  style =" margin-left: 35% !important;">Sắp xếp theo</label>
           <form:select path="type" class="form-control"  style ="width: 170px !important;display: inline-block !important; margin-left: 10px !important;">
							<form:options itemLabel="label" itemValue="id"
								items="${cmbType}" />
						  </form:select>
			<label style =" margin-left: 20px !important;">Năm</label>
	        <form:select path="year" class="form-control"  style ="width: 100px !important;display: inline-block !important; margin-left: 10px !important;">
							<form:options itemLabel="label" itemValue="id"
								items="${cmbYear}" />
						  </form:select>
			 <label style =" margin-left: 20px !important;">Tháng</label>
			 <form:select path="month" class="form-control" style ="width: 100px !important;display: inline-block !important; margin-left: 10px !important;">
						  <form:options itemLabel="label" itemValue="id"
								items="${cmbMonth}" />
						  </form:select>
			<button class="btn btn-primary" style ="inline-block !important; margin-left: 10px">Lọc </button> 
		</form:form>
		
	    <br>
		<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Số thứ tự</th>
						<th scope="col">Tên sản phẩm</th>
						<th scope="col">Đã bán </th>
						<th scope="col">Doanh thu</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="t" items="${pagedListHolder.pageList}" varStatus ="index">
						<tr>

							<td>${pagedListHolder.getPage()*pagedListHolder.getPageSize() +index.index+1}</td>
							<td>${pd.get(index.index)}</td>
							<td>${t[1]}</td>
							<td><f:formatNumber value="${t[2]}"/> đ</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tg:paging2 pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
		</div>
	</div>
</body>
</html>