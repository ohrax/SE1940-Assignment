/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.AbsentRequestDAO;
import model.AbsentRequest;
import model.User;

@WebServlet(name = "ListPersonalRequestsController", urlPatterns = {"/request/list/personal"})
public class ListPersonalRequestsController extends HttpServlet {
    private final AbsentRequestDAO absentRequestDAO = new AbsentRequestDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int userId = user.getUserId();
        List<AbsentRequest> requests = absentRequestDAO.getListRequestsByUser(userId);
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/jsp/listPersonalRequests.jsp").forward(request, response);
    }
}
