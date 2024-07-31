package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Genre;
import com.bookvibes.mvc.model.dao.GenreDAOInterface;

import java.util.List;

public class GenreController {

    private GenreDAOInterface genreDAOInterface;

    public GenreController(GenreDAOInterface genreDAOInterface) {
        this.genreDAOInterface = genreDAOInterface;
    }

    public List<Genre> getAll() {

        List<Genre> genreList = genreDAOInterface.getAll();
        return genreList;
    }
}
