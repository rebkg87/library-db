package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Author;
import com.bookvibes.mvc.model.dao.AuthorDAOInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {

    @Mock
    private AuthorDAOInterface authorDAOInterface;

    @InjectMocks
    private AuthorController authorController;

    @Test
    public void testGetAll() {
        List<Author> expectedAuthors = Arrays.asList(
                new Author(1, "JK Rowling"),
                new Author(2, "Paulo Cohelo")
        );

        when(authorDAOInterface.getAll()).thenReturn(expectedAuthors);

        List<Author> actualAuthors = authorController.getAll();

        assertEquals(expectedAuthors, actualAuthors);
        verify(authorDAOInterface, times(1)).getAll();
    }
}