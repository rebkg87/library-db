package com.bookvibes.mvc.model.dao;
import com.bookvibes.mvc.config.DBConnection;
import com.bookvibes.mvc.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements BookDAOInterface {


    private static String TABLENAME = "books";
    private static String GET_ALL = "SELECT b.id, b.title, b.description, b.isbn FROM " + TABLENAME + " AS b ";
    private static String GET_BY_AUTHOR = GET_ALL + "JOIN authors_books AS ab ON ab.id_book = b.id WHERE ab.id_author = ?";
    private static String GET_BY_GENRE = GET_ALL + "JOIN genres_books AS gb ON gb.id_book = b.id WHERE gb.id_genre = ?";
    private static String GET_BY_TITLE = GET_ALL + "WHERE LOWER(b.title) LIKE '%' || LOWER(?) || '%' ";
    private static final String CHECK_BOOK_EXISTENCE = "SELECT COUNT(*) FROM " + TABLENAME + " WHERE LOWER(title) = LOWER(?)";

    @Override
    public List<Book> getBookByAuthor(Integer authorId) {

        List<Book> bookList = new ArrayList<>();

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(GET_BY_AUTHOR);
            ps.setInt(1, authorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Book bookBean = new Book();
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
    public List<Book> getBookByGenre(Integer genreId) {
        List<Book> bookList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_BY_GENRE);
            ps.setInt(1, genreId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Book bookBean = new Book();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getLong("isbn"));
                bookList.add(bookBean);

            }
        } catch (SQLException e) {
            throw new RuntimeException((e));
        }
        return bookList;
    }

    //buscar por title
    @Override
    public List<Book> getBookByTitle(String bookTitle) {
        List<Book> bookList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(GET_BY_TITLE);
            ps.setString(1, bookTitle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Book bookBean = new Book();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getLong("isbn"));
                bookList.add(bookBean);

            }
        } catch (SQLException e) {
            throw new RuntimeException((e));
        }
        return bookList;
    }

    //Mostrar libros para ID

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
    public void deleteBook(Connection conn, int bookId) throws SQLException {
        String deleteAuthorBookSQL = "DELETE FROM authors_books WHERE id_book = ?";
        String deleteGenreBookSQL = "DELETE FROM genres_books WHERE id_book = ?";
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

    //Mostrar todos los libros
    @Override
    public List<Book> showBooks() {

        List<Book> bookList = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("select b.id, b.title, b.description, b.isbn, a.author, g.genre from books as b\n" +
                    "join authors_books as ab on b.id = ab.id_book\n" +
                    "join authors as a on a.id = ab.id_author\n" +
                    "join genres_books as gb on b.id = gb.id_book\n" +
                    "join genres as g on g.id = gb.id_genre;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Book bookBean = new Book();
                bookBean.setId(rs.getInt("id"));
                bookBean.setTitle(rs.getString("title"));
                bookBean.setDescription(rs.getString("description"));
                bookBean.setIsbn(rs.getLong("isbn"));
                bookBean.setAuthor(rs.getString("author"));
                bookBean.setGenre(rs.getString("genre"));

                bookList.add(bookBean);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }


    @Override
    public void addBook(Book book, List<String> authors, List<String> genres) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            String insertBookSQL = "INSERT INTO books (title, description, isbn) VALUES (?, ?, ?) RETURNING id";
            try (PreparedStatement pstmt = conn.prepareStatement(insertBookSQL)) {
                pstmt.setString(1, book.getTitle());
                pstmt.setString(2, book.getDescription());
                pstmt.setLong(3, book.getIsbn());
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        int bookId = rs.getInt(1);
                        insertAuthorsAndGenres(conn, bookId, authors, genres);
                    }
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el libro: " + e.getMessage(), e);
        }
    }

    private void insertAuthorsAndGenres(Connection conn, int bookId, List<String> authors, List<String> genres) throws SQLException {
        String insertAuthorSQL = "INSERT INTO authors (author) VALUES (?) ON CONFLICT (author) DO NOTHING";
        String insertAuthorsBooksSQL = "INSERT INTO authors_books (id_author, id_book) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertAuthorSQL)) {
            for (String author : authors) {
                pstmt.setString(1, author);
                pstmt.executeUpdate();
                int authorId = getAuthorId(conn, author);
                try (PreparedStatement ps = conn.prepareStatement(insertAuthorsBooksSQL)) {
                    ps.setInt(1, authorId);
                    ps.setInt(2, bookId);
                    ps.executeUpdate();
                }
            }
        }
        String insertGenreSQL = "INSERT INTO genres (genre) VALUES (?) ON CONFLICT (genre) DO NOTHING";
        String insertGenresBooksSQL = "INSERT INTO genres_books (id_genre, id_book) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertGenreSQL)) {
            for (String genre : genres) {
                pstmt.setString(1, genre);
                pstmt.executeUpdate();
                int genreId = getGenreId(conn, genre);
                try (PreparedStatement ps = conn.prepareStatement(insertGenresBooksSQL)) {
                    ps.setInt(1, genreId);
                    ps.setInt(2, bookId);
                    ps.executeUpdate();
                }
            }
        }
    }

    @Override
    public int getAuthorId(Connection conn, String author) throws SQLException {
        String selectAuthorSQL = "SELECT id FROM authors WHERE author = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectAuthorSQL)) {
            pstmt.setString(1, author);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Author not found: " + author);
                }
            }
        }
    }

    @Override
    public int getGenreId(Connection conn, String genre) throws SQLException {
        String selectGenreSQL = "SELECT id FROM genres WHERE genre = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(selectGenreSQL)) {
            pstmt.setString(1, genre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    throw new SQLException("Genre not found: " + genre);
                }
            }
        }
    }

    @Override
    public boolean isBookExist(String bookTitle) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(CHECK_BOOK_EXISTENCE)) {
            ps.setString(1, bookTitle);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    
    @Override
    public void updateBookDetails(Connection connection, int bookId, String newTitle, String newDescription, Long newIsbn) throws SQLException {
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

    @Override
    public void updateBookRelations(Connection connection, int bookId, int[] newEntityIds, String tableName, String entityIdColumn) throws SQLException {
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
}


