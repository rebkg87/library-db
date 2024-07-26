package com.bookvibes.structure.dao;

import com.bookvibes.DBConnection;
import com.bookvibes.classes.Genres;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class GenreDao {

    private static final String GET_ALL="SELECT a.id, a.genre FROM genres AS a";

    public GenreDao() {
    }

    public List<Genres> getAll(){
        List<Genres> genderList=new ArrayList<>();
        try{
            Connection conn= DBConnection.getConnection();
            Statement stm=conn.createStatement();
            ResultSet rs=stm.executeQuery(GET_ALL);

            while (rs.next()){
                Genres genres=new Genres();
                genres.setId(rs.getInt("id"));
                genres.setGenre(rs.getString("genre"));
                genderList.add(genres);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // DBConnection.closeConnection();
        }
        return genderList;
    }
}
