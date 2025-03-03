/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:sqlserver://rax\\SQLEXPRESS:1433;databaseName=LeaveManagementDB;integratedSecurity=true"; // Update as needed
    private static final String USER = "sa"; // Optional if using Windows Authentication
    private static final String PASSWORD = "sa"; // Optional if using Windows Authentication

    public static Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("DB Connection Successful");
        return conn; // Remove USER and PASSWORD if using integrated security
    }
}
