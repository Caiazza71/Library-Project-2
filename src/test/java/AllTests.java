import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import IntegrationTests.IntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({
	BookTest.class,
	UserTest.class,
	LibraryTest.class,
	IntegrationTest.class
	
})

public class AllTests {

}
