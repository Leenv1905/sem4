<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.t2406e.product.model.Product" %>

<%
    List<Product> cartProducts = (List<Product>) request.getAttribute("cartProducts");
    Integer totalQuantity = (Integer) request.getAttribute("totalQuantity");
    Double totalPrice = (Double) request.getAttribute("totalPrice");
%>

<html>
<head>
    <title>Shopping Cart</title>
    <!-- Semantic UI CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">
</head>
<body style="background: linear-gradient(135deg, #667eea, #764ba2);">

<div class="ui container" style="margin-top: 60px; width: 80%;">
    <div class="ui raised very padded segment">
        <h2 class="ui center aligned header">Shopping Cart</h2>

        <table class="ui celled striped table">
            <thead>
            <tr>
                <th style="width:20%;">Image</th>
                <th style="width:30%;">Product Name</th>
                <th style="width:10%;">Price</th>
                <th style="width:10%;">Quantity</th>
                <th style="width:20%;">Subtotal</th>
                <th style="width:10%;">Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (cartProducts != null && !cartProducts.isEmpty()) {
                    for (Product item : cartProducts) {
            %>
            <tr>
                <td><img src="<%= item.getImage() %>" class="ui small rounded image" style="width:70px;height:50px;"></td>
                <td><%= item.getName() %></td>
                <td>$<%= item.getPrice() %></td>
                <td>
                    <div class="ui buttons mini">
                        <a href="<%= request.getContextPath() %>/cart?action=update&id=<%= item.getId() %>&quantity=<%= item.getQuantity()-1 %>"
                           class="ui button">-</a>
                        <div class="ui button disabled"><%= item.getQuantity() %></div>
                        <a href="<%= request.getContextPath() %>/cart?action=update&id=<%= item.getId() %>&quantity=<%= item.getQuantity()+1 %>"
                           class="ui button">+</a>
                    </div>
                </td>
                <td>$<%= item.getPrice() * item.getQuantity() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/cart?action=remove&id=<%= item.getId() %>"
                       class="ui red button mini">Remove</a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6" class="center aligned">Your cart is empty</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <div class="ui segment">
            <p><strong>Total Quantity:</strong> <%= totalQuantity != null ? totalQuantity : 0 %></p>
            <p><strong>Total Price:</strong> $<%= totalPrice != null ? totalPrice : 0 %></p>
        </div>

        <div class="ui buttons">
            <a href="<%= request.getContextPath() %>/product" class="ui button grey">Back to Product List</a>
            <a href="<%= request.getContextPath() %>/cart?action=clear" class="ui button red">Clear Cart</a>
            <a href="#" class="ui green button">Checkout (Mock)</a>
            <form method="post" action="${pageContext.request.contextPath}/checkout">
                <button class="ui primary button">Xác nhận mua hàng</button>
            </form>
        </div>
    </div>
</div>

<!-- Semantic UI JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>
</body>
</html>



<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: vanth--%>
<%--  Date: 12/24/2025--%>
<%--  Time: 4:20 PM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="java.util.*" %>--%>

<%--<%--%>
<%--    // Dữ liệu mẫu cho giỏ hàng--%>
<%--    class CartItem {--%>
<%--        int id;--%>
<%--        String name;--%>
<%--        double price;--%>
<%--        int quantity;--%>
<%--        String image;--%>

<%--        CartItem(int id, String name, double price, int quantity, String image) {--%>
<%--            this.id = id;--%>
<%--            this.name = name;--%>
<%--            this.price = price;--%>
<%--            this.quantity = quantity;--%>
<%--            this.image = image;--%>
<%--        }--%>
<%--    }--%>

<%--    List<CartItem> cart = new ArrayList<>();--%>
<%--    cart.add(new CartItem(1, "iPhone 15 Pro", 1200.00, 1, "https://cdn.wccftech.com/wp-content/uploads/2025/03/iPhone-17-3-1.jpg"));--%>
<%--    cart.add(new CartItem(2, "Samsung Galaxy S24", 999.00, 2, "https://cdn.wccftech.com/wp-content/uploads/2025/03/iPhone-17-3-1.jpg"));--%>
<%--    cart.add(new CartItem(3, "MacBook Air M2", 1500.00, 1, "https://cdn.wccftech.com/wp-content/uploads/2025/03/iPhone-17-3-1.jpg"));--%>
<%--    cart.add(new CartItem(4, "Sony WH-1000XM5", 350.00, 1, "https://cdn.wccftech.com/wp-content/uploads/2025/03/iPhone-17-3-1.jpg"));--%>
<%--    cart.add(new CartItem(5, "Apple Watch Ultra", 799.00, 1, "https://cdn.wccftech.com/wp-content/uploads/2025/03/iPhone-17-3-1.jpg"));--%>

<%--    int totalQuantity = 0;--%>
<%--    double totalPrice = 0;--%>
<%--    for (CartItem item : cart) {--%>
<%--        totalQuantity += item.quantity;--%>
<%--        totalPrice += item.price * item.quantity;--%>
<%--    }--%>
<%--%>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Shopping Cart</title>--%>
<%--    <!-- Semantic UI CSS -->--%>
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css">--%>
<%--</head>--%>
<%--<body style="background: linear-gradient(135deg, #667eea, #764ba2);">--%>

<%--<div class="ui container" style="margin-top: 60px; width: 80%;">--%>
<%--    <div class="ui raised very padded segment">--%>
<%--        <h2 class="ui center aligned header">Shopping Cart</h2>--%>

<%--        <table class="ui celled striped table">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th style="width:20%;">Image</th>--%>
<%--                <th style="width:30%;">Product Name</th>--%>
<%--                <th style="width:10%;">Price</th>--%>
<%--                <th style="width:10%;">Quantity</th>--%>
<%--                <th style="width:20%;">Subtotal</th>--%>
<%--                <th style="width:10%;">Action</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <%--%>
<%--                for (CartItem item : cart) {--%>
<%--            %>--%>
<%--            <tr>--%>
<%--                <td><img src="<%= item.image %>" class="ui small rounded image" style="width:70px;height:50px;"></td>--%>
<%--                <td><%= item.name %></td>--%>
<%--                <td>$<%= item.price %></td>--%>
<%--                <td>--%>
<%--                    <div class="ui buttons mini">--%>
<%--                        <a href="#" class="ui button">-</a>--%>
<%--                        <div class="ui button disabled"><%= item.quantity %></div>--%>
<%--                        <a href="#" class="ui button">+</a>--%>
<%--                    </div>--%>
<%--                </td>--%>
<%--                <td>$<%= item.price * item.quantity %></td>--%>
<%--                <td>--%>
<%--                    <a href="#" class="ui red button mini">Remove</a>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <%--%>
<%--                }--%>
<%--            %>--%>
<%--            </tbody>--%>
<%--        </table>--%>

<%--        <div class="ui segment">--%>
<%--            <p><strong>Total Quantity:</strong> <%= totalQuantity %></p>--%>
<%--            <p><strong>Total Price:</strong> $<%= totalPrice %></p>--%>
<%--        </div>--%>

<%--        <div class="ui buttons">--%>
<%--            <a href="<%= request.getContextPath() %>/product" class="ui button grey">Back to Product List</a>--%>
<%--            <a href="#" class="ui green button">Checkout (Mock)</a>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<!-- Semantic UI JS -->--%>
<%--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>

