package com.t2406e.product.controller;

import com.t2406e.product.dao.*;
import com.t2406e.product.model.*;
import com.t2406e.product.util.DBConnection;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;
// chặn ADMIN servlet bằng Filter role ADMIN nữa cho an toàn

@WebServlet("/admin/orders")
public class AdminOrderServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAO();
    private OrderItemDAO itemDAO = new OrderItemDAO();
    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("loginUser");

        // ===== CHECK ADMIN =====
        if (user == null || !"ADMIN".equals(user.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        try {
            List<Order> orders = orderDAO.getAll();
            Map<Order, List<OrderItem>> orderMap = new LinkedHashMap<>();

            for (Order o : orders) {
                List<OrderItem> items = itemDAO.getByOrderId(o.getId());

                for (OrderItem item : items) {
                    Product p = productDAO.getById(item.getProductId());
                    req.setAttribute("product_" + item.getProductId(), p);
                }

                orderMap.put(o, items);
            }

            req.setAttribute("orderMap", orderMap);
            req.getRequestDispatcher("/admin-orders.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /* ===== ADMIN DELETE ORDER + RESTORE STOCK ===== */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        int orderId = Integer.parseInt(req.getParameter("orderId"));

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            Map<Integer, Integer> items =
                    itemDAO.getItemsForRestore(conn, orderId);

            for (Map.Entry<Integer, Integer> e : items.entrySet()) {
                productDAO.restoreStock(conn, e.getKey(), e.getValue());
            }

            itemDAO.deleteByOrderId(conn, orderId);
            orderDAO.delete(conn, orderId);

            conn.commit();
            resp.sendRedirect(req.getContextPath() + "/admin/orders");

        } catch (Exception e) {
            throw new ServletException("Admin delete order failed", e);
        }
    }
}
