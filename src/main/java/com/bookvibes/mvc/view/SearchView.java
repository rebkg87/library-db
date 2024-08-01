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
        String search;
        do {
            System.out.println("Deseas buscar por: título (1), género (2), autor (3)");
            search = scanner.nextLine();

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
            System.out.print("¿Desea realizar otra búsqueda? (S/N): ");
            again = scanner.next();

        } while ("S".equalsIgnoreCase(again));
    }
}


