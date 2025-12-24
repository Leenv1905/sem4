<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 12/24/2025
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.t2406e.product.model.Product" %>

<%
  Product product = (Product) request.getAttribute("product");
%>

<html>
<head>
  <title>Edit Product</title>
  <!-- Semantic UI CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">
</head>
<body style="background: linear-gradient(135deg, #667eea, #764ba2);">

<div class="ui container" style="margin-top: 80px; max-width: 500px;">
  <div class="ui raised very padded segment">
    <h2 class="ui center aligned header">Edit Product</h2>

    <!-- POST: update product -->
    <form class="ui form" action="<%= request.getContextPath() %>/product?action=edit" method="post">

      <!-- Hidden ID -->
      <input type="hidden" name="id" value="<%= product.getId() %>">

      <div class="field">
        <label>Product Name</label>
        <input type="text" name="name" value="<%= product.getName() %>" required>
      </div>

      <div class="field">
        <label>Price</label>
        <input type="number" name="price" step="0.01" value="<%= product.getPrice() %>" required>
      </div>

      <div class="field">
        <label>Description</label>
        <input type="text" name="description" value="<%= product.getDescription() %>" required>
      </div>

      <div class="field">
        <label>Quantity</label>
        <input type="number" name="quantity" value="<%= product.getQuantity() %>" required>
      </div>

      <div class="field">
        <label>Image URL</label>
        <input type="text" name="image" value="<%= product.getImage() %>" required>
      </div>

      <div class="ui two buttons">
        <button type="submit" class="ui green button">Update</button>
        <a href="<%= request.getContextPath() %>/product" class="ui button">Back to List</a>
      </div>
    </form>
  </div>
</div>

<!-- Semantic UI JS (optional cho các component động) -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
</body>
</html>

