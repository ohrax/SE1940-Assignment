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
import dao.UserDAO;
import dao.UserRoleDAO;
import java.util.ArrayList;
import java.util.stream.Collectors;
import model.AbsentRequest;
import model.User;
import model.UserRole;

@WebServlet(name = "AgendaController", urlPatterns = {"/agenda"})
public class AgendaController extends HttpServlet {

    private final AbsentRequestDAO absentRequestDAO = new AbsentRequestDAO();
    private final UserRoleDAO userRoleDAO = new UserRoleDAO();
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        boolean isHigherUp = userRoleDAO.isUserHigherUp(user.getUserId());
        List<AbsentRequest> requests;
        
        if (isHigherUp) {
            List<User> teamMembers = userDAO.list().stream()
                .filter(u -> u.getDirectManagerId() == user.getUserId())
                .collect(Collectors.toList());
            requests = new ArrayList<>();
            for (User member : teamMembers) {
                requests.addAll(absentRequestDAO.getListRequestsByUser(member.getUserId()));
            }
        } else {
            requests = absentRequestDAO.getListRequestsByUser(user.getUserId());
        }
        
        request.setAttribute("requests", requests);
        request.setAttribute("isHigherUp", isHigherUp);
        request.getRequestDispatcher("/jsp/agenda.jsp").forward(request, response);
    }
}


    
