package com.bookvibes.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDeleteDAO implements BookDeleteDAOInterface {

    @Override
    public void showBook(Connection conn) throws SQLException {
        String selectCatalogSQL = "SELECT b.id, b.title, a.author AS author FROM books AS b " +
                "JOIN authors_books AS ab ON b.id = ab.id_book " +
                "JOIN authors AS a ON ab.id_author = a.id";

        try (PreparedStatement pstmt = conn.prepareStatement(selectCatalogSQL);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("ID | Título | Autor");
            System.out.println("---------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                System.out.println(id + " | " + title + " | " + author);
            }
        }
    }

    @Override
    public void deleteBook (Connection conn, int bookId) throws SQLException{
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


}
