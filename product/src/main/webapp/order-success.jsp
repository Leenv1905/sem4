<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Success</title>

    <!-- Semantic UI -->
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">

    <style>
        body {
            background: linear-gradient(135deg, #43cea2, #185a9d);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .success-box {
            background: #ffffff;
            padding: 50px 60px;
            border-radius: 12px;
            text-align: center;
            width: 420px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.2);
        }

        .success-icon {
            font-size: 64px;
            color: #21ba45;
            margin-bottom: 20px;
        }

        .success-title {
            font-size: 26px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .success-text {
            color: #666;
            margin-bottom: 30px;
        }

        .success-actions a {
            display: block;
            margin-top: 12px;
        }
    </style>
</head>

<body>

<div class="success-box">
    <div class="success-icon">
        <i class="check circle icon"></i>
    </div>

    <div class="success-title">
        🎉 Đặt hàng thành công!
    </div>

    <p class="success-text">
        Cảm ơn bạn đã mua hàng tại <strong>MyShop</strong>.
        Đơn hàng của bạn đã được ghi nhận và đang được xử lý.
    </p>

    <div class="success-actions">
        <a href="<%= request.getContextPath() %>/orders"
           class="ui primary button fluid">
            <i class="clipboard list icon"></i>
            Xem đơn hàng của tôi
        </a>

        <a href="<%= request.getContextPath() %>/view-product"
           class="ui basic button fluid">
            <i class="shopping bag icon"></i>
            Tiếp tục mua sắm
        </a>
    </div>
</div>

</body>
</html>
