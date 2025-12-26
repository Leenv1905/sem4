package com.t2406e.product.controller;

import com.t2406e.product.dao.ProductDAO;
import com.t2406e.product.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "cartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            showCart(req, resp);
            return;
        }

        switch (action) {
            case "add":
                addToCart(req, resp);
                break;
            case "update":
                updateCart(req, resp);
                break;
            case "remove":
                removeFromCart(req, resp);
                break;
            case "clear":
                clearCart(req, resp);
                break;
            default:
                showCart(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp); // xử lý giống GET
    }

    /* ================= SHOW CART ================= */
    private void showCart(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        List<Product> cartProducts = new ArrayList<>();
        double totalPrice = 0;
        int totalQuantity = 0;

        if (cart != null) {
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
                Product p = productDAO.getById(entry.getKey());
                if (p != null) {
                    int qty = entry.getValue();
                    p.setQuantity(qty); // quantity trong giỏ
                    cartProducts.add(p);
                    totalQuantity += qty;
                    totalPrice += p.getPrice() * qty;
                }
            }
        }

        req.setAttribute("cartProducts", cartProducts);
        req.setAttribute("totalQuantity", totalQuantity);
        req.setAttribute("totalPrice", totalPrice);

        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    /* ================= ADD ================= */
//    private void addToCart(HttpServletRequest req, HttpServletResponse resp)
//            throws IOException {
//
//        int id = Integer.parseInt(req.getParameter("id"));
//        HttpSession session = req.getSession();
//        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
//
//        if (cart == null) {
//            cart = new HashMap<>();
//        }
//
//        cart.put(id, cart.getOrDefault(id, 0) + 1);
//
//        session.setAttribute("cart", cart);
//        resp.sendRedirect(req.getContextPath() + "/cart");
//    }

    // CÁCH 2 trả về thông báo
    private void addToCart(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        int id = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart == null) {
//            cart = new HashMap<>();
            cart = new LinkedHashMap<>(); // giữ thứ tự thêm sản phẩm
        }

//        cart.put(id, cart.getOrDefault(id, 0) + 1);

// ==== ĐO THỜI GIAN THÊM 1 SẢN PHẨM VÀO GIỎ HÀNG ====
        long start = System.nanoTime();
        cart.put(id, cart.getOrDefault(id, 0) + 1);
        long end = System.nanoTime();
        System.out.println("Thời gian thêm vào cart: " + (end - start) + " ns");
// ==== ĐO THỜI GIAN THÊM 1 SẢN PHẨM VÀO GIỎ HÀNG ====

        session.setAttribute("cart", cart);

        /* ================== LOG DEMO ================== */
        System.out.println("=== CART ORDER (LinkedHashMap) ===");
        cart.forEach((k, v) -> System.out.println("ProductID=" + k + ", Qty=" + v));
        System.out.println("==================================");

        // Lấy lại danh sách sản phẩm để hiển thị
        List<Product> products = productDAO.getAll();
        req.setAttribute("products", products);
        req.setAttribute("message", "Sản phẩm đã được thêm vào giỏ hàng thành công!");

        req.getRequestDispatcher("/product-list.jsp").forward(req, resp);
    }


    /* ================= UPDATE ================= */
    private void updateCart(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart != null) {
            if (quantity > 0) {
                cart.put(id, quantity);
            } else {
                cart.remove(id);
            }
            session.setAttribute("cart", cart);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    /* ================= REMOVE ONE ================= */
    private void removeFromCart(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession();
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");

        if (cart != null) {
            cart.remove(id);
            session.setAttribute("cart", cart);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    /* ================= CLEAR ALL ================= */
    private void clearCart(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession();
        session.removeAttribute("cart");

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
