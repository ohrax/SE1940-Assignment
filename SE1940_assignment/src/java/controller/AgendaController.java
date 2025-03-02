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
import dao.UserRoleDAO;
import model.AbsentRequest;
import model.User;
import model.UserRole;

@WebServlet(name = "AgendaController", urlPatterns = {"/agenda"})
public class AgendaController extends HttpServlet {

    private final AbsentRequestDAO absentRequestDAO = new AbsentRequestDAO();
    private final UserRoleDAO userRoleDAO = new UserRoleDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        
        if (user == null) {
            response.sendRedirect("/login");
            return;
        }
        
        boolean isHigherUp = userRoleDAO.isUserHigherUp(user.getUserId());
        List<AbsentRequest> requests;
        String viewType = request.getParameter("view");
        if ("employee".equals(viewType) && isHigherUp) {
            requests = absentRequestDAO.list();
        } else {
            requests = absentRequestDAO.getListRequestsByUser(user.getUserId());
        }
        
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/jsp/agenda.jsp").forward(request, response);
    }
}


    
