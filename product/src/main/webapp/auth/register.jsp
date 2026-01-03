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
    <title>Register</title>

    <form action="${pageContext.request.contextPath}/auth?action=register" method="post">
        Username: <input type="text" name="username"><br>
        Full name: <input type="text" name="fullName"><br>
        Password: <input type="password" name="password"><br>
        <button type="submit">Register</button>
    </form>

</head>
<body>

</body>
</html>
