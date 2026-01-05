<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 1/5/2026
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Account</title>

    <!-- Semantic UI -->
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
        .product-img {
            width: 60px;
            height: 45px;
            object-fit: cover;
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

        <div class="right menu">
            <div class="ui simple dropdown item">
                <i class="user icon"></i> My Account
                <i class="dropdown icon"></i>
                <div class="menu">
                    <a class="item active">Orders</a>
                    <a class="item">My Setting</a>
                    <a class="item">Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ===== MAIN CONTENT ===== -->
<div class="ui fluid container main-container">

    <div class="ui raised very padded segment">
        <h2 class="ui header">
            <i class="shopping bag icon"></i>
            <div class="content">
                My Orders
                <div class="sub header">Order history of your account</div>
            </div>
        </h2>

        <!-- ===== ORDER #1 ===== -->
        <div class="ui segment">
            <h4 class="ui dividing header">
                Order Code: <span class="ui blue label">ORD-1001</span>
            </h4>

            <table class="ui celled table">
                <thead>
                <tr>
                    <th>Image</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <img src="https://picsum.photos/100/80?1"
                             class="ui rounded image product-img">
                    </td>
                    <td>iPhone 15 Pro</td>
                    <td>1</td>
                    <td>$1200</td>
                </tr>
                <tr>
                    <td>
                        <img src="https://picsum.photos/100/80?2"
                             class="ui rounded image product-img">
                    </td>
                    <td>AirPods Pro</td>
                    <td>2</td>
                    <td>$500</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <th colspan="3" class="right aligned">Total</th>
                    <th>$1700</th>
                </tr>
                </tfoot>
            </table>
        </div>

        <!-- ===== ORDER #2 ===== -->
        <div class="ui segment">
            <h4 class="ui dividing header">
                Order Code: <span class="ui green label">ORD-1002</span>
            </h4>

            <table class="ui celled table">
                <thead>
                <tr>
                    <th>Image</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <img src="https://picsum.photos/100/80?3"
                             class="ui rounded image product-img">
                    </td>
                    <td>MacBook Pro M3</td>
                    <td>1</td>
                    <td>$2400</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <th colspan="3" class="right aligned">Total</th>
                    <th>$2400</th>
                </tr>
                </tfoot>
            </table>
        </div>

    </div>
</div>

<!-- ===== FOOTER ===== -->
<footer style="margin-top:50px; background:#1b1c1d; padding:30px; color:white;">
    <div class="ui container center aligned">
        © 2026 MyShop – My Account
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
</body>
</html>

