import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import com.Book;
import com.User;


public class UserTest {
	User classUser;
	Book classBook;
	List<Book> books;
	
	@Before
	public void makeUser() {
		classBook = new Book("TITLE", "AUTHOR", "19892");
		classUser = new User("Maxwell", "71");
		books = new ArrayList<>();
	}
	
	@Test
	public void testGetName() {
		Assert.assertTrue(classUser.getName().equals("Maxwell"));

	}
	
	@Test
	public void testGetUserID() {
		Assert.assertTrue(classUser.getUserId().equals("71"));
	}
	
	@Test
	public void testGetBorrowedBooks() {
		Assert.assertNotNull(classUser.getBorrowedBooks());
	}
	
	@Test
	public void testBorrowAndReturnBooks() {
		//Asserting the book was borrowed
		classUser.borrowBook(classBook);
		
		//Asserting Past the Thrown exception
		IllegalStateException thrown = Assert.assertThrows(IllegalStateException.class, () -> {classUser.borrowBook(classBook);});
        Assert.assertEquals("Book is not available for borrowing", thrown.getMessage());
		
        //Asserting the book was borrowed
		classUser.returnBook(classBook);
		
		//Asserting Past the Thrown exception
		thrown = Assert.assertThrows(IllegalStateException.class, () -> {classUser.returnBook(classBook);});
	    Assert.assertEquals("Book not borrowed by this user", thrown.getMessage());
		
	}
	
}
