<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.t2406e.player.model.Player" %>
<%@ page import="com.t2406e.player.model.Indexer" %>
<%@ page import="com.t2406e.player.model.PlayerIndex" %>
<%@ page import="java.util.List" %>
<%@ page import="com.t2406e.player.model.PlayerView" %>


<%
    PlayerView editPlayer =
            (PlayerView) request.getAttribute("player");
    boolean isEdit = (editPlayer != null);
%>


<html>
<head>
    <title><%= isEdit ? "Edit Player" : "Add Player" %></title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h2><%= isEdit ? "Edit Player" : "Add Player" %></h2>

<form method="post" action="player">
    <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">

    <% if (isEdit) { %>
<%--    <input type="hidden" name="id" value="<%=editPlayer.getPlayerIndexId()%>">--%>
    <input type="hidden" name="id" value="<%=editPlayer.getPlayerIndexId()%>">
    <input type="hidden" name="playerId" value="<%=editPlayer.getPlayerId()%>">

    <% } %>

    Name:
    <input name="name" value="<%= isEdit ? editPlayer.getPlayerName() : "" %>"><br>

    Full name:
    <input name="fullName" value=""><br>

    Age:
    <input name="age" value="<%= isEdit ? editPlayer.getAge() : "" %>"><br>

    Index:
    <select name="indexId">
        <%
            List<Indexer> indexers =
                    (List<Indexer>) request.getAttribute("indexers");

            if (indexers != null) {
                for (Indexer i : indexers) {
        %>
        <option value="<%=i.getIndexId()%>"
                <%= isEdit && i.getIndexId() == editPlayer.getIndexId() ? "selected" : "" %>>
            <%=i.getName()%>
        </option>
        <%
                }
            }
        %>
    </select>

    Value:
    <input name="value" value="<%= isEdit ? editPlayer.getValue() : "" %>"><br>

    <button><%= isEdit ? "Update" : "Add" %></button>
</form>


<a href="player">← Back</a>

</body>
</html>
