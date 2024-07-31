package com.bookvibes.mvc.model.dao;

import com.bookvibes.mvc.model.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookDAOInterface {
    public List<Book> getBookByAuthor(Integer authorId);
    public List<Book> getBookByGenre(Integer genreId);
    public List<Book> getBookByTitle(String bookTitle);
    public void showBook (Connection conn) throws SQLException;
    public void deleteBook(Connection conn, int bookId) throws SQLException;
}
