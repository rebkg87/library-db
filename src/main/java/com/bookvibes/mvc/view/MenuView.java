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
            System.out.println("\n----- Menú Principal -----");
            System.out.println("1. Ver catálogo de libros");
            System.out.println("2. Agregar libro");
            System.out.println("3. Editar libro");
            System.out.println("4. Buscar libro");
            System.out.println("5. Eliminar libro");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            choice = scanner.next();

            switch (choice) {
                case "1":
                    bookView.showBooksByGenre();
                    break;
                case "2":
                    bookView.showBooksByGenre();
                    break;
                case "3":
                    bookView.showBooksByGenre();
                    break;
                case "4":
                    System.out.println("Deseas buscar por: título (1), género (2), autor (3)");
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

