package com.bookvibes.structure.service;

import com.bookvibes.classes.Genres;
import com.bookvibes.structure.dao.GenreDao;

import java.util.List;

public class GenreService {
    public GenreService() {
    }

    public List<Genres> getAll() {
        GenreDao genreDao = new GenreDao();
        List<Genres> genresList = genreDao.getAll();
        return genresList;
    }
}
