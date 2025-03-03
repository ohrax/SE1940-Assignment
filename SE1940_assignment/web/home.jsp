<%-- 
    Document   : home
    Created on : Mar 3, 2025, 11:05:00 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
    <head><title>Home</title></head>
    <body>
        <h1>Welcome, ${sessionScope.user.username}</h1>
        <h3>Role: ${sessionScope.user.roleName}</h3>
        <ul>
            <c:forEach var="option" items="${options}">
                <li><a href="/LeaveManagement/${option}">${option}</a></li>
            </c:forEach>
        </ul>
    </body>
</html>
