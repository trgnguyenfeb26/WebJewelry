<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@include file="/WEB-INF/views/include/taglib1.jsp"%>
<script type="text/javascript" 
	src= "<c:url value='/resources/ckeditor/ckeditor.js'/>">
</script>
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
		    <h2>Nội dung phản ánh</h2>
        <form:form action="/JEWERLY/admin/support.htm?search" method ="post" modelAttribute ="rad" >
           <label  style =" margin-left: 55% !important;">Trạng thái </label>
           <form:select path="id" class="form-control"  style ="width: 170px !important;display: inline-block !important; margin-left: 10px !important;">
							<form:options itemLabel="label" itemValue="id"
								items="${cmb}" />
						  </form:select>
			<button class="btn btn-primary" style ="inline-block !important; margin-left: 10px">Lọc </button> 
		</form:form>
        <br>
		<table class="table table-hover"">
				<thead>
					<tr>
						<th scope="col">ID Khách hàng </th>
						<th scope="col">Họ Tên </th>
						<th scope="col">Nội dung</th>
						<th scope="col">Thời gian</th>
						<th scope="col">Trạng thái</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sp" items="${pagedListHolder.pageList}">

						<tr>
                            <td>${sp.getAccount().getId()}</td>
							<td>${sp.getAccount().getFullName()}</td>
							<td>${sp.getContent()}</td>
							<td><fmt:formatDate pattern="HH:mm - dd/MM/yyyy"
									value="${sp.getDate()}" /></td>
							<td>
							<c:if test="${sp.getFeedback() == true }">
									<p>Đã phản hồi</p>
							</c:if> <c:if test="${sp.getFeedback() == false}">
								<a href="/JEWELRY6/admin/support/${sp.getId()}.htm?feedback"><img
									width="40" height="40"
									src="<c:url value="/resources/icon/email.png"/>"></a>
							</c:if>
							</td>
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