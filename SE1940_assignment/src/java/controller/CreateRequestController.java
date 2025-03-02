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

@WebServlet(name = "CreateRequestController", urlPatterns = {"/request/create"})
public class CreateRequestController extends HttpServlet {

    private final AbsentRequestDAO absentRequestDAO = new AbsentRequestDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/createRequest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        LocalDateTime fromDate = LocalDateTime.parse(request.getParameter("fromDate"));
        LocalDateTime toDate = LocalDateTime.parse(request.getParameter("toDate"));
        int createdBy = Integer.parseInt(request.getParameter("createdBy"));

        AbsentRequest requestObj = new AbsentRequest(0, title, fromDate, toDate, createdBy, "In Progress", 0, LocalDateTime.now(), LocalDateTime.now());
        absentRequestDAO.insert(requestObj);

        response.sendRedirect("/request/list/personal");
    }
}
