<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<base href="${pageContext.servletContext.contextPath}/">
<header class="shadow-sm sticky-top bg-white">
	<style>
.navbar-brand:hover {
	opacity: 0.7;
	color: white;
}

.navbar-brand {
	text-decoration: none;
	color: white;
}

.link:hover {
	opacity: 0.7;
	color: white;
	text-decoration: none;
}

.link{
	color: white;
	text-decoration: none;
	font-size: 1.1rem;
}

.bg-white {
	background-color: #333366 !important;
}
</style>
	<div class="container-xl">
		<nav class="navbar navbar-expand mb-4">
			<div class="container-fluid">
				<a class="navbar-brand" href="admin/user.htm">Trang chủ</a>

				<div class="collapse navbar-collapse justify-content-end"
					id="navbarSupportedContent">
					<a class="link" href="admin/changePass.htm"> Đổi mật khẩu </a>
					<a class="link" href="logout.htm">Đăng xuất</a>
				</div>
			</div>
		</nav>
	</div>
	
	
</header>