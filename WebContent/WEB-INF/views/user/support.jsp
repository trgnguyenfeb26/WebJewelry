<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@include file="/WEB-INF/views/include/taglib1.jsp"%>
<link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/elegant-icons.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/nice-select.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/jquery-ui.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/owl.carousel.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/slicknav.min.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Liên hệ </title>
<link href="<c:url value='/resources/themify-icons/themify-icons.css' />" rel="stylesheet">
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
 .comment-wrapper .media-list .media {
    border-bottom:1px solid #888888;
	margin-bottom: 10px;
}
.comment-wrapper p{
	font-size: 14px;
	margin-bottom: 0px;
}
.comment-wrapper i{
	font-size: 14px;
	color: #ec6262;

}

.link {
	padding: 12px;
	text-decoration: none;
	transition: .15s;
}

.link:hover {
	text-decoration: underline;
}

.category {
	text-decoration: none;
	padding: 10px;
	background: #198754;
	color: #fff;
	border-radius: 6px;
	margin: 10px;
}

.product-list {
	grid-template-columns: repeat(4, 1fr);
	gap: 20px;
}

.btn-adjust {
	width: 38px;
	height: 38px;
}

.quantity {
	flex: 1;
	text-align: center;
	font-size: 18px;
	font-weight: 600;
}

.disabled {
  pointer-events: none;
  cursor: default;
}
.card:hover a{
	text-decoration: none;
	transform: translateY(-15px);
}
/* .d-inline-flex{
                  margin-left: 250px;
} */
</style>
</head>

<body>
   <div class="header" style="margin:120px;">
		<tag:headerLogin />
	</div>
    
       <div class="contact-form spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="contact__form__title">
                        <h2>Liên hệ với chúng tôi</h2>
                    </div>
                </div>
            </div>
            <form:form action="/JEWELRY6/support.htm" method ="post" modelAttribute ="sp">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <form:textarea class="form-control" path ="content" placeholder="Liên hệ..." />
                        <button type="submit" class="site-btn">Gửi</button>
                        <p>${mess}</p>
                    </div>
                    
                </div>
                
            </form:form>
        </div>
    </div>
	<tag:footer2 />
</body>

<script src="/resources/js/jquery-3.3.1.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/jquery.nice-select.min.js"></script>
    <script src="/resources/js/jquery-ui.min.js"></script>
    <script src="/resources/js/jquery.slicknav.js"></script>
    <script src="/resources/js/mixitup.min.js"></script>
    <script src="/resources/js/owl.carousel.min.js"></script>
    <script src="/resources/js/main.js"></script>
</html>