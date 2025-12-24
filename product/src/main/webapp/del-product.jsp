<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 12/24/2025
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.t2406e.product.model.Product" %>

<%
  Product product = (Product) request.getAttribute("product");
%>

<html>
<head>
  <title>Delete Product</title>
  <!-- Semantic UI CSS -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">
</head>
<body style="background: linear-gradient(135deg, #667eea, #764ba2);">

<div class="ui container" style="margin-top: 120px; max-width: 500px;">
  <div class="ui raised very padded segment center aligned">
    <h2 class="ui red header">Delete Product</h2>

    <p>Are you sure you want to delete this product?</p>

    <div class="ui segment left aligned">
      <p><strong>Name:</strong> <%= product.getName() %></p>
      <p><strong>Price:</strong> $<%= product.getPrice() %></p>
      <p><strong>Description:</strong> <%= product.getDescription() %></p>
      <p><strong>Quantity:</strong> <%= product.getQuantity() %></p>
    </div>

    <div class="ui two buttons">
      <button class="ui red button" onclick="$('#confirmModal').modal('show')">Delete</button>
      <a href="<%= request.getContextPath() %>/product" class="ui button">Cancel</a>
    </div>
  </div>
</div>

<!-- ===== MODAL CONFIRM ===== -->
<div id="confirmModal" class="ui modal">
  <div class="header">Confirm Delete</div>
  <div class="content">
    <p>This action cannot be undone.</p>
  </div>
  <div class="actions">
    <form action="<%= request.getContextPath() %>/product?action=delete" method="post" style="margin:0;">
      <input type="hidden" name="id" value="<%= product.getId() %>">
      <button type="submit" class="ui red button">Yes, Delete</button>
    </form>
    <div class="ui cancel button">Cancel</div>
  </div>
</div>

<!-- jQuery + Semantic UI JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
</body>
</html>

