package org.books.test.integration;

import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.books.service.OrderSenderLocal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderSenderTest {

	private Context context;

	@Before
	public void setUp() throws Exception {
		long start = new Date().getTime();
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.openejb.client.LocalInitialContextFactory");
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
	public void tearDown() throws Exception {
		context.close();
	}

	@Test
	public void should_be_able_to_send_messages() throws Exception {
		OrderSenderLocal orderSender = (OrderSenderLocal) context.lookup("OrderSenderLocal");
		orderSender.sendMessage("Hello!");
		
		Thread.sleep(1000); // Wait for the timer in the MDB to fire
	}
}
