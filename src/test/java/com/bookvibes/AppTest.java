// package com.bookvibes;

// import static org.junit.Assert.assertTrue;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.sql.Connection;
// import java.sql.DriverManager;

// import org.junit.Test;

// /**
//  * Unit test for simple App.
//  */
// public class AppTest 
// {
//     /**
//      * Rigorous Test :-)
//      */
//     @Test
//     void shouldEditBook (){
       
//         int bookId = 1;
//         String newTitle = "Updated Book Title";
//         String newDescription = "Updated Description";
//         Long newIsbn = 9876543210123L;
//         int[] newAuthorIds = {1};
//         int[] newGenreIds = {1};

       
//         editBook.editBook(bookId, newTitle, newDescription, newIsbn, newAuthorIds, newGenreIds);

       
//         Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
//         Statement stmt = connection.createStatement();
//         var resultSet = stmt.executeQuery("SELECT title, description, isbn FROM books WHERE id = " + bookId);

//         if (resultSet.next()) {
//             assertEquals(newTitle, resultSet.getString("title"));
//             assertEquals(newDescription, resultSet.getString("description"));
//             assertEquals(newIsbn, resultSet.getLong("isbn"));
//         } else {
//             throw new AssertionError("Book not found in the database");
//         }

//         stmt.close();
//         connection.close();
//     }
//     }
    
// }
