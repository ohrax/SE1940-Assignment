/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LeaveRequestDAO;
import model.LeaveRequest;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AgendaServlet extends HttpServlet {
    private LeaveRequestDAO requestDAO = new LeaveRequestDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }
        String pathInfo = req.getPathInfo() != null ? req.getPathInfo() : "";
        switch (pathInfo) {
            case "/personal":
                try {
                    List<LeaveRequest> requests = requestDAO.getPersonalRequests(user.getUserId());
                    req.setAttribute("requests", requests);
                    req.getRequestDispatcher("/agenda.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            case "/employee":
                if (!user.getRoleName().equals("department_manager") && !user.getRoleName().equals("admin")) {
                    resp.sendError(403, "Forbidden");
                    return;
                }
                try {
                    List<LeaveRequest> requests = requestDAO.getEmployeeRequests(user.getUserId(), user.getRoleName());
                    req.setAttribute("requests", requests);
                    req.getRequestDispatcher("/agenda.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            default:
                resp.sendError(404, "Not Found");
        }
    }
}
