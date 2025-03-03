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
import java.sql.Date;
import java.util.List;

public class RequestServlet extends HttpServlet {
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
            case "/create":
                req.getRequestDispatcher("/request_create.jsp").forward(req, resp);
                break;
            case "/list/personal":
                try {
                    List<LeaveRequest> requests = requestDAO.getPersonalRequests(user.getUserId());
                    req.setAttribute("requests", requests);
                    req.getRequestDispatcher("/request_list.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            case "/list/employee":
                if (!user.getRoleName().equals("leader") && !user.getRoleName().equals("department_manager") && !user.getRoleName().equals("admin")) {
                    resp.sendError(403, "Forbidden");
                    return;
                }
                try {
                    List<LeaveRequest> requests = requestDAO.getEmployeeRequests(user.getUserId(), user.getRoleName());
                    req.setAttribute("requests", requests);
                    req.getRequestDispatcher("/request_list.jsp").forward(req, resp);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            default:
                resp.sendError(404, "Not Found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }
        String pathInfo = req.getPathInfo();
        if ("/create".equals(pathInfo)) {
            String title = req.getParameter("title");
            Date fromDate = Date.valueOf(req.getParameter("from_date"));
            Date toDate = Date.valueOf(req.getParameter("to_date"));
            String reason = req.getParameter("reason");
            LeaveRequest request = new LeaveRequest(0, user.getUserId(), title, fromDate, toDate, reason, "Inprogress", null);
            try {
                requestDAO.createRequest(request);
                resp.sendRedirect("/request/list/personal");
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }
}
