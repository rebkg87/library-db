package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Books;
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
        // Arrange
        Integer authorId = 1;
        List<Books> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Books("JK Rowling", "Harry Potter y la Piedra Filosofal", 1234567, "Fantástico", "Description"));
        expectedBooks.add(new Books("Sergi Torres", "La biología del presente", 1234568, "Autoayuda", "Description"));

        when(bookDAOInterface.getBookByAuthor(authorId)).thenReturn(expectedBooks);

        // Act
        List<Books> actualBooks = bookController.getBookByAuthor(authorId);

        // Assert
        assertEquals(expectedBooks, actualBooks);
        verify(bookDAOInterface, times(1)).getBookByAuthor(authorId);
    }

    @Test
    public void testGetBookByGenre() {
        // Arrange
        Integer genreId = 1;
        List<Books> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Books("JK Rowling", "Harry Potter y la Piedra Filosofal", 1234567, "Fantástico", "Description"));
        expectedBooks.add(new Books("Sergi Torres", "La biología del presente", 1234568, "Autoayuda", "Description"));

        when(bookDAOInterface.getBookByGenre(genreId)).thenReturn(expectedBooks);

        // Act
        List<Books> actualBooks = bookController.getBookByGenre(genreId);

        // Assert
        assertEquals(expectedBooks, actualBooks);
        verify(bookDAOInterface, times(1)).getBookByGenre(genreId);
    }

    @Test
    public void testGetBookByTitle() {
        // Arrange
        String bookTitle = "Title 1";
        List<Books> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Books("JK Rowling", "Harry Potter y la Piedra Filosofal", 1234567, "Fantástico", "Description"));
        expectedBooks.add(new Books("Sergi Torres", "La biología del presente", 1234568, "Autoayuda", "Description"));

        when(bookDAOInterface.getBookByTitle(bookTitle)).thenReturn(expectedBooks);

        // Act
        List<Books> actualBooks = bookController.getBookByTitle(bookTitle);

        // Assert
        assertEquals(expectedBooks, actualBooks);
        verify(bookDAOInterface, times(1)).getBookByTitle(bookTitle);
    }
}