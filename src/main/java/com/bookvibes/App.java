package com.bookvibes;
import com.bookvibes.classes.Books ;
import com.bookvibes.structure.dao.BookAllDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Books book = new Books();
//        System.out.println("Título: ");
//        book.setTitle(scanner.nextLine());
//
//        System.out.println("Autores: ");
//        book.setAuthor(scanner.nextLine());
//
//        System.out.println("Descripción: ");
//        book.setDescription(scanner.nextLine());
//
//        System.out.println("Género: ");
//        book.setGenre(scanner.nextLine());
//
//        System.out.println("ISBN: ");
//        book.setIsbn(scanner.nextLine());
//
//        System.out.println();
//        System.out.println(book.toString());
//
//        saveBookToDatabase(book);

        BookAllDao booksito = new BookAllDao();
        System.out.println(booksito.showBooks());
    }

    private static void saveBookToDatabase(Books book) {
        String sql = "INSERT INTO books (title, author, description, genre, isbn) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getDescription());
            pstmt.setString(4, book.getGenre());
            pstmt.setString(5, book.getIsbn());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Libro guardado exitosamente en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}