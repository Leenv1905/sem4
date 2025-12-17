<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 12/17/2025
  Time: 11:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 1100px;
            margin: 60px auto;
            background: #fff;
            border-radius: 10px;
            padding: 25px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .top-actions {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
        }

        .btn {
            padding: 8px 14px;
            border-radius: 6px;
            text-decoration: none;
            color: #fff;
            font-size: 14px;
        }

        .btn-home { background: #6c757d; }
        .btn-create { background: #28a745; }
        .btn-view { background: #17a2b8; }
        .btn-edit { background: #ffc107; color: #000; }
        .btn-delete { background: #dc3545; }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }

        th {
            background: #f4f6fb;
        }

        img {
            width: 70px;
            height: 50px;
            object-fit: cover;
            border-radius: 4px;
        }

        .pagination {
            margin-top: 20px;
            text-align: center;
        }

        .pagination a {
            display: inline-block;
            padding: 6px 12px;
            margin: 0 4px;
            background: #667eea;
            color: #fff;
            border-radius: 4px;
            text-decoration: none;
        }

        .pagination a.active {
            background: #764ba2;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Product List</h2>

    <div class="top-actions">
        <a href="<%= request.getContextPath() %>/" class="btn btn-home">Home</a>
        <a href="<%= request.getContextPath() %>/product" class="btn btn-create">Create Product</a>
    </div>

    <table>
        <tr>
            <th>#</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Image</th>
            <th>Action</th>
        </tr>

        <%-- ====== DỮ LIỆU MẪU (10 SP) ====== --%>
        <%
            for (int i = 1; i <= 10; i++) {
        %>
        <tr>
            <td><%= i %></td>
            <td>Product <%= i %></td>
            <td>$<%= i * 10 %></td>
            <td>Sample description for product <%= i %></td>
            <td><%= i * 5 %></td>
            <td>
                <img src="https://cdn.wccftech.com/wp-content/uploads/2025/03/iPhone-17-3-1.jpg?text=Product+<%= i %>">
            </td>
            <td>
                <a href="view-product.jsp" class="btn btn-view">View</a>
                <a href="edit-product.jsp" class="btn btn-edit">Edit</a>
<%--                <a href="#" class="btn btn-delete"--%>
<%--                   onclick="return confirm('Are you sure?')">Del</a>--%>
                <a href="del-product.jsp" class="btn btn-delete">Del</a>
            </td>
        </tr>
        <% } %>
    </table>

    <div class="pagination">
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
    </div>
</div>

</body>
</html>

