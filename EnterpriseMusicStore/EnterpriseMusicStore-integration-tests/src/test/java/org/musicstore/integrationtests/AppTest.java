package org.musicstore.integrationtests;

import org.musicstore.Greeter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    InitialContext jndi;

    @Before
    public void setUp() throws NamingException {
        jndi = new InitialContext();
    }

    @Test
    public void hello() throws NamingException {
        Greeter greeter = (Greeter) jndi.lookup("java:global/EnterpriseMusicStore-ear/EnterpriseMusicStore-ejb/GreeterService");

        assertEquals("Greetings from EJB!", greeter.getMessage());
    }
}
