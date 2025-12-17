package com.t2406e.javaee.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        // 1. Check login
        if (session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. Lấy cart từ session
        List<String> cart = (List<String>) session.getAttribute("myCart");

        // 3. Nếu chưa có → tạo mới
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("myCart", cart);
        }

        String action = req.getParameter("action");
        String item = req.getParameter("item");

        // ADD ITEM
        if (item != null && !item.isEmpty() && action == null) {
            cart.add(item);
        }

        // REMOVE ITEM
        if ("remove".equals(action) && item != null) {
            cart.remove(item); // xóa 1 item đầu tiên trùng tên
        }

        // 4. Thêm sản phẩm
//        String newItem = req.getParameter("item");
//        if (newItem != null && !newItem.isEmpty()) {
//            cart.add(newItem);
//        }

        // 5. Gửi cart sang JSP
        req.setAttribute("cart", cart);

        // 6. Forward (CHỈ GỌI 1 LẦN, CUỐI CÙNG)
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}
