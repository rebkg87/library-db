package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Author;
import com.bookvibes.mvc.model.dao.AuthorDAOInterface;

import java.util.List;

public class AuthorController {

    private AuthorDAOInterface authorDAOInterface;//declarando y sera usado en la linea 14 this.

    public AuthorController(AuthorDAOInterface authorDAOInterface) {
        this.authorDAOInterface = authorDAOInterface;//this.aut.. almacenará lo que contenga el parametro
    }

    public List<Author> getAll() {

        List<Author> authorList = authorDAOInterface.getAll();
        return authorList;
    }
}
