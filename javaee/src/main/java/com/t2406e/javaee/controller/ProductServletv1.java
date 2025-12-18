//package com.t2406e.javaee.controller;
//
//import com.t2406e.javaee.dao.ProductDAO;
//import com.t2406e.javaee.model.Product;
//import jakarta.servlet.ServletConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//
//// Define URL mapping without web.xml
//// Truy xuất thông qua @, không cần viết trên web.xml
//@WebServlet(name = "productServlet", urlPatterns = {"/product"})
////@WebServlet("/product")
//
//public class ProductServletv1 extends HttpServlet {
//    private ProductDAO productDAO;
//    // Khởi tạo 1 model
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        productDAO = new ProductDAO(); // Chính thức cấp phát bộ nhớ
//    }
//
////    @Override
////    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.service(req, resp);
////    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.doGet(req, resp);
//        req.getRequestDispatcher("/create-product.jsp").forward(req, resp);
//// Phương thước doGet/ Post chỉ có thể gọi đến Controller
//// vậy nên ở đây ko có controller tương ứng cho doGet, nên phải ấy tạm create-product.jsp
//// Nếu để là /product thì Tomcat sẽ auto gọi đến product.jsp ->sai
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.doPost(req, resp);
//        createProduct(req, resp); // Bước 2, call Model
//        // Để không cần trển khai createProduct ở đây thì tách ra bên dưới
//        // Bước 3
//        //SetAttibute
//        //RequestDispatcher...
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.doPut(req, resp);
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.doDelete(req, resp);
//    }
//
//    /* ================== CREATE ================== */
//    private void createProduct(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//
//        // Bước 2: Lấy dữ liệu từ form
//        String name = req.getParameter("name");
//        String priceStr = req.getParameter("price");
//        String desscription = req.getParameter("desscription");
//
//        // Bước 3: Convert kiểu
//        double price = Double.parseDouble(priceStr);
//
//        // Bước 4: Đóng gói vào Model
//        Product product = new Product();
//        product.setName(name);
//        product.setPrice(price);
//        product.setDesscription(desscription);
//
//        // Bước 5: Gọi DAO
//        productDAO.insert(product);
//
//        // Bước 6: Redirect
//        resp.sendRedirect(req.getContextPath() + "/product");
//
//    }
//
//    @Override
//    public void destroy() {
////        super.destroy();
//    }
//}
