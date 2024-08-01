package com.bookvibes.mvc.controller;


import com.bookvibes.mvc.model.Book;
import com.bookvibes.mvc.model.dao.BookDAOInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookDAOInterface bookDAOInterface;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetBookByAuthor() {

        Integer authorId = 1;
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book("JK Rowling", "Harry Potter y la Piedra Filosofal", 1234567, "Fantástico", "Description"));
        expectedBooks.add(new Book("Sergi Torres", "La biología del presente", 1234568, "Autoayuda", "Description"));

        when(bookDAOInterface.getBookByAuthor(authorId)).thenReturn(expectedBooks);

        // Act
        List<Book> actualBooks = bookController.getBookByAuthor(authorId);

        // Assert
        assertEquals(expectedBooks, actualBooks);
        verify(bookDAOInterface, times(1)).getBookByAuthor(authorId);
    }

    @Test
    public void testGetBookByGenre() {
      
        Integer genreId = 1;
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book("JK Rowling", "Harry Potter y la Piedra Filosofal", 1234567, "Fantástico", "Description"));
        expectedBooks.add(new Book("Sergi Torres", "La biología del presente", 1234568, "Autoayuda", "Description"));

        when(bookDAOInterface.getBookByGenre(genreId)).thenReturn(expectedBooks);


        List<Book> actualBooks = bookController.getBookByGenre(genreId);

     
        assertEquals(expectedBooks, actualBooks);
        verify(bookDAOInterface, times(1)).getBookByGenre(genreId);
    }

    @Test
    public void testGetBookByTitle() {

        String bookTitle = "Title 1";
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book("JK Rowling", "Harry Potter y la Piedra Filosofal", 1234567, "Fantástico", "Description"));
        expectedBooks.add(new Book("Sergi Torres", "La biología del presente", 1234568, "Autoayuda", "Description"));

        when(bookDAOInterface.getBookByTitle(bookTitle)).thenReturn(expectedBooks);

   
        List<Book> actualBooks = bookController.getBookByTitle(bookTitle);


        assertEquals(expectedBooks, actualBooks);
        verify(bookDAOInterface, times(1)).getBookByTitle(bookTitle);
    }
}