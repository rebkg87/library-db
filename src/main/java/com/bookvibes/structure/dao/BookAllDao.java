package com.bookvibes.structure.dao;

import com.bookvibes.DBConnection;
import com.bookvibes.classes.Books;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAllDao {

    public List<Books> showBooks(){

        List<Books> bookList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("select b.id, b.title, b.description, b.isbn, a.author, g.genre from books as b\n" +
                    "join authors_books as ab on b.id = ab.id_book\n" +
                    "join authors as a on a.id = ab.id_author\n" +
                    "join genres_books as gb on b.id = gb.id_book\n" +
                    "join genres as g on g.id = gb.id_genre;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Books bookBean = new Books();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getString("isbn"));
                bookBean.setAuthor(rs.getString("author"));
                bookBean.setGenre(rs.getString("genre"));

                bookList.add(bookBean);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }
    public static void main(String[] args) {
        BookAllDao bookDao = new BookAllDao();
        List<Books> bookShowList = bookDao.showBooks();
        for (Books bb : bookShowList) {
            System.out.println("| " + bb.getId() + " | " + bb.getTitle() + " | " + bb.getIsbn() + " | " + bb.getAuthor() + " | " + bb.getGenre());
        }
    }

}
