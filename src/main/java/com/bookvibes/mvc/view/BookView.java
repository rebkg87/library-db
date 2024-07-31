package com.bookvibes.mvc.view;

import com.bookvibes.mvc.model.Authors;
import com.bookvibes.mvc.model.Books;
import com.bookvibes.mvc.model.Genres;
import com.bookvibes.mvc.controller.AuthorController;
import com.bookvibes.mvc.controller.BookController;
import com.bookvibes.mvc.controller.GenreController;

import java.util.List;
import java.util.Scanner;

public class BookView {

    private BookController bookController;
    private AuthorController authorController;
    private GenreController genreController;

    public BookView(BookController bookController, AuthorController authorController, GenreController genreController) {
        this.bookController = bookController;
        this.authorController = authorController;
        this.genreController = genreController;
    }

    public void showBooksByAuthor() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------");
        System.out.println("CONSULTA DE LIBROS POR AUTOR");
        System.out.println("----------------------------");
        System.out.println("");


        List<Authors> authorsList = authorController.getAll();
        for (Authors authors : authorsList) {
            System.out.println(authors.getId() + " | " + authors.getAuthor());
        }

        System.out.println();

        String again;
        do {

            System.out.print("Ingrese el ID del autor: ");
            int authorId = scanner.nextInt();


            List<Books> booksList = bookController.getBookByAuthor(authorId);


            if (booksList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");
                for (Books books : booksList) {
                    System.out.println(books.getId() + " | " + books.getTitle() + " | " + books.getDescription() + " | " + books.getIsbn());
                }
            } else {
                System.out.println("No hay resultados para su consulta.");
            }


            System.out.print("Desea consultar nuevamente? (S/N): ");
            again = scanner.next();

        } while (again.equalsIgnoreCase("S"));

    }

    public void showBooksByGenre() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------");
        System.out.println("CONSULTA DE LIBROS POR GENERO");
        System.out.println("----------------------------");
        System.out.println("");

        List<Genres> genresList = genreController.getAll();
        for (Genres genre : genresList) {
            System.out.println(genre.getId() + "|" + genre.getGenre());
        }
        String again;
        do {
            System.out.print("Ingrese el ID del género: ");
            int genreId = scanner.nextInt();

            List<Books> booksList = bookController.getBookByGenre(genreId);
            if (booksList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");
                for (Books books : booksList) {
                    System.out.println(books.getId() + " | " + books.getTitle() + " | " + books.getIsbn());
                }
            } else {
                System.out.println("No hay resultados para su consulta.");
            }
            System.out.print("Desea consultar nuevamente? (S/N): ");
            again = scanner.next();
        } while (again.equalsIgnoreCase("S"));
    }

    // por title
    public void showBooksByTitle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------");
        System.out.println("CONSULTA DE LIBROS POR TITULO");
        System.out.println("----------------------------");
        System.out.println("");

        System.out.println();
        String again;

        do {
            System.out.print("Ingrese el título del libro que deséa buscar: ");
            String title = scanner.next();
            List<Books> booksList = bookController.getBookByTitle(title);
            if (booksList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");
                for (Books books : booksList) {
                    System.out.println("| " + books.getId() + " | " + books.getTitle() + " | " + books.getDescription() + " | " + books.getIsbn());
                }
            } else {
                System.out.println("No hay resultados para su consulta.");
            }
            System.out.print("Desea consultar nuevamente? (S/N): ");
            again = scanner.next();
        } while (again.equalsIgnoreCase("S"));

    }

    public void showAllBooks() {
        List<Books> bookShowList = bookController.getAllBooks();
        for (Books bb : bookShowList) {
            System.out.println("| " + bb.getId() + " | " + bb.getTitle() + " | " + bb.getAuthor() + " | " + bb.getGenre() + " | " + bb.getIsbn() + " | ");
        }
    }


}
