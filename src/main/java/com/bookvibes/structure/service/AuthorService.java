package com.bookvibes.structure.service;

import com.bookvibes.classes.Authors;
import com.bookvibes.structure.dao.AuthorDao;

import java.util.List;

public class AuthorService {
    public AuthorService() {
    }

    // todos los metodos tienen 4 partes
    // NIVEL_ACCESO        | TIPO                                                | NOMBRE | (PARAMETROS_ENTRADA)
    // public, private etc | void(no devuelve nada), Dato(Integer, String, List) | nombre | vacio, uno o muchos

    public List<Authors> getAll() {
        AuthorDao authorDao = new AuthorDao();
        List<Authors> authorsList = authorDao.getAll();
        return authorsList;
    }
}
