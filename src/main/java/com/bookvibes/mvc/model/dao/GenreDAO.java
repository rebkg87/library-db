package com.bookvibes.mvc.model.dao;
import com.bookvibes.mvc.config.DBConnection;
import com.bookvibes.mvc.model.Genre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO implements GenreDAOInterface {

    private static final String GET_ALL="SELECT g.id, g.genre FROM genres AS g ORDER BY g.id";

    public GenreDAO() {
    }

    @Override
    public List<Genre> getAll(){
        List<Genre> genderList=new ArrayList<>();
        try{
            Connection conn= DBConnection.getConnection();
            Statement stm=conn.createStatement();
            ResultSet rs=stm.executeQuery(GET_ALL);

            while (rs.next()){
                Genre genre =new Genre();
                genre.setId(rs.getInt("id"));
                genre.setGenre(rs.getString("genre"));
                genderList.add(genre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return genderList;
    }
}
