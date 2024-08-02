package com.bookvibes.mvc.view;

import com.bookvibes.mvc.config.DBConnection;
import com.bookvibes.mvc.model.Author;
import com.bookvibes.mvc.model.Book;
import com.bookvibes.mvc.model.Genre;
import com.bookvibes.mvc.controller.AuthorController;
import com.bookvibes.mvc.controller.BookController;
import com.bookvibes.mvc.controller.GenreController;
import com.bookvibes.mvc.util.BookVibesUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookView {

    private static final String ID = "ID";
    private static final String NAME = "NOMBRE";
    private static final String TITLE = "TITULO";
    private static final String DESCRIPTION = "DESCRIPCION";
    private static final String ISBN = "ISBN";

    private static final String FORMAT_AUTHOR = "| %-2s | %-30s |%n";
    private static final String FORMAT_AUTHOR_SEP = BookVibesUtil.getSeparator(new int[]{2, 30});

    private static final String FORMAT_GENRE = "| %-2s | %-30s |%n";
    private static final String FORMAT_GENRE_SEP = BookVibesUtil.getSeparator(new int[]{2, 30});

    private static final String FORMAT_BOOK = "| %-2s | %-30s | %-70s | %-15s |%n";
    private static final String FORMAT_BOOK_SEP = BookVibesUtil.getSeparator(new int[]{2, 30, 70, 15});
    private static final String FORMAT_BOOK_WITHOUT_DESCR = "| %-2s | %-40s | %-15s |%n";
    private static final String FORMAT_BOOK_WITHOUT_DESCR_SEP = BookVibesUtil.getSeparator(new int[]{2, 40, 15});

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

        System.out.println();
        System.out.println("################################");
        System.out.println("# CONSULTA DE LIBROS POR AUTOR #");
        System.out.println("################################");
        System.out.println();

        List<Author> authorList = authorController.getAll();
        System.out.println(FORMAT_AUTHOR_SEP);
        System.out.printf(FORMAT_AUTHOR, ID, NAME);
        System.out.println(FORMAT_AUTHOR_SEP);
        for (Author author : authorList) {
            BookVibesUtil.print(new int[]{2, 30}, new Object[]{author.getId(), author.getAuthor()});
        }
        System.out.println(FORMAT_AUTHOR_SEP);

        String again;
        do {

            System.out.println();
            System.out.print("Ingrese el ID del autor: ");
            int authorId = scanner.nextInt();

            List<Book> bookList = bookController.getBookByAuthor(authorId);

            System.out.println();

            if (bookList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");

                System.out.println();
                System.out.println(FORMAT_BOOK_SEP);
                System.out.printf(FORMAT_BOOK, ID, TITLE, DESCRIPTION, ISBN);
                System.out.println(FORMAT_BOOK_SEP);
                for (Book book : bookList) {
                    BookVibesUtil.print(new int[]{2, 30, 70, 15}, new Object[]{book.getId(), book.getTitle(), book.getDescription(), book.getIsbn()});
                }
                System.out.println(FORMAT_BOOK_SEP);

            } else {
                System.out.println("No hay resultados para su consulta.");
            }

            System.out.println();
            System.out.print("Desea consultar nuevamente? (S/N): ");
            again = scanner.next();

        } while ("S".equalsIgnoreCase(again));

    }

    public void showBooksByGenre() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("#################################");
        System.out.println("# CONSULTA DE LIBROS POR GENERO #");
        System.out.println("#################################");
        System.out.println();

        List<Genre> genreList = genreController.getAll();
        System.out.println(FORMAT_GENRE_SEP);
        System.out.printf(FORMAT_GENRE, ID, NAME);
        System.out.println(FORMAT_GENRE_SEP);
        for (Genre genre : genreList) {
            BookVibesUtil.print(new int[]{2, 30}, new Object[]{genre.getId(), genre.getGenre()});
        }
        System.out.println(FORMAT_GENRE_SEP);

        String again;
        do {

            System.out.println();
            System.out.print("Ingrese el ID del género: ");
            int genreId = scanner.nextInt();

            List<Book> bookList = bookController.getBookByGenre(genreId);

            System.out.println();

            if (bookList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");

                System.out.println();
                System.out.println(FORMAT_BOOK_WITHOUT_DESCR_SEP);
                System.out.printf(FORMAT_BOOK_WITHOUT_DESCR, ID, TITLE, ISBN);
                System.out.println(FORMAT_BOOK_WITHOUT_DESCR_SEP);
                for (Book book : bookList) {
                    BookVibesUtil.print(new int[]{2, 40, 15}, new Object[]{book.getId(), book.getTitle(), book.getIsbn()});
                }
                System.out.println(FORMAT_BOOK_WITHOUT_DESCR_SEP);

            } else {
                System.out.println("No hay resultados para su consulta.");
            }

            System.out.println();
            System.out.print("Desea consultar nuevamente? (S/N): ");
            again = scanner.next();

        } while ("S".equalsIgnoreCase(again));
    }

    // por title
    public void showBooksByTitle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("#################################");
        System.out.println("# CONSULTA DE LIBROS POR TITULO #");
        System.out.println("#################################");
        System.out.println();

        String again;

        do {
            System.out.print("Ingrese el título del libro que deséa buscar: ");
            String title = scanner.next();

            List<Book> bookList = bookController.getBookByTitle(title);

            System.out.println();

            if (bookList.size() > 0) {
                System.out.println("El resultado de la busqueda es:");

                System.out.println();
                System.out.println(FORMAT_BOOK_SEP);
                System.out.printf(FORMAT_BOOK, ID, TITLE, DESCRIPTION, ISBN);
                System.out.println(FORMAT_BOOK_SEP);
                for (Book book : bookList) {
                    BookVibesUtil.print(new int[]{2, 30, 70, 15}, new Object[]{book.getId(), book.getTitle(), book.getDescription(), book.getIsbn()});
                }
                System.out.println(FORMAT_BOOK_SEP);

            } else {
                System.out.println("No hay resultados para su consulta.");
            }

            System.out.println();
            System.out.print("Desea consultar nuevamente? (S/N): ");
            again = scanner.next();

        } while ("S".equalsIgnoreCase(again));

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
    public void showAllBooks() {
        List<Book> bookShowList = bookController.getAllBooks();
        for (Book bb : bookShowList) {
            System.out.println("| " + bb.getId() + " | " + bb.getTitle() + " | " + bb.getAuthor() + " | " + bb.getGenre() + " | " + bb.getIsbn() + " | ");
        }
    }
    public void addBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el título del libro:");
        String title = scanner.nextLine();

        List<String> authors = new ArrayList<>();
        System.out.println("Ingrese los autores del libro (separados por coma):");
        String[] authorsInput = scanner.nextLine().split(",");
        for (String author : authorsInput) {
            authors.add(author.trim());
        }

        System.out.println("Ingrese la descripción del libro:");
        String description = scanner.nextLine();
        List<String> genres = new ArrayList<>();

        System.out.println("Ingrese los géneros del libro (separados por coma):");
        String[] genresInput = scanner.nextLine().split(",");
        for (String genre : genresInput) {
            genres.add(genre.trim());
        }

        System.out.println("Ingrese el ISBN del libro:");
        long isbn = scanner.nextLong();
        scanner.nextLine(); // consume the newline

        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setIsbn(isbn);

        bookController.addBook(book, authors, genres);
    }

    public void editBook() {
    Scanner scanner = new Scanner(System.in);
    
        System.out.println("Ingrese el ID del libro que desea editar: ");
    int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("¿Desea editar el título? (s/n): ");
    String editTitle = scanner.nextLine();
    String newTitle = null;
        if (editTitle.equalsIgnoreCase("s")) {
        System.out.println("Ingrese el nuevo título: ");
        newTitle = scanner.nextLine();
    }

        System.out.println("¿Desea editar la descripción? (s/n): ");
    String editDescription = scanner.nextLine();
    String newDescription = null;
        if (editDescription.equalsIgnoreCase("s")) {
        System.out.println("Ingrese la nueva descripción: ");
        newDescription = scanner.nextLine();
    }

        System.out.println("¿Desea editar el ISBN? (s/n): ");
    String editIsbn = scanner.nextLine();
    Long newIsbn = null;
        if (editIsbn.equalsIgnoreCase("s")) {
        System.out.println("Ingrese el nuevo ISBN: ");
        newIsbn = scanner.nextLong();
        scanner.nextLine();
    }

        System.out.println("¿Desea editar los autores? (s/n): ");
    String editAuthors = scanner.nextLine();
    int[] newAuthorIds = null;
        if (editAuthors.equalsIgnoreCase("s")) {
        List<Integer> authorIdsList = new ArrayList<>();
        while (true) {
            System.out.println("Ingrese el ID del nuevo autor (o '0' para finalizar): ");
            int authorId = scanner.nextInt();
            if (authorId == 0) break;
            authorIdsList.add(authorId);
        }
        newAuthorIds = authorIdsList.stream().mapToInt(i -> i).toArray();
    }

        System.out.println("¿Desea editar los géneros? (s/n): ");
    String editGenres = scanner.nextLine();
    int[] newGenreIds = null;
        if (editGenres.equalsIgnoreCase("s")) {
        List<Integer> genreIdsList = new ArrayList<>();
        while (true) {
            System.out.println("Ingrese el ID del nuevo género (o '0' para finalizar): ");
            int genreId = scanner.nextInt();
            if (genreId == 0) break;
            genreIdsList.add(genreId);
        }
        newGenreIds = genreIdsList.stream().mapToInt(i -> i).toArray();
    }
        bookController.editBook(bookId, newTitle, newDescription, newIsbn, newAuthorIds, newGenreIds);
        
}
}

