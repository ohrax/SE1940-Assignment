/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class UserDivision {

    private int id;
    private int userId;
    private int divisionId;
    private String position; //DULead, TLead, Member

    private enum positionEnum {
        DULEAD, TLEAD, MEMBER
    }

    public UserDivision() {
    }

    public UserDivision(int id, int userId, int divisionId, String position) {
        this.id = id;
        this.userId = userId;
        this.divisionId = divisionId;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
