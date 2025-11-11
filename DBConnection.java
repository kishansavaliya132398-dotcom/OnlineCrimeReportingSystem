package com.ocr.util;
import java.sql.*;
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ocr_system?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "ocr_user"; // <-- change
    private static final String PASS = "your_password"; // <-- change
    static {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch(Exception e){ e.printStackTrace(); }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
