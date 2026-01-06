package com.t2406e.product.controller;

import com.t2406e.product.dao.*;
import com.t2406e.product.model.*;

import com.t2406e.product.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAO();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("loginUser");

        Map<Integer, Integer> cart =
                (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {

            // ====== START TRANSACTION ======
            conn.setAutoCommit(false);

            double totalPrice = 0;
            for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
                Product p = productDAO.getById(e.getKey());
                totalPrice += p.getPrice() * e.getValue();
            }

            int orderId = orderDAO.createOrder(conn, user.getId(), totalPrice);

            for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
                Product p = productDAO.getById(e.getKey());
                int qty = e.getValue();

                // Check tồn kho
                if (qty > p.getQuantity()) {
                    throw new SQLException("Không đủ hàng cho sản phẩm: " + p.getName());
                }

                // Lưu chi tiê đơn haàng
                orderItemDAO.addItem(conn, orderId, p.getId(), p.getPrice(), qty);

                // Trừ tồn kho
                int newQty = p.getQuantity() - qty;
                productDAO.updateStock(conn, p.getId(), newQty);
            }

            // ====== COMMIT ======
            conn.commit();
            // Xoá giỏ hàng
            session.removeAttribute("cart");
            resp.sendRedirect(req.getContextPath() + "/order-success.jsp");

        } catch (Exception e) {
            e.printStackTrace();

            // ====== ROLLBACK ======
            try {
                Connection conn = DBConnection.getConnection();
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            throw new ServletException("Checkout thất bại – đã rollback", e);
        }
    }
}

