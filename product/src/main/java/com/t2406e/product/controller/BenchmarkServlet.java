package com.t2406e.product.controller;

import com.t2406e.product.dao.ProductDAO;
import com.t2406e.product.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "benchmarkServlet", urlPatterns = {"/benchmark"})
public class BenchmarkServlet extends HttpServlet {

    private static final int LOOP = 300_000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Map<String, String> results = new LinkedHashMap<>();

        /* ================= HashMap ================= */
        Map<Integer, Integer> hash = new HashMap<>();
        long start = System.nanoTime();
        for (int i = 0; i < LOOP; i++) hash.put(i, i);
        long end = System.nanoTime();
        results.put("HashMap Put", (end - start) / 1_000_000 + " ms");

        /* ================= LinkedHashMap ================= */
        Map<Integer, Integer> linked = new LinkedHashMap<>();
        start = System.nanoTime();
        for (int i = 0; i < LOOP; i++) linked.put(i, i);
        end = System.nanoTime();
        results.put("LinkedHashMap Put", (end - start) / 1_000_000 + " ms");

        /* ================= DAO getAll ================= */
        ProductDAO dao = new ProductDAO();
        start = System.nanoTime();
        List<Product> list = dao.getAll();
        end = System.nanoTime();
        results.put("productDAO.getAll()", (end - start) / 1_000_000 + " ms");

        /* ================= DAO getById ================= */
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) dao.getById(3);
        end = System.nanoTime();
        results.put("productDAO.getById() x1000", (end - start) / 1_000_000 + " ms");

        req.setAttribute("results", results);
        req.getRequestDispatcher("/benchmark.jsp").forward(req, resp);
    }
}
