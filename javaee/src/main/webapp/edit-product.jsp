<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.t2406e.javaee.model.Product" %>

<%
    Product product = (Product) request.getAttribute("product");
%>

<html>
<head>
    <title>Edit Product</title>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 450px;
            margin: 80px auto;
            background: #ffffff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.25);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 6px;
            color: #555;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        input:focus {
            outline: none;
            border-color: #667eea;
        }

        .btn-group {
            display: flex;
            justify-content: space-between;
            margin-top: 25px;
        }

        button, .back-btn {
            padding: 10px 18px;
            border-radius: 6px;
            border: none;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            text-align: center;
        }

        button {
            background: #28a745;
            color: #fff;
        }

        button:hover {
            background: #218838;
        }

        .back-btn {
            background: #e2e8f0;
            color: #333;
        }

        .back-btn:hover {
            background: #cbd5e1;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Edit Product</h2>

    <!-- POST: update product -->
    <form action="<%= request.getContextPath() %>/product?action=edit" method="post">

        <!-- Hidden ID -->
        <input type="hidden" name="id" value="<%= product.getId() %>">

        <div class="form-group">
            <label>Product Name</label>
            <input type="text" name="name"
                   value="<%= product.getName() %>" required>
        </div>

        <div class="form-group">
            <label>Price</label>
            <input type="number" name="price" step="0.01"
                   value="<%= product.getPrice() %>" required>
        </div>

        <div class="form-group">
            <label>Description</label>
            <input type="text" name="description"
                   value="<%= product.getDescription() %>" required>
        </div>

        <div class="form-group">
            <label>Quantity</label>
            <input type="number" name="quantity"
                   value="<%= product.getQuantity() %>" required>
        </div>

        <div class="form-group">
            <label>Image URL</label>
            <input type="text" name="image"
                   value="<%= product.getImage() %>" required>
        </div>

        <div class="btn-group">
            <button type="submit">Update</button>
            <a href="<%= request.getContextPath() %>/product"
               class="back-btn">Back to List</a>
        </div>
    </form>
</div>

</body>
</html>
