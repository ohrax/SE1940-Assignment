/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.AbsentRequest;

/**
 *
 * @author admin
 */
public class AbsentRequestDAO extends DBContext<AbsentRequest> {

    @Override
    public ArrayList<AbsentRequest> list() {
        ArrayList<AbsentRequest> requests = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AbsentRequest";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AbsentRequest request = new AbsentRequest(
                        rs.getInt("requestId"),
                        rs.getString("title"),
                        rs.getTimestamp("fromDate").toLocalDateTime(),
                        rs.getTimestamp("toDate").toLocalDateTime(),
                        rs.getInt("createdBy"),
                        rs.getString("status"),
                        rs.getInt("processedBy"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()
                );
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
    
    public ArrayList<AbsentRequest> getListRequestsByUser(int userId) {
        ArrayList<AbsentRequest> requests = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AbsentRequest WHERE createdBy = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AbsentRequest request = new AbsentRequest(
                    rs.getInt("requestId"),
                    rs.getString("title"),
                    rs.getTimestamp("fromDate").toLocalDateTime(),
                    rs.getTimestamp("toDate").toLocalDateTime(),
                    rs.getInt("createdBy"),
                    rs.getString("status"),
                    rs.getInt("processedBy"),
                    rs.getTimestamp("createdDate").toLocalDateTime(),
                    rs.getTimestamp("updatedDate").toLocalDateTime()
                );
                requests.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public AbsentRequest get(int id) {
        try {
            String sql = "SELECT * FROM AbsentRequest WHERE requestId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AbsentRequest(
                        rs.getInt("requestId"),
                        rs.getString("title"),
                        rs.getTimestamp("fromDate").toLocalDateTime(),
                        rs.getTimestamp("toDate").toLocalDateTime(),
                        rs.getInt("createdBy"),
                        rs.getString("status"),
                        rs.getInt("processedBy"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(AbsentRequest model) {
        try {
            String sql = "INSERT INTO AbsentRequest (title, fromDate, toDate, createdBy, status, processedBy, createdDate, updatedDate) VALUES (?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getTitle());
            stmt.setTimestamp(2, Timestamp.valueOf(model.getFromDate()));
            stmt.setTimestamp(3, Timestamp.valueOf(model.getToDate()));
            stmt.setInt(4, model.getCreatedBy());
            stmt.setString(5, model.getStatus());
            stmt.setInt(6, model.getProcessedBy());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AbsentRequest model) {
        try {
            String sql = "UPDATE AbsentRequest SET title = ?, fromDate = ?, toDate = ?, status = ?, processedBy = ?, updatedDate = GETDATE() WHERE requestId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getTitle());
            stmt.setTimestamp(2, Timestamp.valueOf(model.getFromDate()));
            stmt.setTimestamp(3, Timestamp.valueOf(model.getToDate()));
            stmt.setString(4, model.getStatus());
            stmt.setInt(5, model.getProcessedBy());
            stmt.setInt(6, model.getRequestId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(AbsentRequest model) {
        try {
            String sql = "DELETE FROM AbsentRequest WHERE requestId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getRequestId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
