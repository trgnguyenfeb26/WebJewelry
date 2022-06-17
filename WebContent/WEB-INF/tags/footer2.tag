<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<base href="${pageContext.servletContext.contextPath}/">
    <script src="https://kit.fontawesome.com/b7741ac505.js" crossorigin="anonymous"></script>
<style>
footer {
    color: #666666;
    font-size: 15px;
    font-family: 'Quattrocento Sans', sans-serif;
    line-height: 1.80857;
}

footer a {
    color: #1f1f1f;
    text-decoration: none !important;
    outline: none !important;
    -webkit-transition: all .3s ease-in-out;
    -moz-transition: all .3s ease-in-out;
    -ms-transition: all .3s ease-in-out;
    -o-transition: all .3s ease-in-out;
    transition: all .3s ease-in-out;
}

footer h1,
footer h2,
footer h3,
footer h4,
footer h5,
footer h6 {
    letter-spacing: 0;
    font-weight: normal;
    position: relative;
    padding: 0 0 10px 0;
    font-weight: normal;
    line-height: 120% !important;
    color: #1f1f1f;
    margin: 0
}

footer h1 {
    font-size: 24px
}

footer h2 {
    font-size: 22px
}

footer h3 {
    font-size: 18px
}

footer h4 {
    font-size: 16px
}

footer h5 {
    font-size: 14px
}

footer h6 {
    font-size: 13px
}

footer h1 a,
footer h2 a,
footer h3 a,
footer h4 a,
footer h5 a,
footer h6 a {
    color: #212121;
    text-decoration: none!important;
    opacity: 1
}

footer h1 a:hover,
footer h2 a:hover,
footer h3 a:hover,
footer h4 a:hover,
footer h5 a:hover,
footer h6 a:hover {
    opacity: .8
}

footer a {
    color: #1f1f1f;
    text-decoration: none;
    outline: none;
}

footer a {
    text-decoration: none !important;
    outline: none !important;
    -webkit-transition: all .3s ease-in-out;
    -moz-transition: all .3s ease-in-out;
    -ms-transition: all .3s ease-in-out;
    -o-transition: all .3s ease-in-out;
    transition: all .3s ease-in-out;
}

footer ul,
footer li,
footer ol {
    list-style: none;
    margin: 0px;
    padding: 0px;
}

.okkk {
    display: flex;
    justify-content: center;
}

.footer-main {
    padding: 70px 0px;
    background: #202020;
}

.footer-widget h4 {
    color: #ffffff;
    font-size: 16px;
    text-transform: uppercase;
    letter-spacing: 2px;
    margin-bottom: 20px;
    position: relative;
    font-weight: 700;
}

.footer-widget h4::before {
    border-bottom: 3px solid #d33b33;
    content: "";
    height: 3px;
    width: 100%;
    position: absolute;
    bottom: 3px;
    left: 0px;
}

.footer-widget p {
    color: #cccccc;
    font-weight: 400;
    font-size: 14px;
    padding-bottom: 20px;
}

.footer-widget ul {
    display: inline-block;
}

.footer-widget ul li {
    float: left;
    margin-right: 5px;
}

.footer-widget ul li a {
    color: #ffffff;
    display: inline-block;
    width: 36px;
    height: 36px;
    border: 2px solid #ffffff;
    text-align: center;
    line-height: 32px;
}

.footer-widget ul li a:hover {
    color: #d33b33;
    border-color: #d33b33;
}

.footer-link {}

.footer-link h4 {
    color: #ffffff;
    font-size: 16px;
    text-transform: uppercase;
    letter-spacing: 2px;
    margin-bottom: 20px;
    position: relative;
    font-weight: 700;
}

.footer-link h4::before {
    border-bottom: 3px solid #d33b33;
    content: "";
    height: 3px;
    width: 100%;
    position: absolute;
bottom: 3px;
    left: 0px;
}

.footer-link ul li {
    margin-right: 5px;
}

.footer-link ul li a {
    color: #ffffff;
    text-align: left;
    display: block;
    line-height: 32px;
    position: relative;
    padding-left: 15px;
    transition: all .3s ease-in-out;
    -webkit-transition: all .3s ease-in-out;
    -moz-transition: all .3s ease-in-out;
    -o-transition: all .3s ease-in-out;
    -ms-transition: all .3s ease-in-out;
}

.footer-link ul li a:hover::before {
    left: 5px;
}

.footer-link ul li a:hover {
    color: #d33b33;
    border-color: #d33b33;
    padding-left: 20px;
}

.footer-link-contact h4 {
    color: #ffffff;
    font-size: 16px;
    text-transform: uppercase;
    letter-spacing: 2px;
    margin-bottom: 20px;
    position: relative;
    font-weight: 700;
}

.footer-link-contact h4::before {
    border-bottom: 3px solid #d33b33;
    content: "";
    height: 3px;
    width: 100%;
    position: absolute;
    bottom: 3px;
    left: 0px;
}

.footer-link-contact ul li {
    margin-bottom: 12px;
}

.footer-link-contact ul li i {
    position: absolute;
    left: 0;
    top: 5px;
    padding-right: 6px;
}

.footer-link-contact ul li p {
    padding-left: 25px;
    color: #cccccc;
    position: relative;
}

.footer-link-contact ul li p {
    font-size: 16px;
    color: #cccccc;
    font-weight: 300;
    padding-right: 16px;
    line-height: 24px;
}

.footer-link-contact ul li p a {
    color: #cccccc;
}

.footer-link-contact ul li p a:hover {
    color: #d33b33;
}


/* ------------ copy right*/

.footer-copyright {
    background: #2c2c2c;
    padding: 20px 0px;
    position: relative;
}

.footer-copyright p {
    text-align: center;
    color: #ffffff;
    font-size: 16px;
}

.footer-copyright p a {
    color: #ffffff;
}

.footer-copyright p a:hover {
    color: #d33b33;
}
</style>
    <footer>
        <div class="footer-main">
            <div class="container" style="font-family: Arial, Helvetica, sans-serif;">
                <div class="row okkk">
                    <div class="col-lg-4 col-md-12 col-sm-12">
                        <div class="footer-widget">
                            <h4>VỀ CHÚNG TÔI</h4>
                            <p>TẬP ĐOÀN VÀNG BẠC ĐÁ QUÝ, tiền thân là Công ty Phát triển Công nghệ và Thương mại TTD được thành lập ngày 28/07/1994.<br> Vào thời điểm những năm 90 của thế kỉ 20, Công ty TTD chính là doanh nghiệp tiên phong trong hoạt
                                động chuyên sâu về khai thác đá quý,<br> chế tác cắt mài và xuất khẩu đá quý ra thị trường quốc tế, vốn là một lĩnh vực vô cùng mới mẻ tại Việt Nam. TTD trở thành Công ty đầu tiên<br> đã đưa ra thị trường quốc tế sản phẩm
                                đá Ruby sao Việt Nam với thương hiệu Việt Nam Star Ruby - VSR. Kể từ đó, Công ty được mệnh danh<br> là ông hoàng Ruby sao của thế giới, đưa Việt Nam được khắc họa như một điểm sáng trên bản đồ Đá quý quốc tế.
                            </p>

                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12 col-sm-12">
                        <div class="footer-link">
                            <h4>THÔNG TIN</h4>
                            <ul style="list-style: none;">
                                <li><a href="#">Giới thiệu</a></li>
                                <li><a href="#">Chăm sóc khách hàng</a></li>
                                <li><a href="#">Đội ngũ quản trị</a></li>
                                <li><a href="#">Chính sách hậu mãi</a></li>
                                <li><a href="#">Bảo mật thông tin, bảo vệ khách hàng</a></li>
                            </ul>
                            <div class="footer-widget">

                                <ul>
                                    <li><a href="#"><i class="fab fa-facebook" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-twitter" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-linkedin" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-google-plus" aria-hidden="true"></i></a></li>
<li><a href="#"><i class="fa fa-rss" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-pinterest-p" aria-hidden="true"></i></a></li>
                                    <li><a href="#"><i class="fab fa-whatsapp" aria-hidden="true"></i></a></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                    <div class="col-lg-4 col-md-12 col-sm-12">
                        <div class="footer-link-contact">
                            <h4>Contact Us</h4>
                            <ul>
                                <li>
                                    <p><i class="fas fa-map-marker-alt"></i>Địa chỉ: 97, man thiện, <br>phường tăng nhơn phú, <br> Tp.Thủ Đức, Tp. Hồ Chí Minh </p>
                                </li>
                                <li>
                                    <p><i class="fas fa-phone-square"></i>Điện thoại: <a href="tel: 0866730867">+1-888 705 770</a></p>
                                </li>
                                <li>
                                    <p><i class="fas fa-envelope"></i>Email: <a href="support.htm">trangsucphongthuy@gmail.com</a></p>
                                </li>
                               
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>




</body>

</html>