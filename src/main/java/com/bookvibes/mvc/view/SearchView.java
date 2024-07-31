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
            int opBuscar = scanner.nextInt();
                if (opBuscar == 1) {
                    bookView.showBooksByTitle();
                } else if (opBuscar == 2) {
                    bookView.showBooksByGenre();
                } else if (opBuscar == 3) {
                    bookView.showBooksByAuthor();
                } else {
                    System.out.println("opcion incorrecta");
                }

            System.out.print("Desea otra opcion del MENU? (S/N):");
            again = scanner.next();
        } while ("S".equalsIgnoreCase(again));
    }

}
