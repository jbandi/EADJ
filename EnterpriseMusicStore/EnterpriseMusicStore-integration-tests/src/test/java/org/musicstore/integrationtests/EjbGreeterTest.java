package org.musicstore.integrationtests;

import org.junit.Before;
import org.junit.Test;
import org.musicstore.Greeter;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import static org.junit.Assert.assertEquals;

public class EjbGreeterTest {

    InitialContext jndi;

    @Before
    public void setUp() throws NamingException {

        jndi = JBossUtil.createInitialContext();
    }

    @Test
    public void hello() throws NamingException {
        Greeter greeter = (Greeter) jndi.lookup("ejb:EnterpriseMusicStore-ear/EnterpriseMusicStoreEJB//GreeterService!org.musicstore.Greeter");

        assertEquals("Greetings from EJB!", greeter.getMessage());
    }


}
