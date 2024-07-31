package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Genres;
import com.bookvibes.mvc.model.dao.GenreDAO;
import com.bookvibes.mvc.model.dao.GenreDAOInterface;

import java.util.List;

public class GenreController {

    private GenreDAOInterface genreDAOInterface;

    public GenreController(GenreDAOInterface genreDAOInterface) {
        this.genreDAOInterface = genreDAOInterface;
    }

    public List<Genres> getAll() {

        List<Genres> genresList = genreDAOInterface.getAll();
        return genresList;
    }
}
