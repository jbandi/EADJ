package org.books.test.integration;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.Assert;

import org.apache.openejb.api.LocalClient;
import org.books.dao.BookDao;
import org.books.domain.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@LocalClient
public class BookDaoTest {

	private Context context;
	
	@EJB
	private BookDao bookDao;

	@Before
	public void setUp() throws Exception {
		long start = new Date().getTime();
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.LocalInitialContextFactory");
		p.put("movieDatabase", "new://Resource?type=DataSource");
		p.put("movieDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
		p.put("movieDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");

//		p.put("movieDatabaseUnmanaged", "new://Resource?type=DataSource");
//		p.put("movieDatabaseUnmanaged.JdbcDriver", "org.hsqldb.jdbcDriver");
//		p.put("movieDatabaseUnmanaged.JdbcUrl", "jdbc:hsqldb:mem:moviedb");
//		p.put("movieDatabaseUnmanaged.JtaManaged", "false");

		p.put("openejb.embedded.initialcontext.close", "destroy");
		p.put("openejb.tempclassloader.skip", "annotations");

		context = new InitialContext(p);
		context.bind("inject", this);
		long end = new Date().getTime();
        System.out.println("Container started in: " + (end-start) + "ms");
	}

	@After
	public void tearDown() throws Exception {
		context.close();
	}

	@Test
	public void should_add_and_delete_books() throws Exception {

		bookDao.addBook(new Book("Martin Fowler", "Patterns of Enterprise Application Architecture", 2002));
		bookDao.addBook(new Book("Eric Evans", "Domain Drven Design", 2003));
		bookDao.addBook(new Book("Gerard Meszaros", "xUnit Test Patterns", 2007));

		List<Book> list = bookDao.getBooks();
		Assert.assertEquals(3, list.size());

		for (Book book : list) {
			bookDao.deleteBook(book);
		}

		Assert.assertEquals(0, bookDao.getBooks().size());
	}

    @Test
	public void should_not_return_books_when_database_is_empty()  throws Exception {
    		List<Book> list = bookDao.getBooks();
    		Assert.assertEquals(0, list.size());
        }
}
