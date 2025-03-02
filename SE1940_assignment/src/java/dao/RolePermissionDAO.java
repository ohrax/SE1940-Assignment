/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.RolePermission;

public class RolePermissionDAO extends DBContext<RolePermission> {

    @Override
    public ArrayList<RolePermission> list() {
        ArrayList<RolePermission> rolePermissions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM RolePermission";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RolePermission rolePermission = new RolePermission(
                    rs.getInt("id"),
                    rs.getInt("roleId"),
                    rs.getInt("permissionId")
                );
                rolePermissions.add(rolePermission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rolePermissions;
    }

    @Override
    public RolePermission get(int id) {
        try {
            String sql = "SELECT * FROM RolePermission WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new RolePermission(
                    rs.getInt("id"),
                    rs.getInt("roleId"),
                    rs.getInt("permissionId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(RolePermission model) {
        try {
            String sql = "INSERT INTO RolePermission (roleId, permissionId) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getRoleId());
            stmt.setInt(2, model.getPermissionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(RolePermission model) {
        try {
            String sql = "UPDATE RolePermission SET roleId = ?, permissionId = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getRoleId());
            stmt.setInt(2, model.getPermissionId());
            stmt.setInt(3, model.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(RolePermission model) {
        try {
            String sql = "DELETE FROM RolePermission WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

