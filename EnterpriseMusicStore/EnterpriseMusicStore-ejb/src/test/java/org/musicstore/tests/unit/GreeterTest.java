package org.musicstore.tests.unit;

import javax.persistence.Persistence;
import org.musicstore.Greeter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import org.junit.Test;
import org.musicstore.GreeterService;
import static org.junit.Assert.*;

public class GreeterTest {

    @Test
    public void greeter_should_return_message() {
        JavaEEGloss gloss = new JavaEEGloss();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EnterpriseMusicStoreTest");
        EntityManager em = emf.createEntityManager();

        gloss.addEM("EnterpriseMusicStore", em);
        Greeter greeter = gloss.make(GreeterService.class);

        assertEquals("Greetings from EJB!", greeter.getMessage());
    }
}
