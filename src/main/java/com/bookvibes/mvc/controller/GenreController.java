package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Genres;
import com.bookvibes.mvc.model.dao.GenreDAO;

import java.util.List;

public class GenreController {
    public GenreController() {
    }

    public List<Genres> getAll() {
        GenreDAO genreDao = new GenreDAO();
        List<Genres> genresList = genreDao.getAll();
        return genresList;
    }
}
