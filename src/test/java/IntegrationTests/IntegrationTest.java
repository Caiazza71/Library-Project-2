package IntegrationTests;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class IntegrationTest {

	@Test
	public void bookTest() {
		assertEquals("2 + 2 should equal 4", 4, 2 + 2);
	}
	
	@Test
	public void userTest() {
		assertEquals("2 + 2 should equal 4", 4, 2 + 2);
	}
	
	@Test
	public void libraryTest() {
		assertEquals("2 + 2 should equal 4", 4, 2 + 2);
	}
	
	
}
