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

    public enum PositionEnum {
        DULEAD("DULead"), TLEAD("TLead"), MEMBER("Member");
        private final String value;
        PositionEnum(String value) { this.value = value; }
        public String getValue() { return value; }
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
        if ("DULead".equals(position) || "TLead".equals(position) || "Member".equals(position)) {
            this.position = position;
        } else {
            throw new IllegalArgumentException("Position must be 'DULead', 'TLead', or 'Member'");
        }
    }

}
