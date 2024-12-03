

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.Book;
import com.Library;
import com.User;
import java.util.List;
public class LibraryTest {
	private Library library;
	private Book book1;
	private Book book2;
	private Book book3;
	private Book book4;
	private User user1;
	private User user2;

	@Before
	public void setUp() {
		library = new Library();
		
		// Fake books
		book1 = new Book("Java Programming", "Author A", "ISBN123");
		book2 = new Book("Data Structures", "Author B", "ISBN456");
		book3 = new Book("Software Testing", "Author C", "ISBN789");
		book4 = new Book("Auto Testing", "Author D", "ISBN321");
		
		// Fake users
		user1 = new User("U001", "John");
		user2 = new User("U002", "Max");
		
		library.addBook(book1);
		library.addBook(book2);
		library.addBook(book3);
		library.addBook(book4);
		library.registerUser(user1);
		library.registerUser(user2);
	}
	
	@Test
	public void testAddBook() {
		Book book5 = new Book("Why Mercedes Sucks", "Author E", "ISBN654");
		library.addBook(book5);
		
		// Assert Equals
		assertEquals(5, library.getTotalNumberOfBooks());
	}
	
	
	@Test
	public void testBorrowBook() {
        // Borrow a book
        library.borrowBook("U001", "ISBN123");

        // Check that the book is in the user's borrowed list
        assertTrue( "The borrowed book should be in the user's list.", user1.getBorrowedBooks().contains(book1));

        // Indirectly verify book availability by checking the available books list
        List<Book> availableBooks = library.getAvailableBooks();
        assertFalse("The borrowed book should not be in the available books list.", availableBooks.contains(book1));
    }
	
	@Test
	public void testReturnBook() {
	    // Borrow a book first
	    library.borrowBook("U001", "ISBN123");

	    // Assert that the book is now borrowed by the user
	    assertTrue("The user should have borrowed the book.", user1.getBorrowedBooks().contains(book1));

	    // Return the book
	    library.returnBook("U001", "ISBN123");

	    // Assert that the book is now available again
	    List<Book> availableBooks = library.getAvailableBooks();
	    assertTrue("The returned book should be in the available books list.", availableBooks.contains(book1));

	    // Assert that the user's borrowed books list is empty
	    assertTrue("The user should not have borrowed any books.", user1.getBorrowedBooks().isEmpty());
	}

	
	
	@Test
	public void testGetAvailableBooks() {
	    // Borrow a book
	    library.borrowBook("U001", "123");

	    //  available books
	    List<Book> availableBooks = library.getAvailableBooks();

	    // Verify the number of available books
	    assertEquals("Only one book should be available after borrowing one.", 1, availableBooks.size());

	    // Verify the correct book is available
	    assertEquals("456", availableBooks.get(0).getIsbn(), "The available book should have ISBN '456'.");
	}
	
	@Test
	public void testInvalidBorrow() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            library.borrowBook("U999", "999");
        });
        
        // Assert That
        assertTrue(exception.getMessage().contains("User or Book not found"));
	}
	
	@Test
	public void testNullUser() {
		User nullUser = null;
		
		/// Assert Null
		assertNull(nullUser);
	}
	
}
