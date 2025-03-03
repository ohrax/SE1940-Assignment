/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }
        List<String> options;
        switch (user.getRoleName()) {
            case "employee":
                options = Arrays.asList("home", "request/create", "request/list/personal", "agenda/personal");
                break;
            case "leader":
                options = Arrays.asList("home", "request/create", "request/list/employee", "review", "agenda/personal");
                break;
            case "department_manager":
                options = Arrays.asList("home", "request/create", "request/list/employee", "agenda/personal", "agenda/employee");
                break;
            case "admin":
                options = Arrays.asList("home", "request/list/employee", "review", "agenda/personal", "agenda/employee");
                break;
            default:
                options = Arrays.asList();
        }
        req.setAttribute("options", options);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
