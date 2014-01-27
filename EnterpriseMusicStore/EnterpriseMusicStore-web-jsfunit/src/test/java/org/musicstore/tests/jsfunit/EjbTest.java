package org.musicstore.tests.jsfunit;

import org.musicstore.Greeter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class EjbTest extends org.apache.cactus.ServletTestCase {

    public void test_greetings_message() throws NamingException {

        final Hashtable jndiProperties = new Hashtable();

        InitialContext jndi = new InitialContext(jndiProperties);
        Greeter greeter = (Greeter) jndi.lookup("java:app/EnterpriseMusicStoreEJB/GreeterService");

        String test = greeter.getMessage();

        assertEquals("Greetings from EJB!", test);
    }
}
