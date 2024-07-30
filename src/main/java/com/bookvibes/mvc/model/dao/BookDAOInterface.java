package com.bookvibes.mvc.model.dao;

import com.bookvibes.mvc.model.Books;

import java.util.List;

public interface BookDAOInterface {
    public List<Books> getBookByAuthor(Integer authorId);
    public List<Books> getBookByGenre(Integer genreId);
    public List<Books> getBookByTitle(String bookTitle);
}
