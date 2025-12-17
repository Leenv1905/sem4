package com.t2406e.javaee.controller;

import com.t2406e.javaee.dao.UserDAO;
import com.t2406e.javaee.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

;

@WebServlet(name = "loginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO(); // khởi tạo DAO 1 lần cặp REQ, REP
    }
    // Nên để ở đây, vì taọ 1 lần Model UserDao() - Tránh lãng phí tài nguyên

    @Override
    // ĐẶT USERDAO() Ở ĐÂY CŨNG ĐƯƠC
    // TUY NHIÊN SẼ LÃNG PHI, VÌ MỖI LẦN DOGET SẼ GOI DEN, GAY RA LANG PHI
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Nếu đã login rồi thì chuyển luôn sang home
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("currentUser") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        // Hiển thị trang login
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
//Step 1
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //Step 2 Call instance of Model
        User user = userDAO.validateLogin(username, password);

        if (user != null) {
            // Login thành công → tạo session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Sai user/pass
            request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
