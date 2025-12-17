<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.t2406e.javaee.model.User" %>

<%
    User currentUser = (User) session.getAttribute("currentUser");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 500px;
            margin: 100px auto;
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        .header h2 {
            margin: 0;
            color: #333;
        }

        .menu a {
            text-decoration: none;
            margin-left: 10px;
            padding: 8px 14px;
            border-radius: 6px;
            background: #667eea;
            color: #fff;
            font-size: 14px;
            font-weight: bold;
            transition: 0.3s;
        }

        .menu a:hover {
            background: #764ba2;
        }

        .content p {
            color: #444;
            font-size: 16px;
            line-height: 1.6;
        }
    </style>
</head>
<body>

<div class="container">

    <!-- Header -->
    <div class="header">
        <h2>
            Xin chào,
            <%= currentUser != null ? currentUser.getUsername() : "Guest" %>
        </h2>

        <div class="menu">
            <a href="<%= request.getContextPath() %>/cart">Cart</a>
            <a href="<%= request.getContextPath() %>/logout">Logout</a>
        </div>
    </div>

    <!-- Content -->
    <div class="content">
        <p>Đây là trang chỉ truy cập được sau khi đăng nhập thành công.</p>
    </div>

</div>

</body>
</html>
