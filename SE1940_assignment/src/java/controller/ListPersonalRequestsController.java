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
import model.AbsentRequest;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "ListPersonalRequestsController", urlPatterns = {"/request/list/personal"})
public class ListPersonalRequestsController extends HttpServlet {

    private final AbsentRequestDAO absentRequestDAO = new AbsentRequestDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        List<AbsentRequest> requests = (List<AbsentRequest>) absentRequestDAO.get(userId);
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/jsp/listPersonalRequests.jsp").forward(request, response);
    }
}
