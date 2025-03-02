/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Division;

public class DivisionDAO extends DBContext<Division> {

    @Override
    public ArrayList<Division> list() {
        ArrayList<Division> divisions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Division";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Division division = new Division(
                        rs.getInt("divisionId"),
                        rs.getString("divisionName"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()
                );
                divisions.add(division);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return divisions;
    }

    @Override
    public Division get(int id) {
        try {
            String sql = "SELECT * FROM Division WHERE divisionId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Division(
                        rs.getInt("divisionId"),
                        rs.getString("divisionName"),
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
    public void insert(Division model) {
        try {
            String sql = "INSERT INTO Division (divisionName, createdDate, updatedDate) VALUES (?, DEFAULT, DEFAULT)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getDivisionName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Division model) {
        try {
            String sql = "UPDATE Division SET divisionName = ?, updatedDate = GETDATE() WHERE divisionId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getDivisionName());
            stmt.setInt(2, model.getDivisionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Division model) {
        try {
            String sql = "DELETE FROM Division WHERE divisionId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getDivisionId());
            stmt.executeUpdate();

            String updateSql = "UPDATE Division SET updatedDate = GETDATE() WHERE divisionId = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.setInt(1, model.getDivisionId());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
