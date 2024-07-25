package com.bookvibes.structure.dao;

import com.bookvibes.DBConnection;
import com.bookvibes.classes.Authors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

    private static final String GET_ALL = "SELECT a.id, a.author FROM authors AS a";

    public AuthorDao() {
    }

    public List<Authors> getAll() {
        List<Authors> authorsList = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(GET_ALL);

            while (rs.next()) {
                Authors authors = new Authors();
                authors.setId(rs.getInt("id"));
                authors.setAuthor(rs.getString("author"));
                authorsList.add(authors);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // close connection
        }
        return authorsList;
    }
//SOLO PARA PROBAR LA CONSULTA
//    public static void main(String[] args) {
//        List<Authors> authorsList = new AuthorDao().getAll();
//        for (Authors a : authorsList) {
//            System.out.println("id => " + a.getId() + " | name => " + a.getAuthor());
//        }
//    }
}
