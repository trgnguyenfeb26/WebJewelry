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
<title>Đăng nhập</title>
<style type="text/css">
.login-block {
	background: #99FFFF; /* fallback for old browsers */
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
body{
	background: #DE6262; /* fallback for old browsers */
	background: -webkit-linear-gradient(to bottom, #FFB88C, #DE6262);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to bottom, #FFB88C, #DE6262);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	float: left;
	width: 100%;
	
}
.banner-text p {
	color: #fff;
}
.row{
padding-top: 50x;
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
<div class="header" style="margin:120px;">
		<tag:header2 />
	</div>
	<form:form action="login.htm" modelAttribute="user">
			<div class="container">

				<div class="row">
				<div class="row mb-4 px-3">  
								<a style="text-decoration: none;" href="login.htm?language=en"><img width="30px" height="30px" src="<c:url value='/resources/images/english.jpg' />" > English</a> &nbsp; <a   style="text-decoration: none;" href="login.htm?language=vi"><img width="30px" height="30px" src="<c:url value='/resources/images/vietnam-icon.png' />" >Vietnamese</a> 
							</div>

		<%-- 			<span style="color: red; font-style: Bold; font-size: 12px;">${error}</span><br> --%>
					<div class="col-md-4 login-sec">
						<h2 class="text-center">TRANG SỨC</h2>
						<form class="login-form">
							<c:if test="${not empty logined}">
								<div class="${frmCtrl}">
									<strong>${warning}</strong> ${message}
								</div>
							</c:if>

							<div class="inner-container">
								<div class="box" id="loginBox">
									<div class="inner-container">
										<div class="box" id="loginBox">

											<form>
												<div class="col-md-12 mb-4">
													<div class="form-outline">
														<label class="form-label" for="email">Email</label>
														<form:input path="email" type="text" id="email" value="${email}"
															class="${frmCtrlEmail}" />
														<div class="${fbEmail}">${contentFBEmail}</div>
													</div>
												</div>

												<div class="col-md-12 mb-4">
													<div class="form-outline">
														<label class="form-label" for="psw">
														<!-- Mật khẩu -->
														 <s:message code="login.Password.title" /> 
														</label> <input
															type="password" name="psw" id="psw" class="${frmCtrlPsw}">
														<div class="${fbPsw}">${contentFBPsw}</div>
													</div>
												</div>

												<div class="row col-6 px-3 mb-2">

													<img src="${pageContext.request.contextPath}/captcha.htm">
												</div>


												<div class="col-md-12 mb-0">

													<div class="form-outline">
														<label class="form-label" for="captcha"><s:message code="login.vuilongnhapcapcha.title" /></label> <input name="captcha" type="text"
															id="captcha" class="${frmCtrlCaptcha}" />
														<div class="${fbCaptcha}">${contentFBCaptcha}</div>
													</div>

												</div>
												<div class="col-md-12 mb-4 pb-2">
												</div>
												<div class="d-grid gap-2">

													<input class="btn btn-primary" type="submit"
														value="<s:message code="login.btndangnhap.title" />" /> <a class="btn btn-success"
														href="${pageContext.request.contextPath}/register.htm"
														role="button"><s:message code="login.Dangki.title" /></a>
														
														<a class="btn btn-warning"
														href="${pageContext.request.contextPath}/forgotPass.htm"
														role="button"><s:message code="login.Quenmhatkhau.title" /></a>
												</div>
												&emsp;&emsp; &emsp;&emsp;
												<p style="color: Green">&emsp;&emsp;&emsp; <s:message code="login.khauhieu.title" /></p>
											</form>
										</div>
									</div>
									<!-- <button type="submit" class="btn btn-login float-right" ><a href="home.htm" >ĐĂNG NHẬP</a></button>  -->
								</div>
							</div>
						</form>
						
					</div>
					<div class="col-md-8 banner-sec">
						<img src="resources/images/220525182822-242794Logo2.gif" style="width: 650px;" />
					</div>
		</section>
	</form:form>
</body>
</html>