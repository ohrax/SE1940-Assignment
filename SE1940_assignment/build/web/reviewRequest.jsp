<%-- 
    Document   : reviewRequest
    Created on : Feb 23, 2025, 11:18:57 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request List</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>List of Requests</h2>
        <table class="table table-striped">
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
                <% for (AbsentRequest request : requestList) { %>
                <tr>
                    <td>
                        <a href="/request/review?id=<%= request.getId() %>">
                            <%= request.getRequestType() %>
                        </a>
                    </td>
                    <td><%= request.getFromDate() %></td>
                    <td><%= request.getToDate() %></td>
                    <td><%= request.getCreatedBy() %></td>
                    <td><%= request.getStatus() %></td>
                    <td><%= request.getProcessedBy() != null ? request.getProcessedBy() : "Pending" %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
