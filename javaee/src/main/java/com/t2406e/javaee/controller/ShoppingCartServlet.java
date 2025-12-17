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

        // 1. Lấy session (tạo mới nếu chưa có)
        HttpSession session = req.getSession();

        // Nếu chưa login → quay lại /login
        if (session == null || session.getAttribute("currentUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Nếu đã login → forward sang view cart.jsp
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);


        // 2. Lấy giỏ hàng từ session
        List<String> cart = (List<String>) session.getAttribute("myCart");

        // 3. Nếu chưa có giỏ hàng → tạo mới
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("myCart", cart);
        }

        // 4. Thêm sản phẩm vào giỏ
        String newItem = req.getParameter("item");
        if (newItem != null && !newItem.isEmpty()) {
            cart.add(newItem);
        }

        // 5. Gửi cart sang JSP
        req.setAttribute("cart", cart);

        // 6. Forward sang JSP
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}
