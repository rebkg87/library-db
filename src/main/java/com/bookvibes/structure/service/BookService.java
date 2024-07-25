package com.bookvibes.structure.service;

import com.bookvibes.classes.Books;
import com.bookvibes.structure.dao.BookDao;

import java.util.List;

public class BookService {
    public BookService() {
    }

    public List<Books> getBookByAuthor(Integer authorId) {
        BookDao bookDao = new BookDao();
        List<Books> booksList = bookDao.getBookByAuthor(authorId);
        return booksList;
    }

    public List<Books> getBookByGenre(Integer genreId) {
        BookDao bookDao = new BookDao();
        List<Books> booksList = bookDao.getBookByGenre(genreId);
        return booksList;
    }
}
