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
<%@ page import="java.util.List" %>
<%@ page import="com.t2406e.product.model.Product" %>
<%
    com.t2406e.product.model.User loginUser =
            (com.t2406e.product.model.User) session.getAttribute("loginUser");
%>
<html>
<head>
    <title>My Shop - Home</title>

    <!-- Semantic UI CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">

    <style>
        body {
            background: linear-gradient(135deg, #fff, #fff);
        }
        .main-container {
            margin-top: 80px;
            width: 85%;
        }
        .product-image {
            height: 150px;
            object-fit: cover;
        }
        footer {
            margin-top: 50px;
            padding: 30px 0;
            background: #7d9fb2;
            color: #fff;
        }
        /* ===== FULL WIDTH BANNER ===== */
        .full-banner {
            width: 100vw;
            height: 500px;
            margin-left: calc(-50vw + 50%);
            margin-top: -10px;
            overflow: hidden;
        }

        .full-banner img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
    </style>
</head>

<body>

<!-- ===== APP BAR ===== -->
<div class="ui fixed inverted menu">
    <div class="ui container">
<%--        <a href="<%= request.getContextPath() %>/" class="header item">--%>
<%--            🛒 MyShop--%>
<%--        </a>--%>
        <a href="<%= request.getContextPath() %>/" class="item">🛒 Home</a>
        <a href="<%= request.getContextPath() %>/product" class="item">Admin</a>
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
                <i class="user icon"></i>
                <%= (loginUser != null ? loginUser.getUsername() : "Account") %>
                <i class="dropdown icon"></i>

                <div class="menu">
                    <% if (loginUser == null) { %>

                    <!-- CHƯA ĐĂNG NHẬP -->
                    <a class="item"
                       href="<%= request.getContextPath() %>/auth?action=login">
                        Login
                    </a>
                    <a class="item"
                       href="<%= request.getContextPath() %>/auth?action=register">
                        Register
                    </a>

                    <% } else { %>

                    <!-- ĐÃ ĐĂNG NHẬP -->
                    <a class="item"
                       href="<%= request.getContextPath() %>/orders">
                        My Orders
                    </a>

                    <div class="divider"></div>

                    <a class="item"
                       href="<%= request.getContextPath() %>/auth?action=logout">
                        Logout
                    </a>

                    <% } %>
                </div>
            </div>

        </div>

    </div>
</div>

<!-- ===== MAIN CONTENT ===== -->
<div class="ui fluid container main-container">

    <!-- ===== BANNER ===== -->
    <div class="full-banner">
        <img src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/ca6cd7150863355.63021c4cc4737.jpg"
<%--        <img src="https://picsum.photos/1920/800"--%>

             alt="Main Banner">
    </div>


    <!-- ===== PRODUCT GRID ===== -->
    <div class="ui raised very padded segment">
        <h2 class="ui center aligned header">All Products</h2>

        <div class="ui four column grid">

            <%
                List<Product> products = (List<Product>) request.getAttribute("products");

                if (products != null && !products.isEmpty()) {
                    for (Product p : products) {
            %>

            <div class="column">
                <div class="ui card">
                    <div class="image">
                        <img src="<%= p.getImage() %>"
                             class="product-image">
                    </div>

                    <div class="content">
                        <div class="header"><%= p.getName() %></div>
                        <div class="meta">
                            <span class="price">$<%= p.getPrice() %></span>
                        </div>
                        <div class="description">
                            <%= p.getDescription() %>
                        </div>
                    </div>

                    <div class="extra content">
                        <% if (p.getQuantity() > 0) { %>
                        <a href="<%= request.getContextPath() %>/cart?action=add&id=<%= p.getId() %>"
                           class="ui blue mini button">
                            <i class="shopping cart icon"></i> Add
                        </a>
                        <% } else { %>
                        <button class="ui disabled mini button">
                            <i class="shopping cart icon"></i> Out
                        </button>
                        <% } %>

                        <a href="<%= request.getContextPath() %>/view-product?action=view&id=<%= p.getId() %>"
                           class="ui teal mini button">
                            View
                        </a>
                    </div>
                </div>
            </div>

            <%
                }
            } else {
            %>
            <div class="ui message">
                No products available.
            </div>
            <%
                }
            %>

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
