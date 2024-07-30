package com.bookvibes.mvc.model;

public class Genres {
   private  Integer id;
   private String genre;

    public Genres() {
    }

    public Genres(Integer id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
