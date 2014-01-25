package org.musicstore.tests.jsfunit;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import junit.framework.TestCase;
import org.musicstore.Greeter;

import java.util.Hashtable;

public class EjbTest extends org.apache.cactus.ServletTestCase {

     public void test_greetings_message() throws NamingException {

         final Hashtable jndiProperties = new Hashtable();
         jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        InitialContext jndi = new InitialContext(jndiProperties);
        //  Greeter greeter = (Greeter) jndi.lookup("ejb:EnterpriseMusicStore/EnterpriseMusicStoreEJB//GreeterService!org.musicstore.Greeter");
        Greeter greeter = (Greeter) jndi.lookup("java:app/EnterpriseMusicStoreEJB/GreeterService");

        String test = greeter.getMessage();
        
        assertEquals("Greetings from EJB!", test);
     }
}
