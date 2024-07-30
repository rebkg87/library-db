package com.bookvibes.mvc.model.dao;
import com.bookvibes.mvc.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookDeleteDao {

    private static int getBookId(Connection conn, String title) throws SQLException {
        String selectBookSQL = "SELECT id FROM books WHERE title = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectBookSQL)) {
            pstmt.setString(1, title);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Título no encontrado: " + title);
                }
            }
        }
    }

    public static void deleteBook (Connection conn, int bookId) throws SQLException{
        String deleteAuthorBookSQL = "DELETE FROM authors_books WHERE id_book = ?";
        String deleteGenreBookSQL= "DELETE FROM genres_books WHERE id_book = ?";
        String deleteBookSQL = "DELETE FROM books WHERE id = ?";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(deleteAuthorBookSQL)) {
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(deleteGenreBookSQL)) {
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(deleteBookSQL)) {
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Libro eliminado exitosamente.");

        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        } finally {
            conn.setAutoCommit(true);
        }

    }

    public static void deleteBookByTitle (Connection conn, String title) throws SQLException{
        int bookId = getBookId(conn, title);
        deleteBook(conn,bookId);
    }

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);

        System.out.println("Si deseas eliminar un libro por ID teclea 1, si es por título teclea 2.");
        int option = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            switch (option) {
                case 1:
                    System.out.println("Introduce el ID del libro a eliminar: ");
                    int bookId = scanner.nextInt();
                    deleteBook(conn, bookId);
                    break;
                case 2:
                    System.out.println("Introduce el título del libro a eliminar: ");
                    String title = scanner.nextLine();
                    deleteBookByTitle(conn, title);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
