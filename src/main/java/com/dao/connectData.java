// src/model/DatabaseConnection.java
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class connectData {
    private static final String URL = "jdbc:mysql://localhost:3306/ql_sach";
    private static final String USER = "root";
    private static final String PASSWORD = "31082004";
    
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Nạp driver JDBC
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found: " + e.getMessage());
            throw new SQLException("MySQL Driver not found", e);
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
            throw e; // Ném lại SQLException nếu không thể kết nối
        }
        return connection; // Trả về kết nối
    }
    public static void main(String[] args) {
        try {
            System.out.println("Starting database connection test...");
            Connection connection = getConnection();

            if (connection != null) {
                System.out.println("Database connection is successful!");
                // Thực hiện các thao tác với database nếu cần
                connection.close(); // Đóng kết nối khi không còn sử dụng
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace(); // In chi tiết lỗi để kiểm tra
        }
    }
}
