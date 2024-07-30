package com.bookvibes.mvc.view;
import com.bookvibes.mvc.DBConnection;
import com.bookvibes.mvc.model.Books;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Books book = new Books();

        System.out.println("Título: ");
        book.setTitle(scanner.nextLine());

        List<String> authors = new ArrayList<>();
        System.out.println("Autor: ");
        authors.add(scanner.nextLine());

        while (true) {
            System.out.println("¿Hay más autores? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Autor: ");
                authors.add(scanner.nextLine());
            } else {
                break;
            }
        }

        System.out.println("Descripción: ");
        book.setDescription(scanner.nextLine());

        List<String> genres = new ArrayList<>();
        System.out.println("Género: ");
        genres.add(scanner.nextLine());

        while (true) {
            System.out.println("¿Hay más géneros? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Género: ");
                genres.add(scanner.nextLine());
            } else {
                break;
            }
        }

        System.out.println("ISBN: ");
        book.setIsbn(scanner.nextLong());

        System.out.println();
        System.out.println(book.toString());

        saveBookToDatabase(book, authors, genres);

        scanner.close();
    }

    private static void saveBookToDatabase(Books book, List<String> authors, List<String> genres) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // Insertar el libro
            String insertBookSQL = "INSERT INTO books (title, description, isbn) VALUES (?, ?, ?) RETURNING id";
            pstmt = conn.prepareStatement(insertBookSQL);
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getDescription());
            pstmt.setLong(3, book.getIsbn());
            rs = pstmt.executeQuery();
            rs.next();
            int bookId = rs.getInt(1);

            // Insertar autores y relaciones
            for (String author : authors) {
                String insertAuthorSQL = "INSERT INTO authors (author) VALUES (?) ON CONFLICT (author) DO NOTHING";
                pstmt = conn.prepareStatement(insertAuthorSQL);
                pstmt.setString(1, author);
                pstmt.executeUpdate();
                int authorId = getAuthorId(conn, author);

                String insertAuthorsBooksSQL = "INSERT INTO authors_books (id_author, id_book) VALUES (?, ?)";
                pstmt = conn.prepareStatement(insertAuthorsBooksSQL);
                pstmt.setInt(1, authorId);
                pstmt.setInt(2, bookId);
                pstmt.executeUpdate();
            }

            // Insertar géneros y relaciones
            for (String genre : genres) {
                String insertGenreSQL = "INSERT INTO genres (genre) VALUES (?) ON CONFLICT (genre) DO NOTHING";
                pstmt = conn.prepareStatement(insertGenreSQL);
                pstmt.setString(1, genre);
                pstmt.executeUpdate();
                int genreId = getGenreId(conn, genre);

                String insertGenresBooksSQL = "INSERT INTO genres_books (id_genre, id_book) VALUES (?, ?)";
                pstmt = conn.prepareStatement(insertGenresBooksSQL);
                pstmt.setInt(1, genreId);
                pstmt.setInt(2, bookId);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Libro guardado exitosamente en la base de datos.");

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
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