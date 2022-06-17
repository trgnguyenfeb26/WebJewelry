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
<title>Đăng ký tài khoản</title>
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
.gradient-custom {
	/* fallback for old browsers */
	background:#99FFFF;
	/* Chrome 10-25, Safari 5.1-6 */
	background: -webkit-linear-gradient(to bottom right, rgba(240, 147, 251, 1),
		rgba(245, 87, 108, 1));
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background: linear-gradient(to bottom right, rgba(240, 147, 251, 1),
		rgba(245, 87, 108, 1))
}

.card-registration .select-input.form-control[readonly]:not([disabled])
	{
	font-size: 1rem;
	line-height: 2.15;
	padding-left: .75em;
	padding-right: .75em;
}
.link {
	padding: 12px;
	text-decoration: none;
	transition: .15s;
}

.card-registration .select-arrow {
	top: 13px;
}
</style>
</head>
<body>
	<div class="header" style="margin:120px;">
		<tag:header2 />
	</div>
	<form:form action="validate.htm" modelAttribute="user">
		<section class="vh-1500 gradient-custom">
			<div class="container py-5 h-1500">
				<div class="row justify-content-center align-items-center h-1500">
					<div class="col-12 col-lg-9 col-xl-7">
						<div class="card shadow-2-strong card-registration"
							style="border-radius: 15px;">
							<div class="card-body p-4 p-md-5">

								<h3 class="mb-4 pb-2 pb-md-0 mb-md-2">Đăng ký tài khoản</h3>
								<p class="mb-4 pb-2 pb-md-0 mb-md-4">Vui lòng điền vào form
									này để đăng ký tài khoản.</p>
								<form>

									<div class="row">
										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="lastName">Họ</label>
												<form:input path="lastname" type="text" id="lastname"
													class="${frmCtrlLastName}" />
												<div class="${fbLastName}">${contentFBLN}</div>
											</div>
										</div>

										<div class="col-md-6 mb-4">
											<div class="form-outline">
												<label class="form-label" for="lastName">Tên</label>
												<form:input path="firstname" type="text" id="firstname"
													class="${frmCtrlFirstName}" />
												<div class="${fbFirstName}">${contentFBFN}</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6 mb-2 d-flex align-items-center">

											<div class="form-outline datepicker w-100">
												<label for="birthdayDate" class="form-label">Ngày
													sinh</label>
												<form:input path="birthday" type="date"
													class="${frmCtrlBD}" id="birthday"
													value="" min="1900-01-01" max="2009-12-31" />
												<div class="${fbBD}">${contentFBBD}</div>
											</div>

										</div>
										<div class="col-md-6 mb-4">

											<label class="form-label" for="lastName">Giới tính</label><br>

											<div class="form-check form-check-inline">


												<form:radiobutton path="sex" class="form-check-input"
													value="0" />
												<label class="form-check-label" for="male">Nam</label>
											</div>

											<div class="form-check form-check-inline">


												<form:radiobutton path="sex" class="form-check-input"
													value="1" />
												<label class="form-check-label" for="female">Nữ</label>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6 mb-4 pb-2">

											<div class="form-outline">
												<label class="form-label" for="emailAddress">Email</label>
												<form:input path="email" type="email" id="email"
													class="${frmCtrlEmail}" />
												<div class="${fbEmail}">${contentFBEmail}</div>
											</div>
										</div>


										<div class="col-md-6 mb-4 pb-2">

											<div class="form-outline">
												<label class="form-label" for="phoneNumber">Số điện
													thoại</label>
												<form:input path="phone" type="tel" id="phoneNumber"
													class="${frmCtrlPhone}" />
												<div class="${fbPhone}">${contentFBPhone}</div>
											</div>
										</div>


									</div>

									<div class="row">
										<div class="col-md-12 mb-4 pb-2">

											<div class="form-outline">
												<label class="form-label" for="address">Địa chỉ</label>
												<form:input path="address" type="text" id="address"
													class="${frmCtrlAdd}" />
												<div class="${fbAdd}">${contentFBAdd}</div>
											</div>

										</div>

									</div>

									<div class="row">
										<div class="col-md-6 mb-2 pb-2">

											<div class="form-outline">
												<label class="form-label" for="psw">Mật khẩu</label>
												<form:input path="password" type="password" id="psw"
													class="${frmCtrlPsw}" />
												<div class="${fbPsw}">${contentFBPsw}</div>
											</div>

										</div>
										<div class="col-md-6 mb-2 pb-2">

											<div class="form-outline">
												<label class="form-label" for="re-psw">Nhập lại mật
													khẩu</label> <input type="password" id="psw_repeat"
													name="psw_repeat" class="${frmCtrlRePsw}">
												<div class="${fbRePsw}">${contentFBRePsw}</div>
											</div>

										</div>
									</div>

									<div class="row">
										<div class="col-md-12 mb-4 pb-2">

											<input type="checkbox" onclick="myFunction()"> <span
												style="font-size: 13px;"> Hiển thị mật khẩu</span><br>

										</div>

									</div>



									

										<img src="${pageContext.request.contextPath}/captcha.htm">
									
									
										<div class="row">
										<div class="col-md-6 mb-4 pb-2">

											<div class="form-outline">
												<label class="form-label" for="address">Vui lòng nhập captcha</label>
												<input name="captcha" type="text" id="captcha"
													class="${frmCtrlCaptcha}" />
												<div class="${fbCaptcha}">${contentFBCaptcha}</div>
											</div>

										</div>

									</div>

									<script>
										function myFunction() {
											var x = document
													.getElementById("psw");
											var y = document
													.getElementById("psw_repeat");
											if (x.type === "password"
													|| y.type == "password") {
												x.type = "text";
												y.type = "text";
											} else {
												x.type = "password";
												y.type = "password";
											}
										}
									</script>

									<div class="d-grid gap-2">
										
												<input class="btn btn-success" type="submit"
														value="Đăng ký" />
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</form:form>
</body>
</html>