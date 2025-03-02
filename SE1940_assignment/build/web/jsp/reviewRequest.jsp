<%-- 
    Document   : reviewRequest
    Created on : Feb 23, 2025, 11:18:57 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Duyệt Đơn Xin Nghỉ - Leave Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <c:if test="${empty sessionScope.loggedInUser || !requestScope.isHigherUp}">
        <c:redirect url="/login"/>
    </c:if>
    <div class="container mt-4">
        <h2>Duyệt Đơn Xin Nghỉ</h2>
        <c:if test="${not empty request}">
            <div class="card p-3">
                <h5>${request.title}</h5>
                <p><strong>Từ Ngày (From):</strong> ${request.fromDate}</p>
                <p><strong>Tới Ngày (To):</strong> ${request.toDate}</p>
                <p><strong>Tạo Bởi (Created By):</strong> ${request.createdBy}</p>
                <p><strong>Trạng Thái (Status):</strong> ${request.status}</p>
                <p><strong>Xử Lý Bởi (Processed By):</strong> ${request.processedBy == 0 ? "Pending" : request.processedBy}</p>
                <p><strong>Lý Do (Reason):</strong> ${request.reason}</p>
                <form action="/request/review" method="post">
                    <input type="hidden" name="requestId" value="${request.requestId}">
                    <input type="hidden" name="processedBy" value="${sessionScope.loggedInUser.userId}">
                    <div class="mb-3">
                        <label class="form-label">Quyết Định (Decision)</label>
                        <select name="status" class="form-select">
                            <option value="Approved">Duyệt (Approve)</option>
                            <option value="Rejected">Từ Chối (Reject)</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="reason" class="form-label">Lý Do Quyết Định (Reason for Decision)</label>
                        <textarea id="reason" name="reason" class="form-control"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Xác Nhận (Submit)</button>
                </form>
            </div>
        </c:if>
    </div>
</body>
</html>
