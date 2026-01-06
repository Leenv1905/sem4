<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 1/5/2026
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.t2406e.product.model.*" %>

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
                    <a class="item"
                       href="<%= request.getContextPath() %>/auth?action=logout">Logout</a>
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

        <%
            Map<Order, List<OrderItem>> orderMap =
                    (Map<Order, List<OrderItem>>) request.getAttribute("orderMap");

            if (orderMap == null || orderMap.isEmpty()) {
        %>
        <div class="ui info message">
            You have no orders yet.
        </div>
        <%
        } else {
            for (Map.Entry<Order, List<OrderItem>> entry : orderMap.entrySet()) {
                Order order = entry.getKey();
                List<OrderItem> items = entry.getValue();
        %>

        <!-- ===== ORDER ===== -->
        <div class="ui segment">
            <h4 class="ui dividing header">
                Order Code:
                <span class="ui blue label">ORD-<%= order.getId() %></span>
                <span class="ui right floated label"><%= order.getStatus() %></span>
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
                <%
                    for (OrderItem item : items) {
                        Product p = (Product) request.getAttribute(
                                "product_" + item.getProductId()
                        );
                %>
                <tr>
                    <td>
                        <img src="<%= p != null ? p.getImage() : "" %>"
                             class="ui rounded image product-img">
                    </td>
                    <td><%= p != null ? p.getName() : "Unknown" %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>$<%= item.getPrice() %></td>
                </tr>
                <%
                    }
                %>
                </tbody>

                <tfoot>
                <tr>
                    <th colspan="3" class="right aligned">Total</th>
                    <th>$<%= order.getTotalPrice() %></th>
                </tr>
                </tfoot>
            </table>

            <% if ("NEW".equals(order.getStatus())) { %>
            <form method="post"
                  action="<%= request.getContextPath() %>/orders"
                  onsubmit="return confirm('Are you sure you want to cancel this order?');">
                <input type="hidden" name="orderId" value="<%= order.getId() %>">
                <button class="ui red button">Cancel Order</button>
            </form>
            <% } %>
        </div>

        <%
                }
            }
        %>

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


