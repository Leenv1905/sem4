package com.t2406e.product.controller;

import com.t2406e.product.dao.ProductDAO;
import com.t2406e.product.model.Product;
import com.t2406e.product.model.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "productServlet", urlPatterns = {"/product"})
    public class ProductServlet extends HttpServlet {

        private ProductDAO productDAO;

        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);
            productDAO = new ProductDAO();
        }

    /* ================== GET ================== */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            listProducts(req, resp);
            return;
        }

        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;

            case "edit":
                showEditForm(req, resp);
                break;

            case "delete":
                showDeleteConfirm(req, resp);
                break;
            case "view":
                viewProduct(req, resp);
                break;

            default:
                listProducts(req, resp);
        }
    }
    /* ================== POST ================== */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("create".equals(action)) {
            createProduct(req, resp);
        } else if ("edit".equals(action)) {
            updateProduct(req, resp);
        }
        else if ("delete".equals(action)) {
            deleteProduct(req, resp);
        }
    }

    /* ================== LIST ================== */
    private void listProducts(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

//        List<Product> products = productDAO.getAll();
// === CHECK THỜI GIAN THỰC THI ===
        long start = System.nanoTime();
        List<Product> products = productDAO.getAll();
        long end = System.nanoTime();
        System.out.println("getAll() mất: " + (end - start) + " ns");
// === CHECK THỜI GIAN THỰC THI ===

        req.setAttribute("products", products);
        //== NẾU NHIỀU TRANG CẦN DÙNG==
//        String view = req.getParameter("view");
//        if (view == null) view = "product-list.jsp";
//        req.getRequestDispatcher("/" + view).forward(req, resp);
        req.getRequestDispatcher("/product-list.jsp").forward(req, resp);
    }

    /* ================== SHOW CREATE FORM ================== */
    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/create-product.jsp").forward(req, resp);
    }

    /* ================== CREATE ================== */
    private void createProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // 1. Lấy dữ liệu từ form
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String description = req.getParameter("description");
        String image = req.getParameter("image");

        // 2. Đóng gói Model
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setImage(image);

        // 3. Gọi DAO
        productDAO.insert(product);

        // 4. Redirect về list
        resp.sendRedirect(req.getContextPath() + "/product");
    }
    /* ================= EDIT ================= */
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productDAO.getById(id);

        req.setAttribute("product", product);
        req.getRequestDispatcher("/edit-product.jsp").forward(req, resp);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Product product = new Product();
        product.setId(Integer.parseInt(req.getParameter("id")));
        product.setName(req.getParameter("name"));
        product.setPrice(Double.parseDouble(req.getParameter("price")));
        product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        product.setDescription(req.getParameter("description"));
        product.setImage(req.getParameter("image"));

        productDAO.update(product);
        resp.sendRedirect(req.getContextPath() + "/product");
    }
    /* ================= SHOW DELETE ================= */

    private void showDeleteConfirm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Không phải ADMIN → không cho mở trang confirm
        HttpSession session = req.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null || !"ADMIN".equals(loginUser.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        // Không phải ADMIN → không cho mở trang confirm

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productDAO.getById(id);

        req.setAttribute("product", product);
        req.getRequestDispatcher("/del-product.jsp").forward(req, resp);
    }

    /* ================= DELETE ================= */
    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);

        // Kiểm soát đăng nhập
        // Không caanf thết vì đã có AuthFilter. Tuy nhiên để code đây để tham khảo
        // Có những chiết lý code sẽ để Servlet tự phòng thủ, đề phòng bị tăắt AuthFilter
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/auth?action=login");
            return;
        }

        User loginUser = (User) session.getAttribute("loginUser");

        // CHỉ admin mới đươ quyền xóa
        if (loginUser == null || !"ADMIN".equals(loginUser.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "You are not allowed to delete product");
            return;
        }

        int id = Integer.parseInt(req.getParameter("id"));
        productDAO.delete(id);

        resp.sendRedirect(req.getContextPath() + "/product");
    }
    /* ================= VIEW ================= */
    private void viewProduct(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productDAO.getById(id);

        if (product == null) {
            resp.sendRedirect(req.getContextPath() + "/product");
            return;
        }

        req.setAttribute("product", product);
        req.getRequestDispatcher("/view-product.jsp").forward(req, resp);
    }


    }
