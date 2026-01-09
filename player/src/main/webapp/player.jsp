<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.t2406e.player.model.*" %>

<html>
<head>
    <title>Player Information</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h2>Player Information</h2>

<form method="post" action="player">
    Name: <input name="name"><br>
    Full name: <input name="fullName"><br>
    Age: <input name="age"><br>

    Index:
    <select name="indexId">
        <%
            List<Indexer> indexers =
                    (List<Indexer>) request.getAttribute("indexers");

            if (indexers != null) {
                for (Indexer i : indexers) {
        %>
        <option value="<%=i.getIndexId()%>"><%=i.getName()%></option>
        <%
                }
            }
        %>

    </select>

    Value: <input name="value"><br>

    <button>Add</button>
</form>

<hr>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Player name</th>
        <th>Player age</th>
        <th>Index name</th>
        <th>Value</th>
        <th>Action</th>
    </tr>
    </thead>

    <tbody>
    <%
        List<PlayerView> players =
                (List<PlayerView>) request.getAttribute("players");
        for (PlayerView p : players) {
    %>
    <tr>
        <td><%=p.getId()%></td>
        <td><%=p.getPlayerName()%></td>
        <td><%=p.getAge()%></td>
        <td><%=p.getIndexName()%></td>
        <td><%=p.getValue()%></td>
        <td>
            ✏️ 🗑️
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<div class="table-footer">
    Số 8, Tôn Thất Thuyết, Cầu Giấy, Hà Nội
</div>


</body>
</html>
