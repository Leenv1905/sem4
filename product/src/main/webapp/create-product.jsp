<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 12/24/2025
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
    <!-- Semantic UI CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">
</head>
<body style="background: linear-gradient(135deg, #667eea, #764ba2);">

<div class="ui container" style="margin-top: 80px; max-width: 500px;">
    <div class="ui raised very padded segment">
        <h2 class="ui center aligned header">Create Product</h2>

        <!-- POST: gửi dữ liệu -->
        <form class="ui form" action="${pageContext.request.contextPath}/product?action=create" method="post">

            <div class="field">
                <label>Product Name</label>
                <input type="text" name="name" required>
            </div>

            <div class="field">
                <label>Price</label>
                <input type="number" name="price" step="0.01" required>
            </div>

            <div class="field">
                <label>Description</label>
                <input type="text" name="description" required>
            </div>

            <div class="field">
                <label>Quantity</label>
                <input type="number" name="quantity" required>
            </div>

            <div class="field">
                <label>Image URL</label>
                <input type="text" name="image" placeholder="images/iphone15.jpg" required>
            </div>

            <div class="ui two buttons">
                <button type="submit" class="ui primary button">Create</button>
                <a href="${pageContext.request.contextPath}/product" class="ui button">Back to List</a>
            </div>
        </form>
    </div>
</div>

<!-- Semantic UI JS (optional cho các component động) -->
<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
</body>
</html>

