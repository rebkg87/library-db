package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.config.DBConnection;
import com.bookvibes.mvc.model.Book;
import com.bookvibes.mvc.model.dao.BookDAOInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookController {
    private BookDAOInterface bookDAOInterface;


    public BookController(BookDAOInterface bookDAOInterface) {
        this.bookDAOInterface = bookDAOInterface;
        }


    public List<Book> getBookByAuthor(Integer authorId) {

        List<Book> bookList = bookDAOInterface.getBookByAuthor(authorId);
        return bookList;
    }

    public List<Book> getBookByGenre(Integer genreId) {

        List<Book> bookList = bookDAOInterface.getBookByGenre(genreId);
        return bookList;
    }

    public List<Book> getBookByTitle(String bookTitle) {

        List<Book> bookList = bookDAOInterface.getBookByTitle(bookTitle);
        return bookList;
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
