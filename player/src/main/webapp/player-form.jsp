<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.t2406e.player.model.Player" %>

<%
    Player p = (Player) request.getAttribute("player");
    boolean isEdit = (p != null);
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
    <input type="hidden" name="id" value="<%=p.getPlayerId()%>">
    <% } %>

    <label>Player name</label>
    <input name="name" value="<%= isEdit ? p.getName() : "" %>">

    <label>Full name</label>
    <input name="fullName" value="<%= isEdit ? p.getFullName() : "" %>">

    <label>Age</label>
    <input name="age" value="<%= isEdit ? p.getAge() : "" %>">

    <button class="btn-primary">
        <%= isEdit ? "Update" : "Add" %>
    </button>
</form>

<a href="player">← Back</a>

</body>
</html>
