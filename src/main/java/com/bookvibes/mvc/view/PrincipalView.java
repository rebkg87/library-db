package com.bookvibes.mvc.view;

import com.bookvibes.mvc.controller.BookController;

import java.util.Scanner;

public class PrincipalView {

    private BookView bookView;

    public PrincipalView(BookView bookView) {
        this.bookView = bookView;
    }

    public void showView() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("SISTEMA DE BIBLIOTECA");

        String again;
        do {

            System.out.println("(1)  BUSCAR");//1buscar,2editar,3eliminar, 4mostrar
            System.out.println("(2) EDITAR");//1buscar,2editar,3eliminar, 4mostrar
            System.out.println("(3) Eliminar");//1buscar,2editar,3eliminar, 4mostrar
            System.out.println("(4) Mostrar todos los libros");//1buscar,2editar,3eliminar, 4mostrar
            System.out.println("que opcion quieres:");//1buscar,2editar,3eliminar, 4mostrar
            int option = scanner.nextInt();
            if (option == 1) {
                System.out.println("que deseas buscar por : titulo (1), genero(2), autor(3)");
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
            } else if (option == 4) {
                bookView.showAllBooks();
            } else if (option == 2 || option == 3) {
                System.out.println("OPCIONES NO IMPLEMENTADAS AUN.");
            }

            System.out.print("Desea otra opcion del MENU? (S/N):");
            again = scanner.next();
        } while ("S".equalsIgnoreCase(again));
    }
}
