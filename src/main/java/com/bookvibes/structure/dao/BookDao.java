package com.bookvibes.structure.dao;

import com.bookvibes.DBConnection;
import com.bookvibes.classes.Books;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private static String TABLENAME = "books";

    private static String GET_ALL = "SELECT b.id, b.title, b.description, b.isbn FROM " + TABLENAME + " AS b ";
    private static String GET_BY_AUTHOR = GET_ALL + "JOIN authors_books AS ab ON ab.id_book = b.id WHERE ab.id_author = ?";
    private static String GET_BY_GENRE = GET_ALL + "JOIN genres_books AS gb ON gb.id_book = b.id WHERE gb.id_genre = ?";
    private static String GET_BY_TITLE= GET_ALL + "WHERE LOWER(b.title) LIKE '%' || LOWER(?) || '%' ";


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
                bookBean.setIsbn(rs.getLong("isbn"));

                bookList.add(bookBean);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            DBConnection.closeConnection();
        }

        return bookList;
    }

    //buscar por género
    public List<Books> getBookByGenre(Integer genreId) {
        List<Books> booksList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_BY_GENRE);
            ps.setInt(1, genreId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Books bookBean = new Books();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getLong("isbn"));
                booksList.add(bookBean);

            }
        } catch (SQLException e) {
            throw new RuntimeException((e));
        } finally {
            //            DBConnection.closeConnection();
        }
        return booksList;
    }

    //buscar por title
    public List<Books> getBookByTitle(String bookTitle) {
        List<Books> booksList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_BY_TITLE);
            ps.setString(1, bookTitle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Books bookBean = new Books();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getLong("isbn"));
                booksList.add(bookBean);

            }
        } catch (SQLException e) {
            throw new RuntimeException((e));
        } finally {
            //            DBConnection.closeConnection();
        }
        return booksList;
    }

//    public static void main(String[] args) {
//        BookDao bookDao = new BookDao();
//        List<Books> bookShowList = bookDao.getBookByTitle("SoMbra");
//        for (Books bb : bookShowList) {
//            System.out.println("| " + bb.getId() + " | " + bb.getTitle() + " | " + bb.getIsbn() + " | " );
//        }
//    }


}
