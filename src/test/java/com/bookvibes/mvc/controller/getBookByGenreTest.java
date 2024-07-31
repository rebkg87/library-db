package com.bookvibes.mvc.controller;

import com.bookvibes.mvc.model.Books;
import com.bookvibes.mvc.model.dao.BookDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class getBookByGenreTest {

    @Autowired
    private Books getBookByGenre;

    @Test
    void GetBookByGenre(){
        List<Books> books = new ArrayList<>();
        books.add(new Books("author", "title", 23453232, "genre", "description"));


    }

}