package com.bookvibes;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/bdLibro";
    private static final String USER = "postgres";
    private static final String PASSWORD = "femcoders";
    
    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            java.sql.Connection connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Conexión exitosa a Librería!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}