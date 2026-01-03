<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 1/3/2026
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
  <form action="${pageContext.request.contextPath}/auth?action=login" method="post">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <button type="submit">Login</button>
  </form>


  <p style="color:red">${error}</p>
  <a href="${pageContext.request.contextPath}/auth?action=register">Register</a>

</head>
<body>

</body>
</html>
