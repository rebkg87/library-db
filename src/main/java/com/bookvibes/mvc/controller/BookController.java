package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.config.DBConnection;
import com.bookvibes.mvc.model.Book;
import com.bookvibes.mvc.model.dao.BookDAOInterface;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

public class BookController {
    private BookDAOInterface bookDAOInterface;


    public BookController(BookDAOInterface bookDAOInterface) {
        this.bookDAOInterface = bookDAOInterface;
        }


    public List<Book> getBookByAuthor(Integer authorId) {

        List<Book> bookList = bookDAOInterface.getBookByAuthor(authorId);
        return bookList;
    }

    public List<Book> getBookByGenre(Integer genreId) {

        List<Book> bookList = bookDAOInterface.getBookByGenre(genreId);
        return bookList;
    }

    public List<Book> getBookByTitle(String bookTitle) {

        List<Book> bookList = bookDAOInterface.getBookByTitle(bookTitle);
        return bookList;
    }

    public void showBooks() {
        try (Connection conn = DBConnection.getConnection()) {
            bookDAOInterface.showBook(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteBook(int bookId) {
        try (Connection conn = DBConnection.getConnection()) {
            bookDAOInterface.deleteBook(conn, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    public  List<Book> getAllBooks(){
        List<Book> bookShowList = bookDAOInterface.showBooks();
        return bookShowList;
    }
    public void addBook(Book book, List<String> authors, List<String> genres) {
        if (bookDAOInterface.isBookExist(book.getTitle())) {
            System.out.println("El libro ya existe en la base de datos.");
        } else {
            bookDAOInterface.addBook(book, authors, genres);
            System.out.println("Libro agregado exitosamente.");
        }
    }
    public void editBook(int bookId, String newTitle, String newDescription, Long newIsbn, int[] newAuthorIds, int[] newGenreIds) {
        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false);

            bookDAOInterface.updateBookDetails(connection, bookId, newTitle, newDescription, newIsbn);
            if (newAuthorIds != null) {
                bookDAOInterface.updateBookRelations(connection, bookId, newAuthorIds, "authors_books", "id_author");
            }
            if (newGenreIds != null) {
                bookDAOInterface.updateBookRelations(connection, bookId, newGenreIds, "genres_books", "id_genre");
            }

            connection.commit();
            System.out.println("Los datos se han actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    

