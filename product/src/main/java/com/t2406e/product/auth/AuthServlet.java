package com.t2406e.product.auth;

import com.t2406e.product.dao.UserDAO;
import com.t2406e.product.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

    @WebServlet("/auth")
    public class AuthServlet extends HttpServlet {

        private UserDAO userDAO = new UserDAO();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            String action = req.getParameter("action");

            if ("login".equals(action)) {
                req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);

            } else if ("register".equals(action)) {
                req.getRequestDispatcher("/auth/register.jsp").forward(req, resp);

            } else if ("logout".equals(action)) {
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
            }
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            String action = req.getParameter("action");

            if ("login".equals(action)) {
                login(req, resp);

            } else if ("register".equals(action)) {
                register(req, resp);
            }
        }

        private void login(HttpServletRequest req, HttpServletResponse resp)
                throws IOException, ServletException {

            String username = req.getParameter("username");
            String password = req.getParameter("password");

            User user = userDAO.login(username, password);

            if (user != null) {
                req.getSession().setAttribute("loginUser", user);
                resp.sendRedirect(req.getContextPath() + "/product");
                //AuthServlet KHÔNG redirect ngược lại /auth khi đã login
            } else {
                req.setAttribute("error", "Invalid username or password");
                req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
            }
        }

        private void register(HttpServletRequest req, HttpServletResponse resp)
                throws IOException, ServletException {

            User user = new User();
            user.setUsername(req.getParameter("username"));
            user.setPassword(req.getParameter("password"));
            user.setFullName(req.getParameter("fullName"));

            userDAO.register(user);
            resp.sendRedirect(req.getContextPath() + "/auth?action=login");
        }
    }


