<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 1/6/2026
  Time: 12:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.t2406e.product.model.*" %>

<html>
<head>
    <title>Admin – Orders</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">

    <style>
        body { background: #f5f6fa; }
        .main-container { margin-top: 80px; width: 90%; }
        .product-img {
            width: 50px;
            height: 40px;
            object-fit: cover;
        }
    </style>
</head>
<%--<style>--%>
<%--    .left-sidebar {--%>
<%--        position: fixed;--%>
<%--        top: 60px;--%>
<%--        left: 0;--%>
<%--        width: 220px;--%>
<%--        height: 100%;--%>
<%--        background: #1b1c1d;--%>
<%--        padding-top: 10px;--%>
<%--        z-index: 1000;--%>
<%--    }--%>

<%--    .left-sidebar .ui.vertical.menu {--%>
<%--        width: 100%;--%>
<%--        border: none;--%>
<%--        box-shadow: none;--%>
<%--        background: transparent;--%>
<%--    }--%>

<%--    .left-sidebar .ui.vertical.menu .item {--%>
<%--        color: white !important;--%>
<%--        font-size: 16px;--%>
<%--        padding: 15px 20px !important;--%>
<%--    }--%>

<%--    .left-sidebar .ui.vertical.menu .item:hover {--%>
<%--        background: #27292a !important;--%>
<%--    }--%>

<%--    /* Đẩy nội dung sang phải */--%>
<%--    .main-content {--%>
<%--        margin-left: 240px !important;--%>
<%--    }--%>

<%--</style>--%>

<body>

<!-- ================= APP BAR ================= -->
<div class="ui fixed inverted menu">
    <div class="ui container">
        <a href="<%= request.getContextPath() %>/" class="header item">
            🛠 Home
        </a>

        <a href="<%= request.getContextPath() %>/product"
           class="item active">Products</a>

        <a href="<%= request.getContextPath() %>/admin/orders"
           class="item">Orders</a>

        <a href="#"
           class="item">Users</a>

        <div class="right menu">
            <div class="ui simple dropdown item">
                <i class="user icon"></i> Admin
                <i class="dropdown icon"></i>
                <div class="menu">
                    <a class="item"
                       href="<%= request.getContextPath() %>/auth?action=logout">
                        Logout
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- ================= SIDEBAR ================= -->
<%--<div class="left-sidebar">--%>
<%--    <div class="ui vertical menu">--%>
<%--        <a href="<%= request.getContextPath() %>/product" class="item">--%>
<%--            <i class="boxes icon"></i> Products--%>
<%--        </a>--%>

<%--        <a href="<%= request.getContextPath() %>/admin/orders" class="item">--%>
<%--            <i class="shopping bag icon"></i> Orders--%>
<%--        </a>--%>

<%--        <a href="<%= request.getContextPath() %>/admin/users" class="item">--%>
<%--            <i class="users icon"></i> Users--%>
<%--        </a>--%>
<%--    </div>--%>
<%--</div>--%>



<!-- ===== MAIN ===== -->

<div class="ui container main-content">

    <h2 class="ui header">
        <i class="clipboard list icon"></i>
        <div class="content">All Orders</div>
    </h2>

    <%
        Map<Order, List<OrderItem>> orderMap =
                (Map<Order, List<OrderItem>>) request.getAttribute("orderMap");

        if (orderMap == null || orderMap.isEmpty()) {
    %>
    <div class="ui info message">No orders found.</div>
    <%
    } else {
        for (Map.Entry<Order, List<OrderItem>> entry : orderMap.entrySet()) {
            Order order = entry.getKey();
            List<OrderItem> items = entry.getValue();
    %>

    <div class="ui segment">
        <h4 class="ui dividing header">
            Order #<%= order.getId() %>
            <span class="ui label"><%= order.getStatus() %></span>
            <span class="ui right floated label">
                User ID: <%= order.getUserId() %>
            </span>
        </h4>

        <table class="ui celled table">
            <thead>
            <tr>
                <th>Image</th>
                <th>Product</th>
                <th>Qty</th>
                <th>Price</th>
            </tr>
            </thead>

            <tbody>
            <%
                for (OrderItem item : items) {
                    Product p = (Product) request.getAttribute(
                            "product_" + item.getProductId());
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

        <form method="post"
              action="<%= request.getContextPath() %>/admin/orders"
              onsubmit="return confirm('Delete this order and restore stock?');">
            <input type="hidden" name="orderId" value="<%= order.getId() %>">
            <button class="ui red button">Delete Order</button>
        </form>
    </div>

    <%
            }
        }
    %>

</div>

<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
</body>
</html>

