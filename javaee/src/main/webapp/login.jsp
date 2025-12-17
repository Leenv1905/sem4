<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 380px;
            margin: 120px auto;
            background: #ffffff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        label {
            font-weight: bold;
            color: #444;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border-radius: 6px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            border: none;
            border-radius: 6px;
            background: #667eea;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }

        input[type="submit"]:hover {
            background: #764ba2;
        }

        .error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }

        .nav {
            text-align: center;
            margin-top: 20px;
        }

        .nav a {
            text-decoration: none;
            color: #667eea;
            font-weight: bold;
        }

        .nav a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Đăng nhập</h2>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p class="error"><%= error %></p>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/login" method="post">
        <label>Username</label>
        <input type="text" name="username" required />

        <label>Password</label>
        <input type="password" name="password" required />

        <input type="submit" value="Login" />
    </form>

    <div class="nav">
        <a href="<%= request.getContextPath() %>/home">← Về trang Home</a>
    </div>
</div>

</body>
</html>
