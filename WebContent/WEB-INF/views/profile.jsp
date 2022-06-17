<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath}/">
<title>Thông tin cá nhân</title>
<link https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css>
<link https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js>
<link https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js>
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

body{
	background: #DE6262; /* fallback for old browsers */
	background: -webkit-linear-gradient(to bottom, #FFB88C, #DE6262);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to bottom, #FFB88C, #DE6262);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	float: left;
	width: 100%;
	
}

.form-control:focus {
    box-shadow: none;
    border-color: #BA68C8
}

.profile-button {
    background: rgb(99, 39, 120);
    box-shadow: none;
    border: none
}

.profile-button:hover {
    background: #682773
}

.profile-button:focus {
    background: #682773;
    box-shadow: none
}

.profile-button:active {
    background: #682773;
    box-shadow: none
}

.back:hover {
    color: #682773;
    cursor: pointer
}


.add-experience:hover {
    background: #BA68C8;
    color: #fff;
    cursor: pointer;
    border: solid 1px #BA68C8
}
img {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 150px;
}

.link {
	padding: 12px;
	text-decoration: none;
	transition: .15s;
}

.link:hover {
	text-decoration: underline;
}
.container {
	background: #fff;
	border-radius: 10px;
	box-shadow: 15px 20px 0px rgba(0, 0, 0, 0.1);
}
</style>
</head>

<body>

<div class="header" style="margin:120px;">
		<tag:headerLogin />
	</div>
<form:form action="user/profile/validate.htm" modelAttribute="userE" method="post" enctype="multipart/form-data">
<div class="container rounded mt-5 mb-2">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-2">
            <img class="mt-5" type="file" src="resources/images/${avatar}">
            <span class="font-weight-bold"></span><span class="text-black-50"></span><span> </span></div>
           

		<input type="file" name="avatar">
		
            
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">Cập nhật thông tin</h4>
                </div>
                <form>
                <div class="row mt-2">
                    <div class="col-md-6">
                    	<label class="labels">Họ</label><input type="text" name="lastname" class="${frmCtrlLastName}" placeholder="" value="${lastname}">
                    	<div class="${fbLastName}">${contentFBLN}</div>
                    	</div>
                    <div class="col-md-6">
                    	<label class="labels">Tên</label><input name="firstname" type="text"  class="${frmCtrlFirstName}" value="${firstname}" placeholder="">
                    	<div class="${fbFirstName}">${contentFBFN}</div>
                    	</div>
                </div>
                <div class="row mt-3">
                    <div class="col-md-12">
                    	<label class="labels">Số điện thoại</label><input name="phone" type="text"  class="${frmCtrlPhone}" placeholder="" value="${phone}">
                    	<div class="${fbPhone}">${contentFBPhone}</div>
                    	</div>
                    <div class="col-md-12">
                    	<label class="labels">Địa chỉ</label><input name="address" type="text" class="${frmCtrlAdd}" placeholder="" value="${address}">
                    	<div class="${fbAdd}">${contentFBAdd}</div>
                    	</div>
                    <div class="col-md-12">
                    	<label class="labels">Email</label>
                    	<input name="email"  type="text" class="form-control" readonly="true" placeholder="" value="${email}">
                    	</div>
               
                	</div>
                
                	<div class="col-md-4 mb-0">
						<label class="form-label" for="lastName">Giới tính</label><br>
						<div class="form-check form-check-inline">
							<form:radiobutton path="sex" class="form-check-input" value="0" />
							<label class="form-check-label" for="male">Nam</label>
						</div>
						<div class="form-check form-check-inline">
							<form:radiobutton path="sex" class="form-check-input" value="1" />
								<label class="form-check-label" for="female">Nữ</label>
						</div>
				</div>
                
                <div class="row mt-3">
                    <div class="col-md-6">
                    	<label class="labels">Ngày sinh</label><input name="birthday" type="date" class="${frmCtrlBD}" min="1900-01-01" max="2009-12-31" placeholder="" value="${birthday}">
                    	<div class="${fbBD}">${contentFBBD}</div>
                    	</div>
                   
                </div>
                </form>
                <div class="mt-5 text-center">
                	<input class="btn btn-primary" type="submit" value="Lưu profile" />
                	<a class="btn btn-primary" href="user/changePass.htm">Đổi mật khẩu</a>
                 </div>
            </div>
        </div>
    </div>
</div>
</form:form>
</body>
</html>