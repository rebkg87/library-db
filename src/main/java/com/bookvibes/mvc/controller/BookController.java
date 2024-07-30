package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Books;
import com.bookvibes.mvc.model.dao.BookDAO;

import java.util.List;

public class BookController {
    public BookController() {
    }

    public List<Books> getBookByAuthor(Integer authorId) {
        BookDAO bookDao = new BookDAO();
        List<Books> booksList = bookDao.getBookByAuthor(authorId);
        return booksList;
    }

    public List<Books> getBookByGenre(Integer genreId) {
        BookDAO bookDao = new BookDAO();
        List<Books> booksList = bookDao.getBookByGenre(genreId);
        return booksList;
    }
    public List<Books> getBookByTitle(String bookTitle) {
        BookDAO bookDao = new BookDAO();
        List<Books> booksList = bookDao.getBookByTitle(bookTitle);
        return booksList;
    }

}
