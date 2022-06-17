<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<script
	src="https://cdn.ckeditor.com/ckeditor5/29.2.0/classic/ckeditor.js">
	src = "https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity = "sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin = "anonymous" >
</script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
	crossorigin="anonymous"></script>
<meta charset="utf-8">
<title>Xác thực tài khoản</title>
<style type="text/css">
.login-block {
	background: #DE6262; /* fallback for old browsers */
	background: -webkit-linear-gradient(to bottom, #FFB88C, #DE6262);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to bottom, #FFB88C, #DE6262);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	float: left;
	width: 100%;
	padding: 50px 0;
}

.banner-sec {
	/* background: url(https://static.pexels.com/photos/33972/pexels-photo.jpg)
		no-repeat left bottom;
	background-size: cover;
	min-height: 500px;
	border-radius: 0 10px 10px 0;
	padding: 0; */
	
}

.container {
	background: #fff;
	border-radius: 10px;
	box-shadow: 15px 20px 0px rgba(0, 0, 0, 0.1);
}

.carousel-inner {
	border-radius: 0 10px 10px 0;
}

.carousel-caption {
	text-align: left;
	left: 5%;
}

.login-sec {
	padding: 50px 30px;
	position: relative;
}

.login-sec .copy-text {
	position: absolute;
	width: 80%;
	bottom: 20px;
	font-size: 13px;
	text-align: center;
}

.login-sec .copy-text i {
	color: #FEB58A;
}

.login-sec .copy-text a {
	color: #E36262;
}

.login-sec h2 {
	margin-bottom: 30px;
	font-weight: 800;
	font-size: 30px;
	color: #DE6262;
}

.login-sec h2:after {
	content: " ";
	width: 100px;
	height: 5px;
	background: #FEB58A;
	display: block;
	margin-top: 20px;
	border-radius: 3px;
	margin-left: auto;
	margin-right: auto
}

.btn-login {
	background: #DE6262;
	color: #fff;
	font-weight: 600;
}

.banner-text {
	width: 70%;
	position: absolute;
	bottom: 40px;
	padding-left: 20px;
}

.banner-text h2 {
	color: #fff;
	font-weight: 600;
}

.banner-text h2:after {
	content: " ";
	width: 100px;
	height: 5px;
	background: #FFF;
	display: block;
	margin-top: 20px;
	border-radius: 3px;
}

.banner-text p {
	color: #fff;
}
</style>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
</head>
<body>
	<tag:headerNoLogin />
	<form:form action="user/verify.htm" modelAttribute="user" method="POST">

		<section class="login-block">

			<div class="container">

				<div class="row">

					<span style="color: red; font-style: Bold; font-size: 12px;">${error}</span><br>
					<div class="col-md-4 login-sec">
						<h2 class="text-center">TRANG SỨC PHONG THỦY</h2>
						<h5 class="text-center">XÁC THỰC EMAIL</h5>
						<form class="login-form">
						<div class="alert alert-primary" role="alert">
  						Một mã xác thực gồm 8 ký tự vừa được gửi qua email của bạn!
						</div>
							<div class="inner-container">
								<div class="box" id="loginBox">
									<div class="inner-container">
										<div class="box" id="loginBox">

											<form>
												<div class="col-md-12 mb-4">
													<div class="form-outline">
														<label class="form-label" for="email">Mã xác thực</label>
														<input name="verify" type="text" id="verify"
															class="${frmCtrlVerify}" required>
														<div class="${fbVerify}">${contentFBVerify}</div>
													</div>
												</div>
												<div class="d-grid gap-2">
														<br>
													<input class="btn btn-primary" type="submit"
														value="Tiếp tục" /> 
												</div>
												&emsp;&emsp; &emsp;&emsp;
												<p style="color: Green">&emsp;&emsp;&emsp;Chất lượng,
													quý phái!</p>
											</form>
										</div>
									</div>
									<!-- <button type="submit" class="btn btn-login float-right" ><a href="home.htm" >ĐĂNG NHẬP</a></button>  -->
								</div>
							</div>
						</form>
						
					</div>
					<div class="col-md-8 banner-sec">
						<img src="resources/images/220525182822-242794Logo2.gif" style="width: 730px;" />
					</div>

				</div>
		</section>
	</form:form>
</body>
</html>