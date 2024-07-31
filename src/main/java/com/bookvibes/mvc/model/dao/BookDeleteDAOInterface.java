package com.bookvibes.mvc.model.dao;


import java.sql.Connection;
import java.sql.SQLException;

public interface BookDeleteDAOInterface {
    public void showBook (Connection conn) throws SQLException;
    public void deleteBook(Connection conn, int bookId) throws SQLException;

}
