<%-- 
    Document   : createRequest
    Created on : Feb 23, 2025, 11:18:37 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đơn Xin Nghỉ Phép - Leave Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <c:if test="${empty sessionScope.loggedInUser}">
        <c:redirect url="/login"/>
    </c:if>
    <div class="container mt-4">
        <h2>Đơn Xin Nghỉ Phép</h2>
        <p>User: ${sessionScope.loggedInUser.fullname} (Phòng Ban: <c:out value="${userDivisionDAO.getByUserId(sessionScope.loggedInUser.userId).divisionId == 1 ? 'IT' : 'HR'}"/>)</p>
        <form action="/request/create" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">Tiêu Đề (Title)</label>
                <input type="text" id="title" name="title" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="fromDate" class="form-label">Từ Ngày (From Date)</label>
                <input type="datetime-local" id="fromDate" name="fromDate" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="toDate" class="form-label">Tới Ngày (To Date)</label>
                <input type="datetime-local" id="toDate" name="toDate" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="reason" class="form-label">Lý Do (Reason)</label>
                <textarea id="reason" name="reason" class="form-control" required></textarea>
            </div>
            <input type="hidden" name="createdBy" value="${sessionScope.loggedInUser.userId}">
            <button type="submit" class="btn btn-primary">Gửi (Submit)</button>
        </form>
    </div>
</body>
</html>

