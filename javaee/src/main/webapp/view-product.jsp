<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.t2406e.javaee.model.Product" %>

<%
    Product p = (Product) request.getAttribute("product");
%>

<html>
<head>
    <title>View Product</title>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 900px;
            margin: 60px auto;
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }

        .back-link {
            margin-bottom: 20px;
        }

        .back-link a {
            text-decoration: none;
            color: #667eea;
            font-weight: bold;
        }

        .product-detail {
            display: flex;
            gap: 30px;
        }

        .product-image {
            flex: 1;
        }

        .product-image img {
            width: 100%;
            border-radius: 8px;
            object-fit: cover;
        }

        .product-info {
            flex: 1;
        }

        .product-info h2 {
            margin-top: 0;
            margin-bottom: 15px;
        }

        .price {
            font-size: 22px;
            color: #e74c3c;
            margin-bottom: 15px;
            font-weight: bold;
        }

        .description {
            margin-bottom: 20px;
            line-height: 1.6;
        }

        .quantity {
            margin-bottom: 20px;
        }

        .actions {
            display: flex;
            gap: 15px;
        }

        .btn {
            padding: 10px 18px;
            border-radius: 6px;
            border: none;
            font-size: 15px;
            cursor: pointer;
            color: #fff;
            text-decoration: none;
        }

        .btn-buy {
            background: #28a745;
        }

        .btn-cart {
            background: #ff9800;
        }
    </style>
</head>
<body>

<div class="container">

    <!-- Back -->
    <div class="back-link">
        <a href="<%= request.getContextPath() %>/product">← Back to Product List</a>
    </div>

    <!-- Product detail -->
    <div class="product-detail">

        <!-- Image -->
        <div class="product-image">
<%--            <img src="<%= request.getContextPath() + "/" + p.getImage() %>"--%>
<%--                 alt="<%= p.getName() %>">--%>
                 <img src="<%= p.getImage() %>"
                     alt="<%= p.getName() %>">
        </div>

        <!-- Info -->
        <div class="product-info">
            <h2><%= p.getName() %></h2>

            <div class="price">$<%= p.getPrice() %></div>

            <div class="description">
                <%= p.getDescription() %>
            </div>

            <div class="quantity">
                <strong>Quantity:</strong> <%= p.getQuantity() %> items available
            </div>

            <div class="actions">
                <a href="#" class="btn btn-buy">Buy Now</a>
                <a href="#" class="btn btn-cart">Add to Cart</a>
            </div>
        </div>

    </div>

</div>

</body>
</html>
