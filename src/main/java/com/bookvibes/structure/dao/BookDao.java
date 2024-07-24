package com.bookvibes.structure.dao;

import com.bookvibes.DBConnection;
import com.bookvibes.classes.Books;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private static String TABLENAME = "books";

    private static String GET_ALL = "SELECT b.id, b.title, b.description, b.isbn FROM " + TABLENAME + " AS b";
    private static String GET_BY_AUTHOR = GET_ALL + " JOIN authors_books AS ab ON ab.id_book = b.id WHERE ab.id_author = ?";



    public List<Books> getBookByAuthor(Integer authorId) {

        List<Books> bookList = new ArrayList<>();

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(GET_BY_AUTHOR);
            ps.setInt(1, authorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Books bookBean = new Books();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getString("isbn"));

                bookList.add(bookBean);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            DBConnection.closeConnection();
        }

        return bookList;
    }

    public static void main(String[] args) {
        BookDao bookDao = new BookDao();
        List<Books> bookBeanList = bookDao.getBookByAuthor(3);
        for (Books bb : bookBeanList) {
            System.out.println("| " + bb.getId() + " | " + bb.getTitle() + " | " + bb.getIsbn() + " |");
        }
    }


}
