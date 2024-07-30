package com.bookvibes.mvc.view;

import com.bookvibes.mvc.DBConnection;
import com.bookvibes.mvc.model.Authors;
import com.bookvibes.mvc.model.Books;
import com.bookvibes.mvc.model.Genres;
import com.bookvibes.mvc.controller.AuthorController;
import com.bookvibes.mvc.controller.BookController;
import com.bookvibes.mvc.controller.GenreController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookView {
    public BookView() {

    }

    public void showBooksByAuthor() {

        Scanner scanner = new Scanner(System.in);

        // llamamos a las clases de servicio y las instanciamos
        AuthorController authorController = new AuthorController();
        BookController bookController = new BookController();


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
        GenreController genreController = new GenreController();
        BookController bookController = new BookController();

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

        BookController bookController = new BookController();

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
            System.out.println("El resultado de la busqueda es:");
            for (Books books : booksList) {
                System.out.println("| " + books.getId() + " | " + books.getTitle() + " | " + books.getDescription() + " | " + books.getIsbn());
            }
            System.out.print("Desea consultar nuevamente? (S/N): ");
            again = scanner.next();
        } while (again.equalsIgnoreCase("S"));

    }

    public void deleteBookFcn(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("----------------------------");
            System.out.println("CONSULTA DE LIBROS");
            System.out.println("----------------------------");
            showBook(conn);
            System.out.println();

            System.out.println("Introduce el ID del libro a eliminar: ");
            int bookId = scanner.nextInt();
            deleteBook(conn, bookId);


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteBook(Connection conn, int bookId) {
    }

    private void showBook(Connection conn) {
    }

    public static void main(String[] args) {
        BookView bookView = new BookView();

        bookView.deleteBook();
    }

}
