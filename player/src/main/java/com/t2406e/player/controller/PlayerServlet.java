package com.t2406e.player.controller;

import com.t2406e.player.dao.*;
import com.t2406e.player.model.Player;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/player")
public class PlayerServlet extends HttpServlet {

    private PlayerDAO playerDAO = new PlayerDAO();
    private IndexerDAO indexerDAO = new IndexerDAO();
    private PlayerIndexDAO playerIndexDAO = new PlayerIndexDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            // ===== EDIT =====
            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("player", playerDAO.findById(id));
                req.setAttribute("indexers", indexerDAO.findAll());
                req.getRequestDispatcher("/player-form.jsp").forward(req, resp);
                return;
            }

            // ===== DELETE =====
            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));

                // ❗ XÓA BẢNG TRUNG GIAN TRƯỚC
                playerIndexDAO.deleteByPlayerId(id);
                playerDAO.delete(id);

                resp.sendRedirect("player");
                return;
            }

            // ===== LIST =====
            req.setAttribute("players", playerDAO.findAllWithIndex());
            req.setAttribute("indexers", indexerDAO.findAll());
            req.getRequestDispatcher("/player.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        try {
            String name = req.getParameter("name");
            String fullName = req.getParameter("fullName");
            String age = req.getParameter("age");
            int indexId = Integer.parseInt(req.getParameter("indexId"));
            float value = Float.parseFloat(req.getParameter("value"));

            // ===== VALIDATION =====
            if (name == null || name.isBlank()
                    || fullName == null || fullName.isBlank()
                    || age == null || !age.matches("\\d+")) {

                resp.sendRedirect("player?error=validation");
                return;
            }

            // ===== UPDATE =====
            if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));

                Player p = new Player();
                p.setPlayerId(id);
                p.setName(name);
                p.setFullName(fullName);
                p.setAge(age);

                playerDAO.update(p);

            }
            // ===== CREATE =====
            else {
                Player p = new Player();
                p.setName(name);
                p.setFullName(fullName);
                p.setAge(age);

                // 🔑 INSERT PLAYER
                int playerId = playerDAO.insert(p);

                // 🔑 INSERT PLAYER_INDEX
                playerIndexDAO.insert(playerId, indexId, value);
            }

            resp.sendRedirect("player");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("player?error=true");
        }
    }
}
