<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.t2406e.player.model.*" %>

<html>
<head>
    <title>Player Information</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h2 class="title">Player Information</h2>

<form method="post" action="player" class="player-form">

    <!-- Row 1 -->
    <div class="form-item">
        <label>Player name</label>
        <input name="name" placeholder="Player name">
    </div>

    <div class="form-item">
        <label>Player age</label>
        <input name="age" placeholder="Player age">
    </div>

    <!-- Row 2 -->
    <div class="form-item">
        <label>Full name</label>
        <input name="fullName" placeholder="Full name">
    </div>

    <div class="form-item">
        <label>Index name</label>
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
    </div>

    <!-- Row 3 -->
    <div class="form-item">
        <label>Value</label>
        <input name="value" placeholder="Value">
    </div>

    <div class="form-action">
        <button type="submit">Add</button>
    </div>

</form>

<hr>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Player name</th>
        <th>Player full name</th>
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

        if (players != null) {
            for (PlayerView p : players) {
    %>
    <tr>
        <td><%=p.getPlayerIndexId()%></td>
        <td><%=p.getPlayerName()%></td>
        <td><%=p.getFullName()%></td>
        <td><%=p.getAge()%></td>
        <td><%=p.getIndexName()%></td>
        <td><%=p.getValue()%></td>
        <td>
            <a href="player?action=edit&id=<%=p.getPlayerIndexId()%>">✏️</a>
            |
            <a href="player?action=delete&id=<%=p.getPlayerIndexId()%>"
               onclick="return confirm('Xóa?');">🗑️</a>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<div class="table-footer">
    Số 8, Tôn Thất Thuyết, Cầu Giấy, Hà Nội
</div>


</body>
</html>
