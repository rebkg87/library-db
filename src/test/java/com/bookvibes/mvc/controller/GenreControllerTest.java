package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Genre;
import com.bookvibes.mvc.model.dao.GenreDAOInterface;
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
public class GenreControllerTest {

    @Mock
    private GenreDAOInterface genreDAOInterface;

    @InjectMocks
    private GenreController genreController;

    @Test
    public void testGetAll() {

        List<Genre> expectedGenres = Arrays.asList(
                new Genre(1, "Misterio"),
                new Genre(2, "Suspense")
        );

        when(genreDAOInterface.getAll()).thenReturn(expectedGenres);

        List<Genre> actualGenres = genreController.getAll();

        assertEquals(expectedGenres, actualGenres);
        verify(genreDAOInterface, times(1)).getAll();
    }
}