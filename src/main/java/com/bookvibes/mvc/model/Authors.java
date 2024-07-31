package com.bookvibes.mvc.model;

public class Authors {
    private Integer id;
    private String author;

    public Authors() {
    }

    public Authors(Integer id, String author) {
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
