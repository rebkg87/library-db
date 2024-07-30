package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.DBConnection;
import com.bookvibes.mvc.model.Books;
import com.bookvibes.mvc.model.dao.BookDAO;
import com.bookvibes.mvc.model.dao.BookDeleteDAO;
import com.bookvibes.mvc.model.dao.BookDeleteDAOInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookController {
    private BookDeleteDAOInterface bookDeleteDao;

    public BookController() {
        this.bookDeleteDao = new BookDeleteDAO();
    }

    public List<Books> getBookByAuthor(Integer authorId) {
        BookDAO bookDao = new BookDAO();
        List<Books> booksList = bookDao.getBookByAuthor(authorId);
        return booksList;
    }

    public List<Books> getBookByGenre(Integer genreId) {
        BookDAO bookDao = new BookDAO();
        List<Books> booksList = bookDao.getBookByGenre(genreId);
        return booksList;
    }
    public List<Books> getBookByTitle(String bookTitle) {
        BookDAO bookDao = new BookDAO();
        List<Books> booksList = bookDao.getBookByTitle(bookTitle);
        return booksList;
    }

    public void showBooks() {
        try (Connection conn = DBConnection.getConnection()) {
            bookDeleteDao.showBook(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteBook(int bookId) {
        try (Connection conn = DBConnection.getConnection()) {
            bookDeleteDao.deleteBook(conn, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
