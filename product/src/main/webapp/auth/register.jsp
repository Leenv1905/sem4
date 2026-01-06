<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 1/3/2026
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register – MyShop</title>

    <!-- Semantic UI -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">

    <style>
        body {
            background: linear-gradient(135deg, #667eea, #764ba2);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .register-box {
            background: #fff;
            padding: 45px 50px;
            border-radius: 12px;
            width: 420px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.25);
        }

        .register-title {
            text-align: center;
            margin-bottom: 30px;
        }

        .register-title h2 {
            margin-bottom: 5px;
        }

        .register-title p {
            color: #777;
        }

        .extra-links {
            margin-top: 20px;
            text-align: center;
        }
    </style>
</head>

<body>

<div class="register-box">
    <div class="register-title">
        <h2>📝 Register</h2>
        <p>Create your MyShop account</p>
    </div>

    <form class="ui form"
          action="<%= request.getContextPath() %>/auth?action=register"
          method="post">

        <div class="field">
            <label>Username</label>
            <input type="text" name="username"
                   placeholder="Choose a username"
                   required>
        </div>

        <div class="field">
            <label>Full name</label>
            <input type="text" name="fullName"
                   placeholder="Your full name"
                   required>
        </div>

        <div class="field">
            <label>Password</label>
            <input type="password" name="password"
                   placeholder="Create a password"
                   required>
        </div>

        <button class="ui green fluid button" type="submit">
            <i class="user plus icon"></i>
            Register
        </button>
    </form>

    <div class="extra-links">
        <p>
            Already have an account?
            <a href="<%= request.getContextPath() %>/auth?action=login">
                Login here
            </a>
        </p>
    </div>
</div>

</body>
</html>

