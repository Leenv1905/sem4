<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 500px;
            margin: 80px auto;
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        .header h2 {
            margin: 0;
            color: #333;
        }

        .menu a {
            text-decoration: none;
            margin-left: 10px;
            padding: 8px 14px;
            border-radius: 6px;
            background: #667eea;
            color: #fff;
            font-size: 14px;
            font-weight: bold;
            transition: 0.3s;
        }

        .menu a:hover {
            background: #764ba2;
        }

        ul {
            padding-left: 20px;
        }

        li {
            margin: 6px 0;
        }

        .actions a {
            display: inline-block;
            margin: 8px 5px 0 0;
            padding: 10px 14px;
            background: #667eea;
            color: #fff;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
            transition: 0.3s;
        }

        .actions a:hover {
            background: #764ba2;
        }

        .empty {
            color: #777;
            font-style: italic;
        }

        .session {
            font-size: 12px;
            color: #888;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<div class="container">

    <!-- Header -->
    <div class="header">
        <h2>Your Shopping Cart</h2>
        <div class="menu">
            <a href="home">Home</a>
            <a href="logout">Logout</a>
        </div>
    </div>

    <p class="session">Session ID: ${pageContext.session.id}</p>

    <!-- Cart content -->
    <c:if test="${empty cart}">
        <p class="empty">Your cart is empty.</p>
    </c:if>

    <c:if test="${not empty cart}">
        <ul>
            <c:forEach var="item" items="${cart}">
                <li>${item}</li>
            </c:forEach>
        </ul>
    </c:if>

    <hr>

    <!-- Actions -->
    <div class="actions">
        <a href="cart?item=Laptop">Add Laptop</a>
        <a href="cart?item=Phone">Add Phone</a>
        <a href="cart?item=Headphone">Add Headphone</a>
    </div>

</div>

</body>
</html>
