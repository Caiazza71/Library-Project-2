package IntegrationTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import com.Book;
import com.Library;
import com.User;
import java.util.List;

public class IntegrationTest {
	Library classlibrary;
	Book book1;
	Book book2;
	Book book3;
	User user1;
	User user2;
	
	@Before
	public void setup() {
		classlibrary = new Library();
		
		// Fake books
		book1 = new Book("Book 1", "Author A", "123");
		book2 = new Book("Book 2", "Author B", "456");
		book3 = new Book("Book 3", "Author C", "789");
		
		// Fake users
		user1 = new User("John", "U001");
		user2 = new User("Maxwell", "U002");
	}
	
	@Test
	public void testCase1() {
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
		classlibrary.addBook(book3);
		
		classlibrary.borrowBook(user1.getUserId(), book1.getIsbn());
		
		//Checking all books in list are not book1
		for (Book book : classlibrary.getAvailableBooks()) {
			Assert.assertNotSame(book1, book);
		}
	}
	
	@Test
	public void testCase2() {
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
		classlibrary.addBook(book3);
		
		IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.borrowBook("newUser", book1.getIsbn());});
	    Assert.assertEquals("User or Book not found", thrown.getMessage());
	}
	
	@Test
	public void testCase3() {
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
		classlibrary.addBook(book3);
		
		IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.borrowBook(user1.getUserId(),"Book5");});
	    Assert.assertEquals("User or Book not found", thrown.getMessage());
	}
	
	@Test
	public void testCase4() {
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
		classlibrary.addBook(book3);
		
		classlibrary.borrowBook(user1.getUserId(), book1.getIsbn());
		
		IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.borrowBook(user1.getUserId(), book1.getIsbn());});
	    Assert.assertEquals("Book is not available for borrowing", thrown.getMessage());
	}
	
	@Test
	public void testCase5() {
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
		classlibrary.addBook(book3);
		
		classlibrary.borrowBook(user1.getUserId(), book1.getIsbn());
		classlibrary.borrowBook(user2.getUserId(), book2.getIsbn());

		Assert.assertEquals(2, classlibrary.getTotalBorrowedBooks());
		
		classlibrary.returnBook(user1.getUserId(), book1.getIsbn());
		
		Assert.assertEquals(1, classlibrary.getTotalBorrowedBooks());
	}
	
	@Test
	public void testCase6() {
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
		classlibrary.addBook(book3);
		
		Assert.assertEquals(0, classlibrary.getTotalBorrowedBooks());

		IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.returnBook("user3", book1.getIsbn());});
	    Assert.assertEquals("User or Book not found", thrown.getMessage());
	    
	    Assert.assertEquals(0, classlibrary.getTotalBorrowedBooks());
	}
	
	@Test
	public void testCase7() {
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
		classlibrary.addBook(book3);
		
		Assert.assertEquals(0, classlibrary.getTotalBorrowedBooks());

		IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.returnBook(user1.getUserId(),"book5");});
	    Assert.assertEquals("User or Book not found", thrown.getMessage());
	    
	    Assert.assertEquals(0, classlibrary.getTotalBorrowedBooks());
	}
	
	@Test
	public void testCase8() {
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
		classlibrary.addBook(book3);
		
		classlibrary.borrowBook(user1.getUserId(), book1.getIsbn());
		Assert.assertEquals(1, classlibrary.getTotalBorrowedBooks());
		
		classlibrary.returnBook(user1.getUserId(), book1.getIsbn());
		Assert.assertEquals(0, classlibrary.getTotalBorrowedBooks());
		
		IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.returnBook(user1.getUserId(),book1.getIsbn());});
	    Assert.assertEquals("Book not borrowed by this user", thrown.getMessage());
	    
	    Assert.assertEquals(0, classlibrary.getTotalBorrowedBooks());
	}
	
	@Test
	public void testCase9() {
		classlibrary.registerUser(user1);
		classlibrary.registerUser(user2);
		classlibrary.addBook(book1);
		classlibrary.addBook(book2);
		classlibrary.addBook(book3);
		
		IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classlibrary.returnBook(user1.getUserId(),book1.getIsbn());});
	    Assert.assertEquals("Book not borrowed by this user", thrown.getMessage());
	    
	    Assert.assertEquals(0, classlibrary.getTotalBorrowedBooks());
	}
}
