package com.bookvibes.mvc;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    
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
        } finally {
            System.out.println("Conexión cerrada");
        }
    }
    
}