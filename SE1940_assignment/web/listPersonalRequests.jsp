<%-- 
    Document   : listRequests
    Created on : Feb 23, 2025, 11:18:47 PM
    Author     : admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Personal Requests</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
    <c:if test="${empty sessionScope.user}">
        <c:redirect url="login.jsp"/>
    </c:if>
    <div class="container mt-4">
        <h2 class="text-center">Personal Requests</h2>
        <table class="table table-bordered text-center">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Created By</th>
                    <th>Status</th>
                    <th>Processed By</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="request" items="${requestList}">
                <c:if test="${request.createdBy eq sessionScope.user.username}">
                    <tr>
                        <td>${request.title}</td>
                        <td>${request.fromDate}</td>
                        <td>${request.toDate}</td>
                        <td>${request.createdBy}</td>
                        <td>${request.status}</td>
                        <td>${request.processedBy}</td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>