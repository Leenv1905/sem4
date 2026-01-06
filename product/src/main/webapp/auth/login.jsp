<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login – MyShop</title>

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

    .login-box {
      background: #fff;
      padding: 45px 50px;
      border-radius: 12px;
      width: 380px;
      box-shadow: 0 15px 40px rgba(0,0,0,0.25);
    }

    .login-title {
      text-align: center;
      margin-bottom: 30px;
    }

    .login-title h2 {
      margin-bottom: 5px;
    }

    .login-title p {
      color: #777;
    }

    .error-text {
      margin-top: 15px;
      color: #db2828;
      text-align: center;
    }

    .extra-links {
      margin-top: 20px;
      text-align: center;
    }
  </style>
</head>

<body>

<div class="login-box">
  <div class="login-title">
    <h2>🔐 Login</h2>
    <p>Sign in to your MyShop account</p>
  </div>

  <form class="ui form"
        action="<%= request.getContextPath() %>/auth?action=login"
        method="post">

    <div class="field">
      <label>Username</label>
      <input type="text" name="username"
             placeholder="Enter your username"
             required>
    </div>

    <div class="field">
      <label>Password</label>
      <input type="password" name="password"
             placeholder="Enter your password"
             required>
    </div>

    <button class="ui primary fluid button" type="submit">
      <i class="sign in icon"></i>
      Login
    </button>
  </form>

  <%-- Error message from servlet --%>
  <%
    String error = (String) request.getAttribute("error");
    if (error != null) {
  %>
  <div class="error-text">
    <i class="exclamation circle icon"></i>
    <%= error %>
  </div>
  <%
    }
  %>

  <div class="extra-links">
    <p>
      Don't have an account?
      <a href="<%= request.getContextPath() %>/auth?action=register">
        Register here
      </a>
    </p>
  </div>
</div>

</body>
</html>
