package com.bookvibes.mvc;

import com.bookvibes.mvc.controller.AuthorController;
import com.bookvibes.mvc.controller.BookController;
import com.bookvibes.mvc.controller.GenreController;
import com.bookvibes.mvc.model.dao.*;
import com.bookvibes.mvc.view.BookView;
import com.bookvibes.mvc.view.MenuView;
import com.bookvibes.mvc.view.SearchView;

public class App {
    public static void main(String[] args) {

        // dao
        BookDAOInterface bookDAOInterface = new BookDAO();
        AuthorDAOInterface authorDAOInterface=new AuthorDAO();
        GenreDAOInterface genreDAOInterface=new GenreDAO();

        // controller
        BookController bookController = new BookController (bookDAOInterface);
        AuthorController authorController=new AuthorController(authorDAOInterface);
        GenreController  genreController=new GenreController(genreDAOInterface);

        //view
        BookView bookView = new BookView(bookController,authorController,genreController);
        SearchView searchView = new SearchView(bookView);
        MenuView menuView = new MenuView(bookView,searchView);

        // menuView
        menuView.showMenuView();
    }
}
