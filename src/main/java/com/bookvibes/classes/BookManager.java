package com.bookvibes.classes;

import com.bookvibes.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static com.bookvibes.structure.dao.BookDeleteDao.deleteBook;
import static com.bookvibes.structure.dao.BookDeleteDao.deleteBookByTitle;

public class BookManager {

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);

        System.out.println("Si deseas eliminar un libro por ID teclea 1, si es por título teclea 2.");
        int option = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            switch (option) {
                case 1:
                    System.out.println("Introduce el ID del libro a eliminar: ");
                    int bookId = scanner.nextInt();
                    deleteBook(conn, bookId);
                    break;
                case 2:
                    System.out.println("Introduce el título del libro a eliminar: ");
                    String title = scanner.nextLine();
                    deleteBookByTitle(conn, title);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
