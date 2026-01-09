<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.t2406e.noxh.model.Applicant" %>

<%
    List<Applicant> applicants =
            (List<Applicant>) request.getAttribute("applicants");

    int pageSize = 50;
    int currentPage = 1;

    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }

    int totalItems = applicants != null ? applicants.size() : 0;
    int totalPages = (int) Math.ceil((double) totalItems / pageSize);

    int fromIndex = (currentPage - 1) * pageSize;
    int toIndex = Math.min(fromIndex + pageSize, totalItems);
%>



<html>
<head>
    <title>Danh sách hồ sơ</title>
</head>
<body>

<h2>DANH SÁCH HỒ SƠ NHÀ Ở XÃ HỘI</h2>

<p>
    <a href="applicant?action=create">➕ Thêm hồ sơ</a> |
    <a href="applicant?action=draw"
       onclick="return confirm('Bạn có chắc chắn muốn bốc thăm không?');">
        🎯 Bốc thăm
    </a> |
    <a href="applicant?action=winners">🏆 Xem kết quả</a> |
    <a href="applicant?action=reset"
       onclick="return confirm('Bạn có chắc chắn muốn RESET kết quả bốc thăm không?');">
        🔄 Reset bốc thăm
    </a>
</p>


<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Tên người nộp</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>

    <%
        if (applicants != null && totalItems > 0) {
            for (int i = fromIndex; i < toIndex; i++) {
                Applicant a = applicants.get(i);
    %>
    <tr>
        <td><%= a.getId() %></td>
        <td><%= a.getName() %></td>
        <td><%= a.isStatus() ? "Hợp lệ" : "Không hợp lệ" %></td>
        <td>
            <a href="applicant?action=edit&id=<%= a.getId() %>">Sửa</a> |
            <a href="applicant?action=delete&id=<%= a.getId() %>">Xóa</a> |
            <a href="applicant?action=view&id=<%= a.getId() %>">Xem</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">Chưa có hồ sơ nào</td>
    </tr>
    <%
        }
    %>
    <p>
        Trang:
        <%
            for (int i = 1; i <= totalPages; i++) {
        %>
        <a href="applicant?page=<%= i %>">
            <%= (i == currentPage) ? ("[" + i + "]") : i %>
        </a>
        <%
            }
        %>
    </p>


</table>

</body>
</html>
