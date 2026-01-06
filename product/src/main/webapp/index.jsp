<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>JSP - Hello World</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1><%= "Home!" %>--%>
<%--</h1>--%>
<%--<br/>--%>
<%--<a href="home">Hello Servlet</a>--%>
<%--<br/>--%>
<%--<a href="product">HOME</a>--%>
<%--<br/>--%>
<%--<a href="cart">CART</a>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.t2406e.product.model.Product" %>
<html>
<head>
    <title>My Shop - Home</title>

    <!-- Semantic UI CSS -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">

    <style>
        body {
            background: linear-gradient(135deg, #667eea, #764ba2);
        }
        .main-container {
            margin-top: 80px;
            width: 85%;
        }
        .slider-wrapper {
            max-width: 700px;
            margin: 0 auto 40px;
        }
        .product-image {
            height: 150px;
            object-fit: cover;
        }
        footer {
            margin-top: 50px;
            padding: 30px 0;
            background: #1b1c1d;
            color: #fff;
        }
    </style>
</head>

<body>
<%
    response.sendRedirect(request.getContextPath() + "/view-product");
%>

<!-- ===== APP BAR ===== -->
</body>
</html>
