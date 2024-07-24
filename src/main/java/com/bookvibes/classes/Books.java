package com.bookvibes.classes;

public class Books {
    private String author;
    private String title;
    private String isbn;
    private String genre;
    private String description;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Books(String author, String title, String isbn, String genre, String description) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Books{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Books() {
    }
}