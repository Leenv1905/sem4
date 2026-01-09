<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.t2406e.noxh.model.Applicant" %>

<%
    Applicant applicant = (Applicant) request.getAttribute("applicant");
    boolean isEdit = (applicant != null);
%>

<html>
<head>
    <title>Applicant Form</title>
</head>
<body>

<h2>
    <%= isEdit ? "Cập nhật hồ sơ" : "Thêm hồ sơ" %>
</h2>

<form method="post" action="applicant">
    <input type="hidden" name="action" value="<%= isEdit ? "edit" : "create" %>"/>

    <% if (isEdit) { %>
    <input type="hidden" name="id" value="<%= applicant.getId() %>"/>
    <% } %>

    <table>
        <tr>
            <td>Tên người nộp hồ sơ:</td>
            <td>
                <input type="text" name="name"
                       value="<%= isEdit ? applicant.getName() : "" %>" required/>
            </td>
        </tr>

        <tr>
            <td>Trạng thái hợp lệ:</td>
            <td>
                <input type="checkbox" name="status"
                        <%= (isEdit && applicant.isStatus()) ? "checked" : "" %> />
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <button type="submit">Lưu</button>
                <a href="applicant">Hủy</a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
