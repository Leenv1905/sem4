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
        if (uri.startsWith(contextPath + "/auth")) {
            chain.doFilter(req, res);
            return;
        }

        // ===== 2. BỎ QUA RESOURCE TĨNH =====
        if (uri.endsWith(".css") || uri.endsWith(".js")
                || uri.endsWith(".png") || uri.endsWith(".jpg")
                || uri.endsWith(".jpeg") || uri.endsWith(".gif")) {
            chain.doFilter(req, res);
            return;
        }

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

        // ===== 4. CHO ĐI TIẾP =====
        chain.doFilter(req, res);
    }
}
