package com.bookvibes.mvc.view;

import java.util.Scanner;

public class SearchView {

    private BookView bookView;

    public SearchView(BookView bookView) {
        this.bookView = bookView;
    }

    public void showSearchView() {

        Scanner scanner = new Scanner(System.in);

        String again;
        do {

            System.out.println("+----+----------+");
            System.out.println("| ID + OPCION   |");
            System.out.println("+----+----------+");
            System.out.println("| 1  | TITULO   |");
            System.out.println("| 2  | GENERO   |");
            System.out.println("| 3  | AUTOR    |");
            System.out.println("+----+----------+");

            System.out.println();
            System.out.print("Elija una opción: ");
            String search = scanner.next();

            switch (search) {
                case "1":
                    bookView.showBooksByTitle();
                    break;
                case "2":
                    bookView.showBooksByGenre();
                    break;
                case "3":
                    bookView.showBooksByAuthor();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }

            System.out.println();
            System.out.print("Desea elegir otra opción de búsqueda? (S/N): ");
            again = scanner.next();

        } while ("S".equalsIgnoreCase(again));
    }

}


