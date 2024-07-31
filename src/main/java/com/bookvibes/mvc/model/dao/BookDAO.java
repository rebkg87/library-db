package com.bookvibes.mvc.model.dao;
import com.bookvibes.mvc.config.DBConnection;
import com.bookvibes.mvc.model.Books;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements BookDAOInterface {

    private static String TABLENAME = "books";

    private static String GET_ALL = "SELECT b.id, b.title, b.description, b.isbn FROM " + TABLENAME + " AS b ";
    private static String GET_BY_AUTHOR = GET_ALL + "JOIN authors_books AS ab ON ab.id_book = b.id WHERE ab.id_author = ?";
    private static String GET_BY_GENRE = GET_ALL + "JOIN genres_books AS gb ON gb.id_book = b.id WHERE gb.id_genre = ?";
    private static String GET_BY_TITLE= GET_ALL + "WHERE LOWER(b.title) LIKE '%' || LOWER(?) || '%' ";


    @Override
    public List<Books> getBookByAuthor(Integer authorId) {

        List<Books> bookList = new ArrayList<>();

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(GET_BY_AUTHOR);
            ps.setInt(1, authorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Books bookBean = new Books();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getLong("isbn"));

                bookList.add(bookBean);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
//            DBConnection.closeConnection();
        }

        return bookList;
    }

    //buscar por género
    @Override
    public List<Books> getBookByGenre(Integer genreId) {
        List<Books> booksList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_BY_GENRE);
            ps.setInt(1, genreId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Books bookBean = new Books();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getLong("isbn"));
                booksList.add(bookBean);

            }
        } catch (SQLException e) {
            throw new RuntimeException((e));
        } finally {
            //            DBConnection.closeConnection();
        }
        return booksList;
    }

    //buscar por title
    @Override
    public List<Books> getBookByTitle(String bookTitle) {
        List<Books> booksList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_BY_TITLE);
            ps.setString(1, bookTitle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Books bookBean = new Books();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getLong("isbn"));
                booksList.add(bookBean);

            }
        } catch (SQLException e) {
            throw new RuntimeException((e));
        } finally {
            //            DBConnection.closeConnection();
        }
        return booksList;
    }

    //Mostrar libros

    @Override
    public void showBook(Connection conn) throws SQLException {
        String selectCatalogSQL = "SELECT b.id, b.title, a.author AS author FROM books AS b " +
                "JOIN authors_books AS ab ON b.id = ab.id_book " +
                "JOIN authors AS a ON ab.id_author = a.id";

        try (PreparedStatement pstmt = conn.prepareStatement(selectCatalogSQL);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("ID | Título | Autor");
            System.out.println("---------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                System.out.println(id + " | " + title + " | " + author);
            }
        }
    }

    //eliminar por ID

    @Override
    public void deleteBook (Connection conn, int bookId) throws SQLException{
        String deleteAuthorBookSQL = "DELETE FROM authors_books WHERE id_book = ?";
        String deleteGenreBookSQL= "DELETE FROM genres_books WHERE id_book = ?";
        String deleteBookSQL = "DELETE FROM books WHERE id = ?";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(deleteAuthorBookSQL)) {
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(deleteGenreBookSQL)) {
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(deleteBookSQL)) {
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Libro eliminado exitosamente.");

        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            System.out.println("Error al eliminar el libro: " + e.getMessage());
        } finally {
            conn.setAutoCommit(true);
        }

    }

//    public static void main(String[] args) {
//        BookDao bookDao = new BookDao();
//        List<Books> bookShowList = bookDao.getBookByTitle("SoMbra");
//        for (Books bb : bookShowList) {
//            System.out.println("| " + bb.getId() + " | " + bb.getTitle() + " | " + bb.getIsbn() + " | " );
//        }
//    }


}
