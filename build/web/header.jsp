
<script>
    function login() {
        window.location.href = "login.jsp";
    }
    function logout() {
        window.location.href = "logout";

    }
       function personalMealPlans() {
        window.location.href = "personalMealPlans";

    }
</script>
<!--PreLoader-->
<div class="loader">
    <div class="loader-inner">
        <div class="circle"></div>
    </div>
</div>
<!--PreLoader Ends-->

<!-- header -->
<div class="top-header-area" id="sticker">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-sm-12 text-center">
                <div class="main-menu-wrap">
                    <!-- logo -->
                    <div class="site-logo">
                        <a href="index.html">
                            <img src="assets/img/logo.png" alt="">
                        </a>
                    </div>
                    <!-- logo -->

                    <!-- menu start -->
                    <nav class="main-menu">
                        <ul>
                            <li class="current-list-item"><a href="homePage">Home</a>
                            </li>
                            <li><a href="about.jsp">About</a></li>                                                            
                            <li><a href="contact.jsp">Contact</a></li>
                            <li><a href="shop">Shop</a> </li>
                                 
                            <c:if test="${sessionScope.user.role == 'Admin'}">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="manageOrders">Manage order</a>

                                </li>
                                <li class="nav-item">

                                    <a class="nav-link active" aria-current="page" href="weeklyMenus">Manage menu</a>

                                </li>
                                <li class="nav-item">

                                    <a class="nav-link active" aria-current="page" href="manageUsers">Manage user</a>
                                </li>

                            </c:if>                         
                            <c:if test="${sessionScope.user.role == 'Customer'}">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#!">Hello, ${sessionScope.user.name}</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="orderHistory">Order</a>
                                </li>
                               
                            </c:if>
                            </li>
                             <c:choose>
                                <c:when test="${sessionScope.user == null}">
                                    <button class="btn btn-outline-primary " onclick="login()">Login</button>
                                       
                                </c:when>

                                <c:otherwise>
                                    <button class="btn btn-outline-primary " onclick="logout()">Logout</button>
                                     <button class="btn btn-outline-primary " onclick="personalMealPlans()">personalMealPlans</button>
                                </c:otherwise>
                            </c:choose>
                            <li>
                                <div class="header-icons">
                                    <a class="shopping-cart" href="cart"><i class="fas fa-shopping-cart"></i>  <span class="badge bg-dark text-white ms-1 rounded-pill">${sessionScope.cart.size()}</span></a> 
                                    <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                                </div>
                            </li>
                           
                        </ul>
                    </nav>
                    <a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                    <div class="mobile-menu"></div>
                   
                    <!-- menu end -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end header -->

<!-- search area -->
<div class="search-area">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <form action="${pageContext.request.contextPath}/search" method="GET">
                    <div class="search-bar">
                        <div class="search-bar-tablecell">
                            <h3>Search For:</h3>
                            <input type="text" name="keyword" placeholder="Keywords">
                            <button type="submit">Search <i class="fas fa-search"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



<!-- end search area -->
