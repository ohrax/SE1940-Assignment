/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Permission;

public class PermissionDAO extends DBContext<Permission> {

    @Override
    public ArrayList<Permission> list() {
        ArrayList<Permission> permissions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Permission";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Permission permission = new Permission(
                        rs.getInt("permissionId"),
                        rs.getString("path"),
                        rs.getString("permissionList")
                );
                permissions.add(permission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissions;
    }

    @Override
    public Permission get(int id) {
        try {
            String sql = "SELECT * FROM Permission WHERE permissionId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Permission(
                        rs.getInt("permissionId"),
                        rs.getString("path"),
                        rs.getString("permissionList")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Permission model) {
        try {
            String sql = "INSERT INTO Permission (path, permissionList) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getPath());
            stmt.setString(2, model.getPermissionList());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Permission model) {
        try {
            String sql = "UPDATE Permission SET path = ?, permissionList = ? WHERE permissionId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getPath());
            stmt.setString(2, model.getPermissionList());
            stmt.setInt(3, model.getPermissionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Permission model) {
        try {
            String sql = "DELETE FROM Permission WHERE permissionId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getPermissionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
