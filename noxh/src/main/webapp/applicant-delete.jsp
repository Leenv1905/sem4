<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.t2406e.noxh.model.Applicant" %>

<%
    Applicant applicant = (Applicant) request.getAttribute("applicant");
%>

<html>
<head>
    <title>Xóa hồ sơ</title>
</head>
<body>

<h2>Xác nhận xóa hồ sơ</h2>

<p>
    Bạn có chắc chắn muốn xóa hồ sơ của:
    <strong><%= applicant.getName() %></strong> ?
</p>

<form method="post" action="applicant">
    <input type="hidden" name="action" value="delete"/>
    <input type="hidden" name="id" value="<%= applicant.getId() %>"/>

    <button type="submit">Xóa</button>
    <a href="applicant">Hủy</a>
</form>

</body>
</html>
