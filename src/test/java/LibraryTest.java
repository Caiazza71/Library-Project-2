import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

import com.Book;
import com.Library;
import com.User;
import java.util.List;


public class LibraryTest {
	Library classlibrary;
	Book book1;
	Book book2;
	Book book3;
	User user1;
	User user2;

	@Before
	public void createLibrary() {
		classlibrary = new Library();
		
		// Fake books
		book1 = new Book("Book 1", "Author A", "123");
		book2 = new Book("Book 2", "Author B", "456");
		book3 = new Book("Book 3", "Author C", "789");
		
		// Fake users
		user1 = new User("John", "U001");
		user2 = new User("Maxwell", "U002");
		
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
	}
	
	@Test
	public void testAddBook() {
		assertEquals(2, classlibrary.getTotalNumberOfBooks());
	}
	
	@Test
	public void testRegisterUser(){
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		
		// No way to assert that users can be added ?
	}
	
	@Test
	public void testAvailableBooks() {
		classlibrary.displayAvailableBooks();
		Assert.assertNotNull(classlibrary.getAvailableBooks());
	}
	
	@Test
	public void testBorrowBook() {
		//Borrow a Book
		Assert.assertNotNull(book1);
		classlibrary.borrowBook(user1.getUserId(), book1.getIsbn());
        Assert.assertFalse(book1.isAvailable());
        
        // First input is NULL
        IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.borrowBook(null,"0001");});
        Assert.assertEquals("User or Book not found", thrown.getMessage());
        
        // Second input is NULL
        thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.borrowBook("0001", null);});
        Assert.assertEquals("User or Book not found", thrown.getMessage());
    }
	
	@Test
	public void testReturnBook() {
		//return a Book
		Assert.assertNotNull(book1);
		classlibrary.borrowBook(user1.getUserId(), book1.getIsbn());
		classlibrary.returnBook(user1.getUserId(), book1.getIsbn());
        Assert.assertTrue(book1.isAvailable());
        
        // First input is NULL
        IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.returnBook(null,"0001");});
        Assert.assertEquals("User or Book not found", thrown.getMessage());
        
        // Second input is NULL
        thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.returnBook("0001", null);});
        Assert.assertEquals("User or Book not found", thrown.getMessage());
	}
	
	@Test
	public void testGetTotalBorrowedBooks() {
		Assert.assertEquals(0, classlibrary.getTotalBorrowedBooks());
		
		classlibrary.borrowBook(user1.getUserId(), book1.getIsbn());
		Assert.assertEquals(1, classlibrary.getTotalBorrowedBooks());
	}

	@Test
	public void testGetAvailableBooks() {
		// Asserting Empty Library condition
		Library newLib = new Library();
		Assert.assertEquals(0.0, newLib.getAverageBooksPerUser(),0.0);
		
	    // Normal Condition
		Assert.assertNotNull(classlibrary.getAverageBooksPerUser());
		
	}
}
