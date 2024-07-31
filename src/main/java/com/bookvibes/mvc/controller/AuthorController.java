package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Authors;
import com.bookvibes.mvc.model.dao.AuthorDAO;
import com.bookvibes.mvc.model.dao.AuthorDAOInterface;

import java.util.List;

public class AuthorController {

    private AuthorDAOInterface authorDAOInterface;//declarando y sera usado en la linea 14 this.

    public AuthorController(AuthorDAOInterface authorDAOInterface) {
        this.authorDAOInterface = authorDAOInterface;//this.aut.. almacenará lo que contenga el parametro
    }

    public List<Authors> getAll() {

        List<Authors> authorsList = authorDAOInterface.getAll();
        return authorsList;
    }
}
