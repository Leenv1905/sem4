<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.t2406e.javaee.model.Product" %>

<%
    Product product = (Product) request.getAttribute("product");
%>

<html>
<head>
    <title>Delete Product</title>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 500px;
            margin: 120px auto;
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.25);
            text-align: center;
        }

        h2 {
            color: #dc3545;
            margin-bottom: 15px;
        }

        .product-info {
            margin: 20px 0;
            background: #f8f9fa;
            padding: 15px;
            border-radius: 8px;
            text-align: left;
        }

        .product-info p {
            margin: 6px 0;
            color: #333;
        }

        .btn-group {
            margin-top: 25px;
            display: flex;
            justify-content: space-between;
        }

        button, .cancel-btn {
            padding: 10px 20px;
            border-radius: 6px;
            border: none;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
        }

        .delete-btn {
            background: #dc3545;
            color: #fff;
        }

        .delete-btn:hover {
            background: #c82333;
        }

        .cancel-btn {
            background: #e2e8f0;
            color: #333;
        }

        .cancel-btn:hover {
            background: #cbd5e1;
        }

        /* ===== MODAL ===== */
        .modal {
            display: none;
            position: fixed;
            z-index: 999;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background: rgba(0,0,0,0.6);
        }

        .modal-content {
            background: #fff;
            width: 420px;
            margin: 180px auto;
            padding: 25px;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 10px 25px rgba(0,0,0,0.3);
        }

        .modal-buttons {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Delete Product</h2>

    <p>Are you sure you want to delete this product?</p>

    <div class="product-info">
        <p><strong>Name:</strong> <%= product.getName() %></p>
        <p><strong>Price:</strong> $<%= product.getPrice() %></p>
        <p><strong>Description:</strong> <%= product.getDescription() %></p>
        <p><strong>Quantity:</strong> <%= product.getQuantity() %></p>
    </div>

    <div class="btn-group">
        <button class="delete-btn" onclick="openModal()">Delete</button>
        <a href="<%= request.getContextPath() %>/product" class="cancel-btn">
            Cancel
        </a>
    </div>
</div>

<!-- ===== MODAL CONFIRM ===== -->
<div id="confirmModal" class="modal">
    <div class="modal-content">
        <h3>Confirm Delete</h3>
        <p>This action cannot be undone.</p>

        <div class="modal-buttons">
            <!-- Gọi đúng ProductServlet -->
            <a href="<%= request.getContextPath() %>/product?action=delete&id=<%= product.getId() %>"
               class="delete-btn"
               style="padding:10px 18px; text-decoration:none;">
                Yes, Delete
            </a>

            <button class="cancel-btn" onclick="closeModal()">Cancel</button>
        </div>
    </div>
</div>

<script>
    function openModal() {
        document.getElementById("confirmModal").style.display = "block";
    }

    function closeModal() {
        document.getElementById("confirmModal").style.display = "none";
    }
    </sc
