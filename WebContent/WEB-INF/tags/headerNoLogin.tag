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

#nav > li > a{
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
#nav .subnav li:hover a{
    color: #1395cf;
    background-color: #E6E6FA;
}

#nav .subnav{
    display: none;
    position: absolute;
    background-color: #fff;
    list-style: none;
    box-shadow: 0 0 10px rgba(0,0,0,0.3);
    min-width: 220px;
    top:100%;
    padding-inline-start: 0px;
}

#nav .subnav a{
    color: #000;
    padding: 0 16px;
    line-height: 38px;
    display: block;

}


#nav .subnav li {
    display: block;
}

#nav li:hover .subnav{
    display: block;
}

#nav .head-down{
    font-size: 14px;
}

#nav .header-search{
    padding-left:30px;
    
    padding: 20px 24px;
}

#nav .btnsearch:hover .head-search{
   color:red;
    
    
}
#nav .inputSearch{
	border-radius:5px;
    color: rgba(102,102,102,.85);
    padding-left:10px;
    
}
#nav .btnSearch{
    height:30px;
    border-radius:5px;
    background-color:#1395cf;
    cursor: pointer;
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
              
            	<li>
                	<a class="logo" href="nologin/home.htm">	
	                	<img class="logo1" src="<c:url value="/resources/images/220525182822-242794Logo2.gif"/>">
	                </a>	                          		            	
            	</li>    
                 <li style="margin-left:500px">
            	<form class="d-inline-flex" action="nologin/home.htm" method="post"  >
						<input name="searchInput" id="searchInput" class="form-control me-2" style="width:500px;margin-top:20px;right:0px;" type="text"
							placeholder="Search" aria-label="Search" required ">
						<button name="btnsearch" id="btnseach"
							class="btn btn-outline-success" style="margin-top:20px;right:0px;" type="submit" >Search</button>
					</form>
            	</li> 

             	<li style="margin-right:10px">
             		<a href="login.htm">
             			Đăng nhập
             			<i class="header-user ti-user"></i>
             		</a>
             	</li>      
            </ul>        
</div>
</header>
