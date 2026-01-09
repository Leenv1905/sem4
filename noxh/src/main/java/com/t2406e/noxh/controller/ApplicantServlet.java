package com.t2406e.noxh.controller;

import com.t2406e.noxh.dao.ApplicantDAO;
import com.t2406e.noxh.dao.WinnerDAO;
import com.t2406e.noxh.model.Applicant;
import com.t2406e.noxh.model.Winner;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

@WebServlet("/applicant")
public class ApplicantServlet extends HttpServlet {

    private ApplicantDAO applicantDAO;
    private WinnerDAO winnerDAO;

    @Override
    public void init() {
        applicantDAO = new ApplicantDAO();
        winnerDAO = new WinnerDAO();
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        String action = req.getParameter("action");
//
//        if ("create".equals(action)) {
//            req.getRequestDispatcher("/applicant-form.jsp").forward(req, resp);
//        } else if ("edit".equals(action)) {
//            int id = Integer.parseInt(req.getParameter("id"));
//            req.setAttribute("applicant", applicantDAO.findById(id));
//            req.getRequestDispatcher("/applicant-form.jsp").forward(req, resp);
//        } else if ("delete".equals(action)) {
//            int id = Integer.parseInt(req.getParameter("id"));
//            req.setAttribute("applicant", applicantDAO.findById(id));
//            req.getRequestDispatcher("/applicant-delete.jsp").forward(req, resp);
//        } else if ("draw".equals(action)) {
//            drawWinner(resp);
//        } else if ("winners".equals(action)) {
//            req.setAttribute("winners", winnerDAO.findAll());
//            req.getRequestDispatcher("/winner-list.jsp").forward(req, resp);
//        } else {
//            req.setAttribute("applicants", applicantDAO.findAll());
//            req.getRequestDispatcher("/applicant-list.jsp").forward(req, resp);
//        }
//    }
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    String action = req.getParameter("action");

    if (action == null) {
        // LIST APPLICANTS
        List<Applicant> applicants = applicantDAO.findAll();
        req.setAttribute("applicants", applicants);
        req.getRequestDispatcher("/applicant-list.jsp").forward(req, resp);
        return;
    }

    switch (action) {
        case "create":
            req.getRequestDispatcher("/applicant-form.jsp").forward(req, resp);
            break;

        case "edit": {
            int id = Integer.parseInt(req.getParameter("id"));
            Applicant applicant = applicantDAO.findById(id);
            req.setAttribute("applicant", applicant);
            req.getRequestDispatcher("/applicant-form.jsp").forward(req, resp);
            break;
        }

        case "delete": {
            int id = Integer.parseInt(req.getParameter("id"));
            Applicant applicant = applicantDAO.findById(id);
            req.setAttribute("applicant", applicant);
            req.getRequestDispatcher("/applicant-delete.jsp").forward(req, resp);
            break;
        }

        case "view": {
            int id = Integer.parseInt(req.getParameter("id"));
            Applicant applicant = applicantDAO.findById(id);
            req.setAttribute("applicant", applicant);
            req.getRequestDispatcher("/applicant-view.jsp").forward(req, resp);
            break;
        }

        case "draw":
            drawWinner(resp);
            break;

//        case "winners": {
//            List<Winner> winners = winnerDAO.findAll();
//            req.setAttribute("winners", winners);
//            req.getRequestDispatcher("/winner-list.jsp").forward(req, resp);
//            break;
//        }
        case "winners": {
            req.setAttribute("winners", winnerDAO.findWinnerDetails());
            req.getRequestDispatcher("/winner-list.jsp").forward(req, resp);
            break;
        }
        // RESET BỐC THĂM
        case "reset": {
            winnerDAO.deleteAll();
            resp.sendRedirect("applicant");
            break;
        }

        default:
            List<Applicant> applicants = applicantDAO.findAll();
            req.setAttribute("applicants", applicants);
            req.getRequestDispatcher("/applicant-list.jsp").forward(req, resp);
    }
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        if ("create".equals(action)) {
            Applicant a = new Applicant();
            a.setName(req.getParameter("name"));
            a.setStatus(req.getParameter("status") != null);
            applicantDAO.insert(a);

        } else if ("edit".equals(action)) {
            Applicant a = new Applicant();
            a.setId(Integer.parseInt(req.getParameter("id")));
            a.setName(req.getParameter("name"));
            a.setStatus(req.getParameter("status") != null);
            applicantDAO.update(a);

        } else if ("delete".equals(action)) {
            applicantDAO.delete(Integer.parseInt(req.getParameter("id")));
        }

        resp.sendRedirect("applicant");
    }

    // ================== BỐC THĂM ==================
    private void drawWinner(HttpServletResponse resp) throws IOException {

        if (winnerDAO.hasWinner()) {
            resp.sendRedirect("applicant?action=winners");
            return;
        }

        // ✅ CHỈ LẤY HỒ SƠ HỢP LỆ
        List<Applicant> applicants = applicantDAO.findValidApplicants();

        SecureRandom random = new SecureRandom();
        Collections.shuffle(applicants, random);

        int limit = Math.min(500, applicants.size());
        for (int i = 0; i < limit; i++) {
            Winner w = new Winner();
            w.setApplicantId(applicants.get(i).getId());
            winnerDAO.insert(w);
        }

        resp.sendRedirect("applicant?action=winners");
    }

}
