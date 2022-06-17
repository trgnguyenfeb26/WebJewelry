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
		 <div class="container">  
		      <h2>Phản hồi người dùng</h2> 
		       <form action="/JEWELRY6/admin/support/${sp.getId()}.htm?saveEmail" method ="post"  class="form-horizontal"> 
			      <fieldset>  
			            <p>Gửi từ : bandotrangsuc@gmail.com</p>
			            <p>Đến : ${sp.getAccount().getEmail()}</p>
			            <p>Tiêu đề</p>
			            <input name ="subject" placeholder="" class="form-control" type="text" >
			            <br>
			            <p>Nội dung</p>
			            <div class="form-floating">
							<textarea class="form-control" placeholder="Leave a comment 
							here" id="description" name ="body" rows="5" style="height: 200px ; with :200px"></textarea>
				  		</div> 
			            <br>
			            <div class="col-md-12"> 
			              <button type="submit" class="btn btn-md btn-danger pull-right">Phản hồi </button> 
			            </div>
			            <br>
			            <p>${message}</p>
			      </fieldset>  
		    </form> 
		    <br>
		    <table class="table table-hover"">
				<thead>
					<tr>
						<th scope="col">ID Khách hàng </th>
						<th scope="col">Họ Tên </th>
						<th scope="col">Nội dung</th>
						<th scope="col">Thời gian</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${sp.getAccount().getId()}</td>
						<td>${sp.getAccount().getFullName()}</td>
						<td>${sp.getContent()}</td>
						<td><fmt:formatDate pattern="HH:mm - dd/MM/yyyy "
								value="${sp.getDate()}" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		</div>
	</div>
</body>
<script>
CKEDITOR.replace('description');
</script>
</html>