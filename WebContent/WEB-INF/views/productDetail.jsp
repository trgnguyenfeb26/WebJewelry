<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>Trang chủ</title>
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
    <c:if test="${u != null}">
              <tag:header />
     </c:if> <c:if test="${u == null}">
              <tag:headerNoLogin />
     </c:if>
		<section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                             <img class="product__details__pic__item--large"  width="400" height="400"
									src="<c:url value="/resources/images/${pd.getImage()}"/>">
                        </div>
                        <div>
            
                            <img width="150" height="150"
                                src="<c:url value="/resources/images/${pd.getImage()}"/>">
                            <img width="150" height="150"
                                src="<c:url value="/resources/images/${pd.getImage()}"/>">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__text">
                        <h3>${pd.getName() }</h3>
                       
                        <div class="product__details__price"><f:formatNumber
										value="${pd.price * (100 - pd.discount) / 100 }"
										type="currency" currencySymbol="VND" maxFractionDigits="0" /></div>
             
                        
                        
                        <c:if test="${pd.getQuantity() > 0}">
                           <a href="cart/add.htm?productId=${pd.id}" class="buy-btn">Mua ngay</a>
                          		<a href="login.htm" class"buy-btn">Mua ngay</a>
                        </c:if>
                        <ul>
                            <li><b>Danh mục</b> <span>${pd.getCategory().getName()}</span></li>
                            <li><b>Giá tiền</b> <span>
                                <f:formatNumber
										value="${pd.price * (100 - pd.discount) / 100 }"
										type="currency" currencySymbol="vnd" maxFractionDigits="0" />
                             </span></li>
                            <li><b>Giá cũ</b> <span><f:formatNumber value="${pd.getPrice() }"/> vnd</span></li>
                            <li><b>Đã giảm</b> <span>${pd.getDiscount() } %</span></li>
                             <li><b>Chất liệu</b> <span>${pd.getUnit() }</span></li>
                            <li><b>Vận chuyển</b> <span>3 ngày <samp>Miễn phí giao hàng</samp></span></li>
                            
                        </ul>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                    
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab"
                                    aria-selected="false">Thông tin</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab"
                                    aria-selected="false">Nhận xét</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            
                            <div class="tab-pane" id="tabs-2" role="tabpanel">
                                <div class="product__details__tab__desc">
                                
                                    <p>${pd.getContent() }</p>
                                </div>
                            </div>
                            <div class="tab-pane" id="tabs-3" role="tabpanel">
                                <div class="product__details__tab__desc">
                                    <div class="comment-wrapper">
                                        <div class="panel panel-info">
                                            <div class="panel-body">
                                              <form action="/JEWELRY6/productDetail/cmt/${pd.getId()}.htm" method ="post" >
                                                <textarea class="form-control" name ="comment" placeholder="Viết bình luận" rows="3"></textarea>
                                              
                                                <br>
                                                <button type="submit" class="btn btn-info pull-right">Đăng</button>
                                                 </form>
                                                <div class="clearfix"></div>
                                                <hr>
                                                <ul class="media-list">
                                                <c:forEach var="c" items="${cmt}" varStatus ="index">
                                                 <li class="media">
                                      
                                                        <div class="media-body">
                                                            <span class="text-muted pull-right">
                                                                <small class="text-muted"><fmt:formatDate pattern="HH:mm - dd/MM/yyyy"
																	value="${c.getDate() }" /></small>
                                                            </span>
                                                            <c:if test="${ar.get(index.index) == 0}">
                                                            <strong class="text-success">${c.getAccount().getFullName()} (đã mua)</strong>
                                                            </c:if>  <c:if test="${ar.get(index.index) == 1}">
                                                            <strong class="text-success">${c.getAccount().getFullName()} (chưa mua) </strong>
                                                            </c:if>
                                                            <p>${c.getContent() }</p>
                                                            <i>${c.getFeedback()}</i>
                                                        
                                                        </div>
                                                    </li>
                                                   </c:forEach>
                                                  
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>   
	<tag:footer />
</body>

<script src="/resources/js/jquery-3.3.1.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/jquery.nice-select.min.js"></script>
    <script src="/resources/js/jquery-ui.min.js"></script>
    <script src="/resources/js/jquery.slicknav.js"></script>
    <script src="/resources/js/mixitup.min.js"></script>
    <script src="/resources/js/owl.carousel.min.js"></script>
    <script src="/resources/js/main.js"></script>
</html> --%>