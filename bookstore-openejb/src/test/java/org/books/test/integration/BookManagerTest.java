package org.books.test.integration;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.books.dao.BookDao;
import org.books.domain.Book;
import org.books.service.BookManagerLocal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookManagerTest {

	private Context context;

	@Before
	public void setUp() throws Exception{
		long start = new Date().getTime();
        Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        p.put("movieDatabase", "new://Resource?type=DataSource");
        p.put("movieDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("movieDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");

        p.put("movieDatabaseUnmanaged", "new://Resource?type=DataSource");
        p.put("movieDatabaseUnmanaged.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("movieDatabaseUnmanaged.JdbcUrl", "jdbc:hsqldb:mem:moviedb");
        p.put("movieDatabaseUnmanaged.JtaManaged", "false");
        
        p.put("openejb.embedded.initialcontext.close", "destroy");
        
        context = new InitialContext(p);
		long end = new Date().getTime();
        System.out.println("Container started in: " + (end-start));
	}
	
	@After
	public void tearDown() throws Exception{
        context.close();
	}
	
	   @Test
	    public void test() throws Exception {

	        BookDao bookDao = (BookDao) context.lookup("BookDaoLocal");

	        bookDao.addBook(new Book("Martin Fowler", "Patterns of Enterprise Application Architecture", 2002));
	        bookDao.addBook(new Book("Eric Evans", "Domain Driven Design", 2003));
	        bookDao.addBook(new Book("Gerard Meszaros", "xUnit Test Patterns", 2007));

	        List<Book> list = bookDao.getBooks();
	        assertEquals("List.size()", 3, list.size());
	        
	        BookManagerLocal bookManager = (BookManagerLocal) context.lookup("BookManagerLocal");
	        assertNotNull(bookManager);

	        assertEquals(3, bookManager.getBooks().size());
	        
	        assertEquals("first", bookManager.getMessage());
	        bookManager.setMessage("second");
	        assertEquals("second", bookManager.getMessage());

	        context.close();
	    }
}
