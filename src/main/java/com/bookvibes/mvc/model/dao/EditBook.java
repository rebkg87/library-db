package com.bookvibes.mvc.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bookvibes.DBConnection;

public class EditBook {

    public void editBook(int bookId, String newTitle, String newDescription, Long newIsbn, int[] newAuthorIds, int[] newGenreIds) {
        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false);

            updateBookDetails(connection, bookId, newTitle, newDescription, newIsbn);
            if (newAuthorIds != null) {
                updateBookRelations(connection, bookId, newAuthorIds, "authors_books", "id_author");
            }
            if (newGenreIds != null) {
                updateBookRelations(connection, bookId, newGenreIds, "genres_books", "id_genre");
            }

            connection.commit();
            System.out.println("Los datos se han actualizado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBookDetails(Connection connection, int bookId, String newTitle, String newDescription, Long newIsbn) throws SQLException {
        String updateBookSQL = "UPDATE books SET title = COALESCE(?, title), description = COALESCE(?, description), isbn = COALESCE(?, isbn) WHERE id = ?";
        try (PreparedStatement updateBookStmt = connection.prepareStatement(updateBookSQL)) {
            updateBookStmt.setString(1, newTitle);
            updateBookStmt.setString(2, newDescription);
            if (newIsbn != null) {
                updateBookStmt.setLong(3, newIsbn);
            } else {
                updateBookStmt.setNull(3, java.sql.Types.BIGINT);
            }
            updateBookStmt.setInt(4, bookId);
            updateBookStmt.executeUpdate();
        }
    }

    private void updateBookRelations(Connection connection, int bookId, int[] newEntityIds, String tableName, String entityIdColumn) throws SQLException {
        String deleteSQL = "DELETE FROM " + tableName + " WHERE id_book = ?";
        String insertSQL = "INSERT INTO " + tableName + " (id_book, " + entityIdColumn + ") VALUES (?, ?)";

        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL);
             PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
            deleteStmt.setInt(1, bookId);
            deleteStmt.executeUpdate();

            for (int entityId : newEntityIds) {
                insertStmt.setInt(1, bookId);
                insertStmt.setInt(2, entityId);
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EditBook bookDAO = new EditBook();

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

        bookDAO.editBook(bookId, newTitle, newDescription, newIsbn, newAuthorIds, newGenreIds);
        scanner.close();
    }
}