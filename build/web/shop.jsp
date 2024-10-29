<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

        <!-- title -->
        <title>Shop</title>

        <!-- favicon -->
        <link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
        <!-- google font -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
        <!-- fontawesome -->
        <link rel="stylesheet" href="assets/css/all.min.css">
        <!-- bootstrap -->
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <!-- owl carousel -->
        <link rel="stylesheet" href="assets/css/owl.carousel.css">
        <!-- magnific popup -->
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <!-- animate css -->
        <link rel="stylesheet" href="assets/css/animate.css">
        <!-- mean menu css -->
        <link rel="stylesheet" href="assets/css/meanmenu.min.css">
        <!-- main style -->
        <link rel="stylesheet" href="assets/css/main.css">
        <!-- responsive -->
        <link rel="stylesheet" href="assets/css/responsive.css">

    </head>
    <body>

           <%@include file="header.jsp" %>
        <!-- breadcrumb-section -->
        <div class="breadcrumb-section breadcrumb-bg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 offset-lg-2 text-center">
                        <div class="breadcrumb-text">
                            <p>Fresh and Organic</p>
                            <h1>Shop</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end breadcrumb section -->

        <!-- products -->
        <div class="product-section mt-150 mb-150">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="product-filters">
                            <ul>
                                <li class="${selectedCategory == -1 ? 'active' : ''}" data-filter="*">
                                    <a href="${pageContext.request.contextPath}/shop">All</a>
                                </li>
                                <c:forEach var="category" items="${categories}">
                                    <li class="${selectedCategory == category.dietaryRequirementID ? 'active' : ''}" data-filter=".${category.name.toLowerCase()}">
                                        <a href="${pageContext.request.contextPath}/shop?category=${category.dietaryRequirementID}">${category.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                        <div class="row product-lists">
                            <c:forEach var="menu" items="${menus}">
                                <div class="col-lg-4 col-md-6 text-center ${menu.dietaryRequirements.name.toLowerCase()}">
                                    <div class="single-product-item">
                                        <div class="product-image">
                                            <a href="detail?menuID=${menu.menuID}"><img src="${menu.imageUrl}" alt="${menu.menuName}"></a>
                                        </div>
                                        <h3>${menu.menuName}</h3>
                                        <p class="product-price"><span>${menu.dietaryRequirements.name}</span> ${menu.price}$ </p>
                                        <form action="${pageContext.request.contextPath}/cart" method="post">
                                            <input type="hidden" name="menuID" value="${menu.menuID}">
                                            <button type="submit" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="row">
                            <div class="col-lg-12 text-center">
                                <div class="pagination-wrap">
                                    <ul>
                                        <c:if test="${currentPage > 1}">
                                            <li><a href="${pageContext.request.contextPath}/shop?page=${currentPage - 1}&category=${selectedCategory}">Prev</a></li>
                                            </c:if>
                                            <c:forEach var="i" begin="1" end="${totalPages}">
                                            <li><a href="${pageContext.request.contextPath}/shop?page=${i}&category=${selectedCategory}" class="${i == currentPage ? 'active' : ''}">${i}</a></li>
                                            </c:forEach>
                                            <c:if test="${currentPage < totalPages}">
                                            <li><a href="${pageContext.request.contextPath}/shop?page=${currentPage + 1}&category=${selectedCategory}">Next</a></li>
                                            </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end products -->
        <!-- logo carousel -->
        <div class="logo-carousel-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="logo-carousel-inner">
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/1.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/2.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/3.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/4.png" alt="">
                            </div>
                            <div class="single-logo-item">
                                <img src="assets/img/company-logos/5.png" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end logo carousel -->

       <%@include file="footer.jsp" %>

        <!-- jquery -->
        <script src="assets/js/jquery-1.11.3.min.js"></script>
        <!-- bootstrap -->
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <!-- count down -->
        <script src="assets/js/jquery.countdown.js"></script>
        <!-- isotope -->
        <script src="assets/js/jquery.isotope-3.0.6.min.js"></script>
        <!-- waypoints -->
        <script src="assets/js/waypoints.js"></script>
        <!-- owl carousel -->
        <script src="assets/js/owl.carousel.min.js"></script>
        <!-- magnific popup -->
        <script src="assets/js/jquery.magnific-popup.min.js"></script>
        <!-- mean menu -->
        <script src="assets/js/jquery.meanmenu.min.js"></script>
        <!-- sticker js -->
        <script src="assets/js/sticker.js"></script>
        <!-- main js -->
        <script src="assets/js/main.js"></script>

    </body>
</html>