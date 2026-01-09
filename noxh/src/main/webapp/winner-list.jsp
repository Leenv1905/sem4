<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.t2406e.noxh.model.WinnerDetail" %>

<%
    List<WinnerDetail> winners =
            (List<WinnerDetail>) request.getAttribute("winners");

    int pageSize = 50;
    int currentPage = 1;

    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }

    int totalItems = winners != null ? winners.size() : 0;
    int totalPages = (int) Math.ceil((double) totalItems / pageSize);

    int fromIndex = (currentPage - 1) * pageSize;
    int toIndex = Math.min(fromIndex + pageSize, totalItems);
%>



<html>
<head>
    <title>Danh sách trúng tuyển</title>
</head>
<body>

<h2>DANH SÁCH TRÚNG NHÀ Ở XÃ HỘI</h2>

<p>
    <a href="applicant">⬅ Quay lại danh sách hồ sơ</a> |
    <a href="applicant?action=reset"
       onclick="return confirm('Reset toàn bộ kết quả bốc thăm?');">
        🔄 Reset bốc thăm
    </a>
</p>


<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>STT</th>
        <th>Mã hồ sơ</th>
        <th>Tên người trúng</th>
        <th>Trạng thái</th>
    </tr>
    <%
        if (winners != null && totalItems > 0) {
            int stt = fromIndex + 1;
            for (int i = fromIndex; i < toIndex; i++) {
                WinnerDetail w = winners.get(i);
    %>
    <tr>
        <td><%= stt++ %></td>
        <td><%= w.getApplicantId() %></td>
        <td><%= w.getName() %></td>
        <td><%= w.isStatus() ? "Hợp lệ" : "Không hợp lệ" %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">Chưa có kết quả bốc thăm</td>
    </tr>
    <%
        }
    %>


    <p>
        Trang:
        <%
            for (int i = 1; i <= totalPages; i++) {
        %>
        <a href="applicant?action=winners&page=<%= i %>">
            <%= (i == currentPage) ? ("[" + i + "]") : i %>
        </a>
        <%
            }
        %>
    </p>

</table>

</body>
</html>
