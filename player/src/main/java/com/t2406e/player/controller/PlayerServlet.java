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

    // ===================== GET =====================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            // ===== EDIT (player_index) =====
            if ("edit".equals(action)) {
                int piId = Integer.parseInt(req.getParameter("id"));

                req.setAttribute("player", playerIndexDAO.findById(piId));
                req.setAttribute("indexers", indexerDAO.findAll());

                req.getRequestDispatcher("/player-form.jsp").forward(req, resp);
                return;
            }

            // ===== DELETE (player_index) =====
            if ("delete".equals(action)) {
                int piId = Integer.parseInt(req.getParameter("id"));
                playerIndexDAO.deleteById(piId);
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

    // ===================== POST =====================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        try {
            // ===== UPDATE (chỉ update index + value) =====
            if ("update".equals(action)) {

                int piId = Integer.parseInt(req.getParameter("id"));
                int playerId = Integer.parseInt(req.getParameter("playerId"));

                String name = req.getParameter("name");
                String fullName = req.getParameter("fullName");
                String age = req.getParameter("age");

                int indexId = Integer.parseInt(req.getParameter("indexId"));
                float value = Float.parseFloat(req.getParameter("value"));

                // update PLAYER
                playerDAO.updatePlayer(playerId, name, fullName, age);

                // update PLAYER_INDEX
                playerIndexDAO.update(piId, indexId, value);

                resp.sendRedirect("player");
                return;
            }


            // ===== CREATE =====
            String name = req.getParameter("name");
            String fullName = req.getParameter("fullName");
            String age = req.getParameter("age");
            int indexId = Integer.parseInt(req.getParameter("indexId"));
            float value = Float.parseFloat(req.getParameter("value"));

            // VALIDATION CREATE
            if (name == null || name.isBlank()
                    || fullName == null || fullName.isBlank()
                    || age == null || !age.matches("\\d+")) {

                resp.sendRedirect("player?error=validation");
                return;
            }

            Player p = new Player();
            p.setName(name);
            p.setFullName(fullName);
            p.setAge(age);

            // INSERT PLAYER
            int playerId = playerDAO.insert(p);

            // INSERT PLAYER_INDEX
            playerIndexDAO.insert(playerId, indexId, value);

            resp.sendRedirect("player");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("player?error=true");
        }
    }
}
