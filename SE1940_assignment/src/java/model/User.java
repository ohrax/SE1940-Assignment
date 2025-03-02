/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public class User {

    private int userId;
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String status; //active, inactive
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int directManagerId;

    private enum statusEnum {
        active, inactive
    }

    public User() {
    }

    public User(int userId, String username, String fullname, String email, String password, String status, LocalDateTime createdDate, LocalDateTime updatedDate, int directManagerId) {
        this.userId = userId;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.directManagerId = directManagerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getDirectManagerId() {
        return directManagerId;
    }

    public void setDirectManagerId(int directManagerId) {
        this.directManagerId = directManagerId;
    }

}
