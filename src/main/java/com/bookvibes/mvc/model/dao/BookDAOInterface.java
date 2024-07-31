package com.bookvibes.mvc.model.dao;

import com.bookvibes.mvc.model.Books;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookDAOInterface {
    public List<Books> getBookByAuthor(Integer authorId);
    public List<Books> getBookByGenre(Integer genreId);
    public List<Books> getBookByTitle(String bookTitle);
    public void showBook (Connection conn) throws SQLException;
    public void deleteBook(Connection conn, int bookId) throws SQLException;
}
