package com.bookvibes.mvc.view;

import java.util.Scanner;

public class MenuView {
    private BookView bookView;
    private SearchView searchView;

    public MenuView(BookView bookView, SearchView searchView) {
        this.bookView = bookView;
        this.searchView = searchView;
    }

    public void showMenuView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SISTEMA DE BIBLIOTECA");
        String choice;
        String again;

        do {
            System.out.println();
            System.out.println("+----------------+");
            System.out.println("| MENU PRINCIPAL |");
            System.out.println("+----------------+");
            System.out.println();
            System.out.println("+----+------------------------+");
            System.out.println("| ID | OPCION                 |");
            System.out.println("+----+------------------------+");
            System.out.println("| 1  | VER CATALOGO DE LIBROS |");
            System.out.println("| 2  | AGREGAR LIBRO          |");
            System.out.println("| 3  | EDITAR LIBRO           |");
            System.out.println("| 4  | BUSCAR LIBRO           |");
            System.out.println("| 5  | ELIMINAR LIBRO         |");
            System.out.println("| 6  | SALIR                  |");
            System.out.println("+----+------------------------+");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            choice = scanner.next();

            switch (choice) {
                case "1":
                    bookView.showAllBooks();
                    break;
                case "2":
                    bookView.addBook();
                    break;
                case "3":
                    bookView.showBooksByGenre();
                    break;
                case "4":
                    searchView.showSearchView();
                    break;
                case "5":
                    bookView.deleteBook();
                    break;
                case "6":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }

            if (!choice.equals("6")) {
                System.out.print("Desea otra opción del MENU? (S/N): ");
                again = scanner.next();
            } else {
                again = "N";
            }

        } while ("S".equalsIgnoreCase(again));

        scanner.close();
    }
}

