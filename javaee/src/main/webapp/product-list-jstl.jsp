<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Product List</title>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 1200px;
            margin: 60px auto;
            background: #fff;
            border-radius: 10px;
            padding: 25px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .top-actions {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
        }

        .btn {
            padding: 8px 14px;
            border-radius: 6px;
            text-decoration: none;
            color: #fff;
            font-size: 14px;
        }

        .btn-home { background: #6c757d; }
        .btn-create { background: #28a745; }
        .btn-view { background: #17a2b8; }
        .btn-edit { background: #ffc107; color: #000; }
        .btn-delete { background: #dc3545; }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }

        th {
            background: #f4f6fb;
        }

        img {
            width: 70px;
            height: 50px;
            object-fit: cover;
            border-radius: 4px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Product List</h2>

    <div class="top-actions">
        <a href="${pageContext.request.contextPath}/" class="btn btn-home">Home</a>
        <a href="${pageContext.request.contextPath}/product?action=create" class="btn btn-create">
            Create Product
        </a>
    </div>

    <table>
        <tr>
            <th>#</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Image</th>
            <th>Action</th>
        </tr>

        <!-- ===== LIST PRODUCT ===== -->
        <c:if test="${not empty products}">
            <c:forEach items="${products}" var="p" varStatus="st">
                <tr>
                    <td>${st.index + 1}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.description}</td>
                    <td>${p.quantity}</td>
                    <td>
                        <img src="${p.image}">
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/product?action=view&id=${p.id}"
                           class="btn btn-view">View</a>

                        <a href="${pageContext.request.contextPath}/product?action=edit&id=${p.id}"
                           class="btn btn-edit">Edit</a>

                        <a href="${pageContext.request.contextPath}/product?action=delete&id=${p.id}"
                           class="btn btn-delete"
                           onclick="return confirm('Are you sure?')">Del</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

        <!-- ===== EMPTY ===== -->
        <c:if test="${empty products}">
            <tr>
                <td colspan="7">No products found</td>
            </tr>
        </c:if>
    </table>
</div>

</body>
</html>
