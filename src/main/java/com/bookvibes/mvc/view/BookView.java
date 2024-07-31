package com.bookvibes.mvc.view;

import com.bookvibes.mvc.config.DBConnection;
import com.bookvibes.mvc.model.Author;
import com.bookvibes.mvc.model.Book;
import com.bookvibes.mvc.model.Genre;
import com.bookvibes.mvc.controller.AuthorController;
import com.bookvibes.mvc.controller.BookController;
import com.bookvibes.mvc.controller.GenreController;

import java.sql.Connection;
import java.sql.SQLException;
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


        List<Author> authorList = authorController.getAll();
        for (Author author : authorList) {
            System.out.println(author.getId() + " | " + author.getAuthor());
        }

        System.out.println();

        String again;
        do {

            System.out.print("Ingrese el ID del autor: ");
            int authorId = scanner.nextInt();


            List<Book> bookList = bookController.getBookByAuthor(authorId);


            if (bookList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");
                for (Book book : bookList) {
                    System.out.println(book.getId() + " | " + book.getTitle() + " | " + book.getDescription() + " | " + book.getIsbn());
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

        List<Genre> genreList = genreController.getAll();
        for (Genre genre : genreList) {
            System.out.println(genre.getId() + "|" + genre.getGenre());
        }
        String again;
        do {
            System.out.print("Ingrese el ID del género: ");
            int genreId = scanner.nextInt();

            List<Book> bookList = bookController.getBookByGenre(genreId);
            if (bookList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");
                for (Book book : bookList) {
                    System.out.println(book.getId() + " | " + book.getTitle() + " | " + book.getIsbn());
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
            List<Book> bookList = bookController.getBookByTitle(title);
            if (bookList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");
                for (Book book : bookList) {
                    System.out.println("| " + book.getId() + " | " + book.getTitle() + " | " + book.getDescription() + " | " + book.getIsbn());
                }
            } else {
                System.out.println("No hay resultados para su consulta.");
            }
            System.out.print("Desea consultar nuevamente? (S/N): ");
            again = scanner.next();
        } while (again.equalsIgnoreCase("S"));

    }

    public void deleteBook() {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("----------------------------");
            System.out.println("CONSULTA DE LIBROS");
            System.out.println("----------------------------");
            bookController.showBooks();
            System.out.println();

            System.out.println("Introduce el ID del libro a eliminar: ");
            int bookId = scanner.nextInt();
            bookController.deleteBook(bookId);


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }


}
