package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Books;
import com.bookvibes.mvc.model.dao.BookAllDaoInterface;
import com.bookvibes.mvc.model.dao.BookDAO;
import com.bookvibes.mvc.model.dao.BookDAOInterface;

import java.util.List;

public class BookController {

    private BookDAOInterface bookDAOInterface;
    private BookAllDaoInterface bookAllDaoInterface;

    public BookController(BookDAOInterface bookDAOInterface, BookAllDaoInterface bookAllDaoInterface) {

        this.bookDAOInterface = bookDAOInterface;
        this.bookAllDaoInterface = bookAllDaoInterface;
    }

    public List<Books> getBookByAuthor(Integer authorId) {

        List<Books> booksList = bookDAOInterface.getBookByAuthor(authorId);
        return booksList;
    }

    public List<Books> getBookByGenre(Integer genreId) {

        List<Books> booksList = bookDAOInterface.getBookByGenre(genreId);
        return booksList;
    }

    public List<Books> getBookByTitle(String bookTitle) {

        List<Books> booksList = bookDAOInterface.getBookByTitle(bookTitle);
        return booksList;
    }

    public  List<Books> getAllBooks(){
        List<Books> bookShowList = bookAllDaoInterface.showBooks();
        return bookShowList;
    }

}
