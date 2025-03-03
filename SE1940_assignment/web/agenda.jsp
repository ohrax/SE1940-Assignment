<%-- 
    Document   : agenda
    Created on : Mar 3, 2025, 11:05:31 PM
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head><title>Agenda</title></head>
    <body>
        <h1>Agenda</h1>
        <table border="1">
            <tr><th>Personnel</th><th>1/1</th><th>2/1</th><th>3/1</th><!-- Add more dates --></tr>
                    <c:forEach var="request" items="${requests}">
                <tr>
                    <td>${request.createdBy}</td>
                    <td style="background-color: ${request.fromDate <= '2025-01-01' && request.toDate >= '2025-01-01' ? 'red' : 'green'}"></td>
                    <td style="background-color: ${request.fromDate <= '2025-01-02' && request.toDate >= '2025-01-02' ? 'red' : 'green'}"></td>
                    <td style="background-color: ${request.fromDate <= '2025-01-03' && request.toDate >= '2025-01-03' ? 'red' : 'green'}"></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
