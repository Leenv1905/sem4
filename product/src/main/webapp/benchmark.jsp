<%--
  Created by IntelliJ IDEA.
  User: vanth
  Date: 12/26/2025
  Time: 9:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<html>
<head>
    <title>Benchmark Results</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">
</head>

<body style="background: #f0f0f0; padding: 40px;">

<div class="ui container">
    <h2 class="ui header">Benchmark Results</h2>

    <table class="ui celled table">
        <thead>
        <tr>
            <th>Test</th>
            <th>Time</th>
        </tr>
        </thead>
        <tbody>
        <%
            Map<String, String> results = (Map<String, String>) request.getAttribute("results");
            for (Map.Entry<String, String> e : results.entrySet()) {
        %>
        <tr>
            <td><%= e.getKey() %></td>
            <td><%= e.getValue() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <a href="<%= request.getContextPath() %>/product" class="ui button">Back to Products</a>
</div>

</body>
</html>

