<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<base href="${pageContext.servletContext.contextPath}/">

<link href="<c:url value='/resources/themify-icons/themify-icons.css' />" rel="stylesheet">
<header>
<style>

@charset "ISO-8859-1";
@import url('https://fonts.googleapis.com/css?family=Numans');



html{
    font-family: "Roboto Condensed", sans-serif;
}



.clear{
    clear:both;
}


#header{
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    height: 90px;
    background-color: #fff;
    z-index: 10;
    box-shadow: 0 0 10px rgba(0,0,0,0.3);
}

#nav li{
    display: inline-block;
    position: relative;
}

#nav .logo1{
    height:80px;
}
#nav .logo{
	padding:0 0 0 0;
}

#nav  li  a{
    color: rgba(102,102,102,.85);
    text-transform: uppercase;
    display: inline-block;
    line-height: 30px;
    padding: 20px 24px;
    font-weight: 700;
    transition: box-shadow .4s, color .3s;
}

#nav li a{   
    text-decoration: none;
  
}

#nav{
    display: inline-block;
    padding-left:138px;
}


#nav >li:hover >a {
    color: #1395cf;
    background-color: #fff;
    box-shadow: 0 3px #1395cf;
   
}
#subnav >li:hover>a{
 color: #1395cf;
    background-color: #fff;
    box-shadow: 0 3px #1395cf;
}



#nav,#subnav .subnav a{
    color: #000;
    padding: 0 16px;
    line-height: 38px;
    display: block;

}


#nav,#subnav  .subnav li {
    display: block;
}


#nav .head-search{
    color:#fff;
    display: block;
    
}


.header_cart-icon{
    font-size: 25px;
}
.header-user{
    font-size: 22px;
}
</style>

<div id="header">
           
            <ul id="nav" style="justify-content:space-between; display:flex; align-items:center; ">
              
            	<li style="margin-left:130px;">
                	<a class="logo" href="home.htm">	
	                	<img class="logo1" src="<c:url value="/resources/images/220525182822-242794Logo2.gif"/>">
	                </a>	                          		            	
            	</li>    
             <!--     <li style="margin-left:500px">
            	<form class="d-inline-flex" action="nologin/home.htm" method="post"  >
						<input name="searchInput" id="searchInput" class="form-control me-2" style="width:500px;margin-top:20px;right:0px;" type="text"
							placeholder="Search" aria-label="Search" required ">
						<button name="btnsearch" id="btnseach"
							class="btn btn-outline-success" style="margin-top:20px;right:0px;" type="submit" >Search</button>
					</form>
            	</li>  -->
            	<li>
            		<ul id="subnav">
            			<li>
            		<a class="link ti-user" href="user/profile.htm">Thông tin</a>
            	</li>
            	<li>
            		<a class="link ti-shopping-cart" href="cart.htm"> Giỏ hàng</a>
            	</li>
            	<li>
            		<a class="link ti-notepad" href="user/order.htm"> Đơn hàng</a>
            	</li>
            	<li>
            		<a class="link ti-shift-right" href="logout.htm"> Đăng xuất</a>
            	</li>
            		</ul>
            	</li>
            	

    
            </ul>        
</div>
</header>
