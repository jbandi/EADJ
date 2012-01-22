package org.musicstore.tests.jsfunit;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import junit.framework.TestCase;
import org.musicstore.Greeter;

public class EjbTest extends org.apache.cactus.ServletTestCase {

     public void test_greetings_message() throws NamingException {
        InitialContext jndi = new InitialContext();
        Greeter greeter = (Greeter) jndi.lookup("org.musicstore.Greeter");

        String test = greeter.getMessage();
        
        assertEquals("Greetings from EJB!", test);
     }

}
