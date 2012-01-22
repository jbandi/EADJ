package books.test.functional;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class BookSearchTest extends SeleneseTestCase {
	
    private DefaultSelenium selenium;
    
	public void setUp() throws Exception {
		 this.selenium  = new DefaultSelenium("localhost", 8099, "*googlechrome", "http://localhost:8080/");
		 this.selenium.setSpeed("1000");
	     this.selenium.start();
	}	
	
	public void testSearch() throws Exception {
		selenium.open("/books/catalog.faces");
		selenium.type("search_parameter:title", "java");
		selenium.click("search_parameter:search");
		Thread.sleep(100);
		verifyEquals("10", selenium.getXpathCount("//table/tbody/tr"));
	}
}