<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>

    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 450px;
            margin: 80px auto;
            background: #ffffff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.25);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 6px;
            color: #555;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        input:focus {
            outline: none;
            border-color: #667eea;
        }

        .btn-group {
            display: flex;
            justify-content: space-between;
            margin-top: 25px;
        }

        button, .home-btn {
            padding: 10px 18px;
            border-radius: 6px;
            border: none;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            text-align: center;
        }

        button {
            background: #667eea;
            color: #fff;
        }

        button:hover {
            background: #5a67d8;
        }

        .home-btn {
            background: #e2e8f0;
            color: #333;
        }

        .home-btn:hover {
            background: #cbd5e1;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Create Product</h2>

    <!-- POST: gửi dữ liệu -->
    <form action="${pageContext.request.contextPath}/product?action=create" method="post">

        <div class="form-group">
            <label>Product Name</label>
            <input type="text" name="name" required>
        </div>

        <div class="form-group">
            <label>Price</label>
            <input type="number" name="price" step="0.01" required>
        </div>

        <div class="form-group">
            <label>Description</label>
            <input type="text" name="description" required>
        </div>

        <div class="form-group">
            <label>Quantity</label>
            <input type="number" name="quantity" required>
        </div>

        <div class="form-group">
            <label>Image URL</label>
            <input type="text" name="image" placeholder="images/iphone15.jpg" required>
        </div>

        <div class="btn-group">
            <button type="submit">Create</button>

            <!-- GET: mở danh sách -->
            <a href="${pageContext.request.contextPath}/product" class="home-btn">
                Back to List
            </a>
        </div>
    </form>
</div>

</body>
</html>
