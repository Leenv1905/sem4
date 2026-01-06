package com.t2406e.product.controller;

import com.t2406e.product.dao.OrderDAO;
import com.t2406e.product.dao.OrderItemDAO;
import com.t2406e.product.dao.ProductDAO;
import com.t2406e.product.model.Order;
import com.t2406e.product.model.OrderItem;
import com.t2406e.product.model.Product;
import com.t2406e.product.model.User;
import com.t2406e.product.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/orders")
    public class OrderServlet extends HttpServlet {

        private OrderDAO orderDAO = new OrderDAO();
        private OrderItemDAO itemDAO = new OrderItemDAO();
        private ProductDAO productDAO = new ProductDAO();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            HttpSession session = req.getSession(false);
            User user = (User) session.getAttribute("loginUser");
            // LẤY USER ĐANG ĐĂNG NHẬP
            try {
                List<Order> orders = orderDAO.getByUserId(user.getId());
            // LẤY DANH SÁCH ĐƠN HÀNG CỦA USER
                // USER CHỈ ĐƯỢC XEM ĐƠN CỦA MÌNH getByUserId

                // Map<Order, List<OrderItem>>
                Map<Order, List<OrderItem>> orderMap = new LinkedHashMap<>();
            // TẠO CẤU TRÚC DỮ LIỆU, DÙNG LINKEDHASHMAP ĐỂ GIỮ THỨ TỰ ĐƠN HÀNG

                for (Order o : orders) {
                    List<OrderItem> items = itemDAO.getByOrderId(o.getId());
            // LẤY CHI TIẾT ĐƠN HÀNG THEO MỖI ĐƠN

            // Gắn thêm Product vào request scope
            // mỖI OrderItem chỉ có productId, price, quantity
            // Nên khi frontend cần ảnh, tên thì phải gắn cả product vào
                    for (OrderItem item : items) {
                        Product p = productDAO.getById(item.getProductId());
                        req.setAttribute("product_" + item.getProductId(), p);
                    }

                    orderMap.put(o, items);
            // GỘP ĐƠN HÀNG VÀ CHI TIẾT ĐƠN VÀO MAP, TẠO CẤU TRÚC HOÀN CHỈNH CHO VIEW
                }

                req.setAttribute("orderMap", orderMap);
                req.getRequestDispatcher("/order-list.jsp").forward(req, resp);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        /* ===== USER HỦY ĐƠN ===== */
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws IOException, ServletException {

            int orderId = Integer.parseInt(req.getParameter("orderId"));

            try (Connection conn = DBConnection.getConnection()) {
                conn.setAutoCommit(false);

                Map<Integer, Integer> items =
                        itemDAO.getItemsForRestore(conn, orderId);

                for (var e : items.entrySet()) {
                    productDAO.restoreStock(conn, e.getKey(), e.getValue());
                }

                orderDAO.updateStatus(conn, orderId, "CANCELLED");

                conn.commit();
                resp.sendRedirect(req.getContextPath() + "/orders");

            } catch (Exception e) {
                throw new ServletException("Hủy đơn thất bại", e);
            }
        }
    }


