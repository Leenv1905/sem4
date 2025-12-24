<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 12/24/2025
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.t2406e.product.model.Product" %>

<html>
<head>
    <title>Product List</title>
    <!-- Semantic UI CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">
</head>
<body style="background: linear-gradient(135deg, #667eea, #764ba2);">

<div class="ui fluid container" style="margin-top: 60px; width: 80%;">
    <div class="ui raised very padded segment" style="width:100%;">
        <h2 class="ui center aligned header">Product List</h2>

        <div class="ui grid">
            <div class="eight wide column">
                <a href="<%= request.getContextPath() %>/" class="ui button grey">Home</a>
                <a href="<%= request.getContextPath() %>/cart" class="ui button grey">Cart</a>
            </div>
            <div class="eight wide right aligned column">
                <a href="<%= request.getContextPath() %>/product?action=create" class="ui button green">
                    Create Product
                </a>
            </div>
        </div>

        <table class="ui celled striped table">
            <thead>
            <tr>
                <th>#</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Image</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Product> products = (List<Product>) request.getAttribute("products");
                if (products != null && !products.isEmpty()) {
                    int index = 1;
                    for (Product p : products) {
            %>
            <tr>
                <td><%= index++ %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getPrice() %></td>
                <td><%= p.getDescription() %></td>
                <td><%= p.getQuantity() %></td>
                <td>
                    <img src="<%= p.getImage() %>" class="ui small rounded image" style="width:70px;height:50px;">
                </td>
                <td>
                    <a href="<%= request.getContextPath() %>/product?action=view&id=<%= p.getId() %>"
                       class="ui button teal mini">View</a>
                    <a href="<%= request.getContextPath() %>/product?action=edit&id=<%= p.getId() %>"
                       class="ui button yellow mini">Edit</a>
                    <a href="<%= request.getContextPath() %>/product?action=delete&id=<%= p.getId() %>"
                       class="ui button red mini">Del</a>
                    <% if (p.getQuantity() > 0) { %>
                        <a href="<%= request.getContextPath() %>/cart?action=add&id=<%= p.getId() %>"
                        class="ui button blue mini">
                        <i class="shopping cart icon"></i> Add </a>
                    <% } else { %>
                    <button class="ui disabled button mini">
                        <i class="shopping cart icon"></i> Add </button>
                    <% } %>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="7" class="center aligned">No products found</td>
            </tr>
            <%
                }
            %>
<%--            // ĐOạn code hiện thông báo--%>
            <% String message = (String) request.getAttribute("message"); %>
            <% if (message != null) { %>
            <div class="ui positive message">
                <i class="close icon"></i>
                <div class="header">Success</div>
                <p><%= message %></p>
            </div>
            <% } %>

            </tbody>
        </table>
    </div>
</div>

<!-- Semantic UI JS (optional for interactive components)
Bổ xung các tương tác động cho UI
-->
<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
</body>
</html>

