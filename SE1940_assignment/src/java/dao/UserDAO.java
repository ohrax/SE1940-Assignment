/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.User;

public class UserDAO extends DBContext<User> {
    @Override
    public ArrayList<User> list() {
        ArrayList<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [User]";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                    rs.getInt("userId"), rs.getString("username"),
                    rs.getString("fullname"), rs.getString("email"),
                    rs.getString("password"), rs.getString("status"),
                    rs.getTimestamp("createdDate").toLocalDateTime(),
                    rs.getTimestamp("updatedDate").toLocalDateTime(),
                    rs.getInt("directManagerId")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User get(int id) {
        try {
            String sql = "SELECT * FROM [User] WHERE userId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("userId"), rs.getString("username"),
                    rs.getString("fullname"), rs.getString("email"),
                    rs.getString("password"), rs.getString("status"),
                    rs.getTimestamp("createdDate").toLocalDateTime(),
                    rs.getTimestamp("updatedDate").toLocalDateTime(),
                    rs.getInt("directManagerId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "SELECT * FROM [User] WHERE username = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("userId"), rs.getString("username"),
                    rs.getString("fullname"), rs.getString("email"),
                    rs.getString("password"), rs.getString("status"),
                    rs.getTimestamp("createdDate").toLocalDateTime(),
                    rs.getTimestamp("updatedDate").toLocalDateTime(),
                    rs.getInt("directManagerId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(User model) {
        try {
            String sql = "INSERT INTO [User] (username, fullname, email, password, status, createdDate, updatedDate, directManagerId) VALUES (?, ?, ?, ?, ?, DEFAULT, DEFAULT, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getUsername());
            stmt.setString(2, model.getFullname());
            stmt.setString(3, model.getEmail());
            stmt.setString(4, model.getPassword());
            stmt.setString(5, model.getStatus());
            stmt.setInt(6, model.getDirectManagerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User model) {
        try {
            String sql = "UPDATE [User] SET username = ?, fullname = ?, email = ?, password = ?, status = ?, updatedDate = GETDATE(), directManagerId = ? WHERE userId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.getUsername());
            stmt.setString(2, model.getFullname());
            stmt.setString(3, model.getEmail());
            stmt.setString(4, model.getPassword());
            stmt.setString(5, model.getStatus());
            stmt.setInt(6, model.getDirectManagerId());
            stmt.setInt(7, model.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User model) {
        try {
            String sql = "DELETE FROM [User] WHERE userId = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, model.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
