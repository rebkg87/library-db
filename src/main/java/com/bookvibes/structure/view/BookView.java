package com.bookvibes.structure.view;

import com.bookvibes.classes.Authors;
import com.bookvibes.classes.Books;
import com.bookvibes.classes.Genres;
import com.bookvibes.structure.service.AuthorService;
import com.bookvibes.structure.service.BookService;
import com.bookvibes.structure.service.GenreService;

import java.util.List;
import java.util.Scanner;

public class BookView {
    public BookView() {

    }

    public void showBooksByAuthor() {

        Scanner scanner = new Scanner(System.in);

        // llamamos a las clases de servicio y las instanciamos
        AuthorService authorService = new AuthorService();
        BookService bookService = new BookService();

        //mostar titulo de la pagina
        System.out.println("----------------------------");
        System.out.println("CONSULTA DE LIBROS POR AUTOR");
        System.out.println("----------------------------");
        System.out.println("");

        //mostrar los autores con id(consultar al servicio)
        List<Authors> authorsList = authorService.getAll();
        for (Authors authors : authorsList) {
            System.out.println(authors.getId() + " | " + authors.getAuthor());
        }

        System.out.println();

        String again;
        do {
            //preguntar al usuario por id del autor cuyos libros desee buscar
            System.out.print("Ingrese el ID del autor: ");
            int authorId = scanner.nextInt();

            //llamar al servicio para la consulta de libros por autor(consultar al servicio)
            List<Books> booksList = bookService.getBookByAuthor(authorId);

            //si hay resultados mostrar el resultado sino mostrar msj no hay resultados para ese id
            if (booksList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");
                for (Books books : booksList) {
                    System.out.println(books.getId() + " | " + books.getTitle() + " | " + books.getDescription() + " | " + books.getIsbn());
                }
            } else {
                System.out.println("No hay resultados para su consulta.");
            }

            //preguntar si desea hacer otra consulta
            System.out.print("Desea consultar nuevamente? (S/N): ");
            again = scanner.next();

        } while (again.equalsIgnoreCase("S"));

    }

    public void showBooksByGenre() {
        Scanner scanner = new Scanner(System.in);
        GenreService genreService = new GenreService();
        BookService bookService = new BookService();

        System.out.println("----------------------------");
        System.out.println("CONSULTA DE LIBROS POR GENERO");
        System.out.println("----------------------------");
        System.out.println("");

        List<Genres> genresList = genreService.getAll();
        for (Genres genre : genresList) {
            System.out.println(genre.getId() + "|" + genre.getGenre());
        }
        String again;
        do {
            System.out.print("Ingrese el ID del género: ");
            int genreId = scanner.nextInt();

            List<Books> booksList = bookService.getBookByGenre(genreId);
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

    public static void main(String[] args) {
        BookView bookView = new BookView();

        bookView.showBooksByGenre();
    }
}
