

import static org.junit.Assert.*;
import org.junit.Assert;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import com.Book;

public class BookTest {
	Book classBook;
		
	@Before
	public void makeBook() {
		classBook = new Book("TITLE", "AUTHOR", "19892");
	}
	 
	@Test
	public void testGetTitle() {
		Assert.assertTrue(classBook.getTitle().equals("TITLE"));
	}
	
	@Test
	public void testGetAuthor() {
		Assert.assertTrue(classBook.getAuthor().equals("AUTHOR"));
	}
	
	@Test
	public void testGetIsbn() {
		Assert.assertTrue(classBook.getIsbn().equals("19892"));
	}
	
	@Test
	public void testIsAvailable() {
		Assert.assertNotNull(classBook.isAvailable());
	}
	
	@Test
	public void testBorrowAndReturnBook() {
		//Asserting the book was borrowed
		classBook.borrowBook();
		Assert.assertFalse(classBook.isAvailable());
		
		//Asserting the Book was returned
		classBook.returnBook();
		Assert.assertTrue(classBook.isAvailable());
	}
}
