/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.AbsentRequestDAO;
import dao.UserDivisionDAO;
import jakarta.servlet.http.HttpSession;
import model.AbsentRequest;
import java.time.LocalDateTime;
import model.User;

@WebServlet(name = "CreateRequestController", urlPatterns = {"/request/create"})
public class CreateRequestController extends HttpServlet {

    private final AbsentRequestDAO absentRequestDAO = new AbsentRequestDAO();
    private final UserDivisionDAO userDivisionDAO = new UserDivisionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            response.sendRedirect("/login");
            return;
        }
        request.setAttribute("userDivision", userDivisionDAO.getByUserId(user.getUserId()));
        request.getRequestDispatcher("/jsp/createRequest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String title = request.getParameter("title");
        LocalDateTime fromDate = LocalDateTime.parse(request.getParameter("fromDate"));
        LocalDateTime toDate = LocalDateTime.parse(request.getParameter("toDate"));
        int createdBy = user.getUserId();
        String reason = request.getParameter("reason");

        AbsentRequest requestObj = new AbsentRequest(0, title, fromDate, toDate, createdBy, "In Progress", 0, reason, LocalDateTime.now(), LocalDateTime.now());
        absentRequestDAO.insert(requestObj);

        response.sendRedirect(request.getContextPath() + "/request/list/personal?userId=" + createdBy);
    }
}
