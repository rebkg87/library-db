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
            search = scanner.next();

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

            again = scanner.next();
        } while ("S".equalsIgnoreCase(again));

        scanner.close();
    }

}


