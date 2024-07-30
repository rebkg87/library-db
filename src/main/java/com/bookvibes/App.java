package com.bookvibes;
import com.bookvibes.mvc.model.Books;
import com.bookvibes.mvc.config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Books book = new Books();
        System.out.println("Título: ");
        book.setTitle(scanner.nextLine());

        System.out.println("Autor: ");
        book.setAuthor(scanner.nextLine());

        System.out.println("Descripción: ");
        book.setDescription(scanner.nextLine());

        System.out.println("Género: ");
        book.setGenre(scanner.nextLine());

        System.out.println("ISBN: ");
        book.setIsbn(scanner.nextLong()); // Cambiado a nextLong() para capturar long

        System.out.println();
        System.out.println(book.toString());

        saveBookToDatabase(book);

        scanner.close();
    }

    private static void saveBookToDatabase(Books book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);


            String insertAuthorSQL = "INSERT INTO authors (author) VALUES (?) ON CONFLICT (author) DO NOTHING";
            pstmt = conn.prepareStatement(insertAuthorSQL);
            pstmt.setString(1, book.getAuthor());
            pstmt.executeUpdate();
            int authorId = getAuthorId(conn, book.getAuthor());


            String insertGenreSQL = "INSERT INTO genres (genre) VALUES (?) ON CONFLICT (genre) DO NOTHING";
            pstmt = conn.prepareStatement(insertGenreSQL);
            pstmt.setString(1, book.getGenre());
            pstmt.executeUpdate();
            int genreId = getGenreId(conn, book.getGenre());


            String insertBookSQL = "INSERT INTO books (title, description, isbn) VALUES (?, ?, ?) RETURNING id";
            pstmt = conn.prepareStatement(insertBookSQL);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getDescription());
            pstmt.setLong(3, book.getIsbn()); // Cambiado a setLong() para pasar long
            rs = pstmt.executeQuery();
            rs.next();
            int bookId = rs.getInt(1);


            String insertAuthorsBooksSQL = "INSERT INTO authors_books (id_author, id_book) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertAuthorsBooksSQL);
            pstmt.setInt(1, authorId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();


            String insertGenresBooksSQL = "INSERT INTO genres_books (id_genre, id_book) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertGenresBooksSQL);
            pstmt.setInt(1, genreId);
            pstmt.setInt(2, bookId);
            pstmt.executeUpdate();

            conn.commit();
            System.out.println("Libro guardado exitosamente en la base de datos.");

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback de la transacción en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static int getAuthorId(Connection conn, String author) throws SQLException {
        String selectAuthorSQL = "SELECT id FROM authors WHERE author = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectAuthorSQL)) {
            pstmt.setString(1, author);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Author not found: " + author);
                }
            }
        }
    }

    private static int getGenreId(Connection conn, String genre) throws SQLException {
        String selectGenreSQL = "SELECT id FROM genres WHERE genre = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectGenreSQL)) {
            pstmt.setString(1, genre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Genre not found: " + genre);
                }
            }
        }
    }
}