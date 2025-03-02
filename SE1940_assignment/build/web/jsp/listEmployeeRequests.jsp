<%-- 
    Document   : listEmployeeRequests
    Created on : Mar 1, 2025, 4:18:20 PM
    Author     : admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Requests</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <c:if test="${empty sessionScope.loggedInUser || !requestScope.isHigherUp}">
        <c:redirect url="/login"/>
    </c:if>
    <div class="container mt-4">
        <h2>Employee Requests</h2>
        <table class="table table-bordered text-center">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Created By</th>
                    <th>Status</th>
                    <th>Processed By</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="request" items="${requests}">
                    <tr>
                        <td>${request.title}</td>
                        <td>${request.fromDate}</td>
                        <td>${request.toDate}</td>
                        <td>${request.createdBy}</td>
                        <td>${request.status}</td>
                        <td>${request.processedBy == 0 ? "Pending" : request.processedBy}</td>
                        <td>
                            <c:if test="${request.status == 'In Progress'}">
                                <a href="/request/review?requestId=${request.requestId}" class="btn btn-sm btn-primary">Review</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
