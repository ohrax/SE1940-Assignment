/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.UserDivision;

public class UserDivisionDAO extends DBContext<UserDivision> {

    @Override
    public ArrayList<UserDivision> list() {
        ArrayList<UserDivision> userDivisions = new ArrayList<>();
        try {
            String sql = "SELECT * FROM UserDivision";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserDivision userDivision = new UserDivision(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getInt("divisionId"),
                    rs.getString("position")
                );
                userDivisions.add(userDivision);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDivisions;
    }

    @Override
    public UserDivision get(int id) {
        try {
            String sql = "SELECT * FROM UserDivision WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserDivision(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getInt("divisionId"),
                    rs.getString("position")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public UserDivision getByUserId(int userId) {
        try {
            String sql = "SELECT * FROM UserDivision WHERE userId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserDivision(
                    rs.getInt("id"), rs.getInt("userId"),
                    rs.getInt("divisionId"), rs.getString("position")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(UserDivision model) {
        try {
            String sql = "INSERT INTO UserDivision (userId, divisionId, position) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getUserId());
            stmt.setInt(2, model.getDivisionId());
            stmt.setString(3, model.getPosition());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserDivision model) {
        try {
            String sql = "UPDATE UserDivision SET userId = ?, divisionId = ?, position = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getUserId());
            stmt.setInt(2, model.getDivisionId());
            stmt.setString(3, model.getPosition());
            stmt.setInt(4, model.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(UserDivision model) {
        try {
            String sql = "DELETE FROM UserDivision WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
