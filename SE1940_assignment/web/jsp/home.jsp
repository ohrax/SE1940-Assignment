<%-- 
    Document   : home
    Created on : Mar 2, 2025, 12:12:00 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard - Leave Management</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
    <c:if test="${empty sessionScope.loggedInUser}">
        <c:redirect url="/login"/>
    </c:if>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/home">Leave Management</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="/home">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="/request/create">Create Request</a></li>
                    <li class="nav-item"><a class="nav-link" href="/request/list/personal">My Requests</a></li>
                    <li class="nav-item"><a class="nav-link" href="/agenda">Agenda</a></li>
                    <c:if test="${requestScope.isHigherUp}">
                        <li class="nav-item"><a class="nav-link" href="/request/list/employee">Employee Requests</a></li>
                        <li class="nav-item"><a class="nav-link" href="/request/review">Review Requests</a></li>
                    </c:if>
                    <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container mt-4">
        <h2>Welcome, ${sessionScope.loggedInUser.fullname}</h2>
        <p>Quick access to your leave management options above.</p>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
