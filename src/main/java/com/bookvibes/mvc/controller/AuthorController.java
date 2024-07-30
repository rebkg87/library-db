package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Authors;
import com.bookvibes.mvc.model.dao.AuthorDAO;

import java.util.List;

public class AuthorController {
    public AuthorController() {
    }

    // todos los metodos tienen 4 partes
    // NIVEL_ACCESO        | TIPO                                                | NOMBRE | (PARAMETROS_ENTRADA)
    // public, private etc | void(no devuelve nada), Dato(Integer, String, List) | nombre | vacio, uno o muchos

    public List<Authors> getAll() {
        AuthorDAO authorDao = new AuthorDAO();
        List<Authors> authorsList = authorDao.getAll();
        return authorsList;
    }
}
