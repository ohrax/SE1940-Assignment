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

@WebServlet(name = "ReviewRequestController", urlPatterns = {"/request/review"})
public class ReviewRequestController extends HttpServlet {

    private final AbsentRequestDAO absentRequestDAO = new AbsentRequestDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        AbsentRequest requestObj = absentRequestDAO.get(requestId);
        request.setAttribute("request", requestObj);
        request.getRequestDispatcher("/jsp/reviewRequest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String status = request.getParameter("status");
        int processedBy = Integer.parseInt(request.getParameter("processedBy"));

        AbsentRequest requestObj = absentRequestDAO.get(requestId);
        if (requestObj != null) {
            requestObj.setStatus(status);
            requestObj.setProcessedBy(processedBy);
            requestObj.setUpdatedDate(LocalDateTime.now());
            absentRequestDAO.update(requestObj);
        }
        response.sendRedirect("/request/list/employee");
    }
}
