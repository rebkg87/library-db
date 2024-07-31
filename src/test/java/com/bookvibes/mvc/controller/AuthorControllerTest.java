package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Authors;
import com.bookvibes.mvc.model.dao.AuthorDAOInterface;
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
public class AuthorControllerTest {

    @Mock
    private AuthorDAOInterface authorDAOInterface;

    @InjectMocks
    private AuthorController authorController;

    @Test
    public void testGetAll() {
        // Arrange
        List<Authors> expectedAuthors = new ArrayList<>();
        expectedAuthors.add(new Authors(1,"Author 1"));
        expectedAuthors.add(new Authors(2,"Author 2"));

        when(authorDAOInterface.getAll()).thenReturn(expectedAuthors);

        // Act
        List<Authors> actualAuthors = authorController.getAll();

        // Assert
        assertEquals(expectedAuthors, actualAuthors);
        verify(authorDAOInterface, times(1)).getAll();
    }
}