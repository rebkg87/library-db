package com.bookvibes.mvc.model.dao;

import com.bookvibes.mvc.config.DBConnection;
import com.bookvibes.mvc.model.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO implements AuthorDAOInterface {

    private static final String GET_ALL = "SELECT a.id, a.author FROM authors AS a";

    public AuthorDAO() {
    }

    @Override
    public List<Author> getAll() {
        List<Author> authorList = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL);

            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setAuthor(rs.getString("author"));
                authorList.add(author);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // close connection
        }
        return authorList;
    }

}
