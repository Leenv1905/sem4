<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JavaEE Demo</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 400px;
            margin: 100px auto;
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
            text-align: center;
        }

        h1 {
            margin-bottom: 25px;
            color: #333;
        }

        .menu a {
            display: block;
            text-decoration: none;
            margin: 12px 0;
            padding: 12px;
            border-radius: 6px;
            background: #667eea;
            color: #fff;
            font-weight: bold;
            transition: all 0.3s ease;
        }

        .menu a:hover {
            background: #764ba2;
            transform: translateY(-2px);
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Welcome</h1>

    <div class="menu">
        <a href="home">Home</a>
        <a href="login">Login</a>
        <a href="cart">Cart</a>
        <a href="product-list.jsp">Product</a>
    </div>
</div>

</body>
</html>
