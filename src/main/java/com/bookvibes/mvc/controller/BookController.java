package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.config.DBConnection;
import com.bookvibes.mvc.model.Books;
import com.bookvibes.mvc.model.dao.BookDAOInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookController {
    private BookDAOInterface bookDAOInterface;


    public BookController(BookDAOInterface bookDAOInterface) {
        this.bookDAOInterface = bookDAOInterface;
        }


    public List<Books> getBookByAuthor(Integer authorId) {

        List<Books> booksList = bookDAOInterface.getBookByAuthor(authorId);
        return booksList;
    }

    public List<Books> getBookByGenre(Integer genreId) {

        List<Books> booksList = bookDAOInterface.getBookByGenre(genreId);
        return booksList;
    }

    public List<Books> getBookByTitle(String bookTitle) {

        List<Books> booksList = bookDAOInterface.getBookByTitle(bookTitle);
        return booksList;
    }

    public void showBooks() {
        try (Connection conn = DBConnection.getConnection()) {
            bookDAOInterface.showBook(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteBook(int bookId) {
        try (Connection conn = DBConnection.getConnection()) {
            bookDAOInterface.deleteBook(conn, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
