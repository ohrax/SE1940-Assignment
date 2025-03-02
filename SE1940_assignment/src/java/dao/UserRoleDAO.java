/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.UserRole;

public class UserRoleDAO extends DBContext<UserRole> {

    @Override
    public ArrayList<UserRole> list() {
        ArrayList<UserRole> userRoles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM UserRole";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserRole userRole = new UserRole(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("roleId")
                );
                userRoles.add(userRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoles;
    }

    public boolean isUserHigherUp(int userId) {
        try {
            String sql = "SELECT COUNT(*) FROM UserRole ur JOIN Role r ON ur.roleId = r.id WHERE ur.userId = ? AND (r.roleName = 'leader' OR r.roleName = 'admin')";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserRole get(int id) {
        try {
            String sql = "SELECT * FROM UserRole WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserRole(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getInt("roleId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(UserRole model) {
        try {
            String sql = "INSERT INTO UserRole (userId, roleId) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getUserId());
            stmt.setInt(2, model.getRoleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserRole model) {
        try {
            String sql = "UPDATE UserRole SET userId = ?, roleId = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getUserId());
            stmt.setInt(2, model.getRoleId());
            stmt.setInt(3, model.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserRole model) {
        try {
            String sql = "DELETE FROM UserRole WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
