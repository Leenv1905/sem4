<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>JSP - Hello World</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1><%= "Home!" %>--%>
<%--</h1>--%>
<%--<br/>--%>
<%--<a href="home">Hello Servlet</a>--%>
<%--<br/>--%>
<%--<a href="product">HOME</a>--%>
<%--<br/>--%>
<%--<a href="cart">CART</a>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Shop - Home</title>

    <!-- Semantic UI CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">

    <style>
        body {
            background: linear-gradient(135deg, #667eea, #764ba2);
        }
        .main-container {
            margin-top: 80px;
            width: 85%;
        }
        .slider-wrapper {
            max-width: 700px;
            margin: 0 auto 40px;
        }
        .product-image {
            height: 150px;
            object-fit: cover;
        }
        footer {
            margin-top: 50px;
            padding: 30px 0;
            background: #1b1c1d;
            color: #fff;
        }
    </style>
</head>

<body>

<!-- ===== APP BAR ===== -->
<div class="ui fixed inverted menu">
    <div class="ui container">
        <a href="<%= request.getContextPath() %>/" class="header item">
            🛒 MyShop
        </a>
        <a href="<%= request.getContextPath() %>/" class="item">Home</a>
        <a href="<%= request.getContextPath() %>/product" class="item">Products</a>
        <a href="#" class="item">Contact</a>

        <div class="right menu">

            <!-- CART ICON -->
            <a href="<%= request.getContextPath() %>/cart"
               class="item">
                <i class="shopping cart icon"></i>
                Cart
            </a>

            <!-- ACCOUNT DROPDOWN -->
            <div class="ui simple dropdown item">
                <i class="user icon"></i> Account
                <i class="dropdown icon"></i>
                <div class="menu">
                    <a class="item" href="#">Login</a>
                    <a class="item" href="#">Register</a>
                    <div class="divider"></div>
                    <a class="item" href="orders">My Setting</a>
                    <a class="item" href="#">Logout</a>
                </div>
            </div>

        </div>

    </div>
</div>

<!-- ===== MAIN CONTENT ===== -->
<div class="ui fluid container main-container">

    <!-- ===== BANNER ===== -->
    <div class="ui raised segment" style="padding:0; max-width:1200px; max-height: 500px; margin:0 auto 40px;">
        <img src="https://picsum.photos/1200/700"
             alt="Main Banner"
             class="ui fluid rounded image">
    </div>

    <!-- ===== PRODUCT GRID ===== -->
    <div class="ui raised very padded segment">
        <h2 class="ui center aligned header">Featured Products</h2>

        <div class="ui four column grid">

            <%-- MOCK 24 PRODUCTS --%>
            <%
                for (int i = 1; i <= 24; i++) {
            %>
            <div class="column">
                <div class="ui card">
                    <div class="image">
                        <img src="https://picsum.photos/300/200?random=<%= i %>"
                             class="product-image">
                    </div>
                    <div class="content">
                        <div class="header">Product <%= i %></div>
                        <div class="meta">
                            <span class="price">$<%= 10 * i %></span>
                        </div>
                        <div class="description">
                            This is a demo product description.
                        </div>
                    </div>
                    <div class="extra content">
                        <button class="ui blue mini button">
                            <i class="shopping cart icon"></i> Add to Cart
                        </button>
                        <button class="ui teal mini button">View</button>
                    </div>
                </div>
            </div>
            <% } %>

        </div>

        <!-- ===== PAGINATION ===== -->
        <div class="ui center aligned basic segment">
            <div class="ui pagination menu">
                <a class="item">«</a>
                <a class="item active">1</a>
                <a class="item">2</a>
                <a class="item">3</a>
                <a class="item">»</a>
            </div>
        </div>
    </div>
</div>

<!-- ===== FOOTER ===== -->
<footer>
    <div class="ui container center aligned">
        <p>© 2026 MyShop. All rights reserved.</p>
        <p>Email: support@myshop.com | Phone: 0123 456 789</p>
    </div>
</footer>

<!-- Semantic UI JS -->
<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>

</body>
</html>
