package com.t2406e.product.auth;

import com.t2406e.product.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        // ===== 1. BỎ QUA AUTH =====
        if (
                uri.equals(contextPath + "/") ||
                        uri.startsWith(contextPath + "/view-product") ||
//                        uri.startsWith(contextPath + "/product") ||
                        uri.startsWith(contextPath + "/auth") ||
                        uri.endsWith(".css") || uri.endsWith(".js") ||
                        uri.endsWith(".png") || uri.endsWith(".jpg") ||
                        uri.endsWith(".jpeg") || uri.endsWith(".gif")
        ) {
            chain.doFilter(req, res);
            return;
        }

//        // ===== 2. BỎ QUA RESOURCE TĨNH =====
//        if (uri.endsWith(".css") || uri.endsWith(".js")
//                || uri.endsWith(".png") || uri.endsWith(".jpg")
//                || uri.endsWith(".jpeg") || uri.endsWith(".gif")) {
//            chain.doFilter(req, res);
//            return;
//        }

        // == CHẶN XÓA NẾU KHÔNG PHẢI ADMIN==
//        if (uri.contains("action=delete")) {
//            User user = (User) request.getSession().getAttribute("loginUser");
//            if (!"ADMIN".equals(user.getRole())) {
//                response.sendError(403);
//                return;
//            }
//        }


        // ===== 3. KIỂM TRA ĐĂNG NHẬP =====
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect(contextPath + "/auth?action=login");
            return;
        }

        User user = (User) session.getAttribute("loginUser");
        String role = user.getRole();
        //Luwuu lại role để kiểm tra quyền

        /* ================= ROLE RULES ================= */

        // Chỉ Admin được truy cập vào /product
        if (uri.startsWith(contextPath + "/product")) {
            if (!"ADMIN".equals(role)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN,
                        "ADMIN role required");
                return;
            }
        }

        // Chỉ USER được teem sp vào cart
        if (uri.startsWith(contextPath + "/cart")) {
            if (!"USER".equals(role)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN,
                        "USER role required");
                return;
            }
        }

        // Orders: cả USER và ADMIN đều xem được
        if (uri.startsWith(contextPath + "/orders")) {
            if (!("USER".equals(role) || "ADMIN".equals(role))) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        // chỉ admin được truy cập end pont này
        if (uri.startsWith(contextPath + "/admin")) {
            if (!"ADMIN".equals(role)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        // ===== 4. CHO ĐI TIẾP =====
        chain.doFilter(req, res);
    }
}
