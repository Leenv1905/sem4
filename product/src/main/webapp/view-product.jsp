<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 12/24/2025
  Time: 5:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.t2406e.product.model.Product" %>

<%
    Product p = (Product) request.getAttribute("product");
%>

<html>
<head>
    <title>View Product</title>
    <!-- Semantic UI CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">
</head>
<body style="background: linear-gradient(135deg, #667eea, #764ba2);">

<div class="ui container" style="margin-top: 60px; max-width: 900px;">
    <div class="ui raised very padded segment">

        <!-- Back -->
        <div class="ui basic segment">
            <a href="<%= request.getContextPath() %>/product" class="ui blue link">
                ← Back to Product List
            </a>
        </div>

        <!-- Product detail -->
        <div class="ui stackable grid">
            <!-- Image -->
            <div class="eight wide column">
                <img src="<%= p.getImage() %>" alt="<%= p.getName() %>" class="ui large rounded image">
            </div>

            <!-- Info -->
            <div class="eight wide column">
                <h2 class="ui header"><%= p.getName() %></h2>

                <div class="ui red header" style="margin-top:10px;">
                    $<%= p.getPrice() %>
                </div>

                <div class="ui segment">
                    <p><%= p.getDescription() %></p>
                </div>

                <div class="ui segment">
                    <strong>Quantity:</strong> <%= p.getQuantity() %> items available
                </div>

                <div class="ui buttons">
                    <a href="<%= request.getContextPath() %>/cart" class="ui green button">Buy Now</a>
                    <a href="<%= request.getContextPath() %>/cart?action=add&id=<%= p.getId() %>" class="ui orange button">Add to Cart</a>
<%--                    Nên thêm ở đây logic ẩn nút khi quatity = 0--%>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- Semantic UI JS (optional cho các component động) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
</body>
</html>

