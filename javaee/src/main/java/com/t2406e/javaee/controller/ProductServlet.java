package com.t2406e.javaee.controller;

import com.t2406e.javaee.dao.ProductDAO;
import com.t2406e.javaee.model.Product;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
//        action ở đây là tham số điều khiển (controller parameter)
//        – nó quyết định Servlet sẽ xử lý nghiệp vụ nào,
//        thay vì bạn phải tạo nhiều Servlet khác nhau.

        if (action == null) {
            listProducts(req, resp);
            return;
        }
            // Ở đây Get không có action nên sẽ gọi ra ListProduct
            // Nê muô tạo mới thì sẽ chuyên đến form thêm mới
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

        List<Product> products = productDAO.getAll();
        req.setAttribute("products", products);
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

        // Cách khác Lấy dữ liệu từ form và Đóng gói Model luôn
//        Product product = new Product();
//        product.setName(req.getParameter("name"));
//        product.setPrice(Double.parseDouble(req.getParameter("price")));
//        product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
//        product.setDescription(req.getParameter("description"));
//        product.setImage(req.getParameter("image"));

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

        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productDAO.getById(id);

        req.setAttribute("product", product);
        req.getRequestDispatcher("/del-product.jsp").forward(req, resp);
    }

    /* ================= DELETE ================= */
    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

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
