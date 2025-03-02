<%-- 
    Document   : createRequest
    Created on : Feb 23, 2025, 11:18:37 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="model.Division"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="model.Role"%>
<!DOCTYPE html>
<html>
<head>
    <title>Đơn xin nghỉ phép</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="form-container">
        <h2>Đơn xin nghỉ phép</h2>
        <%
            HttpSession userSession = request.getSession();
            User user = (User) userSession.getAttribute("loggedUser");
            Role role = (Role) userSession.getAttribute("userRole");
            Division division = (Division) userSession.getAttribute("userDivision");
        %>
        <p>User: <%= user.getFullName() %>, Role: <%= role.getRoleName() %>, Dep: <%= division.getDivisionName() %></p>
        
        <form action="request/create" method="post">
            <label for="startDate">Từ ngày:</label>
            <input type="date" id="startDate" name="startDate" required>
            
            <label for="endDate">Tới ngày:</label>
            <input type="date" id="endDate" name="endDate" required>
            
            <label for="reason">Lý do:</label>
            <textarea id="reason" name="reason" required></textarea>
            
            <button type="submit">Gửi</button>
        </form>
    </div>
</body>
</html>

