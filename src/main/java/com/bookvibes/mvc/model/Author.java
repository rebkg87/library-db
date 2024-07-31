package com.bookvibes.mvc.model;

public class Author {
    private Integer id;
    private String author;

    public Author() {
    }

    public Author(Integer id, String author) {
        this.id = id;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
