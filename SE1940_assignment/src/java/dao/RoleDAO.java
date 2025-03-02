/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Role;

public class RoleDAO extends DBContext<Role> {

    @Override
    public ArrayList<Role> list() {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Role";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role role = new Role(
                        rs.getInt("roleId"),
                        rs.getString("roleName"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getTimestamp("updatedDate").toLocalDateTime()
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role get(int id) {
        try {
            String sql = "SELECT * FROM Role WHERE roleId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Role(
                        rs.getInt("roleId"),
                        rs.getString("roleName"),
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
    public void insert(Role model) {
        try {
            String sql = "INSERT INTO Role (roleName, createdDate, updatedDate) VALUES (?, DEFAULT, DEFAULT)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getRoleName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role model) {
        try {
            String sql = "UPDATE Role SET roleName = ?, updatedDate = GETDATE() WHERE roleId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getRoleName());
            stmt.setInt(2, model.getRoleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Role model) {
        try {
            String sql = "DELETE FROM Role WHERE roleId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getRoleId());
            stmt.executeUpdate();

            String updateSql = "UPDATE Role SET updatedDate = GETDATE() WHERE roleId = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSql);
            updateStmt.setInt(1, model.getRoleId());
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
