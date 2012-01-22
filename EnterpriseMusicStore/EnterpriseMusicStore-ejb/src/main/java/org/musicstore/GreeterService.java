package org.musicstore;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.musicstore.model.entities.Album;

@Stateless
@Remote(Greeter.class)
public class GreeterService implements Greeter {

    @PersistenceContext(unitName="EnterpriseMusicStore")
    private EntityManager em;

    public String getMessage() {
        Album album = em.find(Album.class, 1L);

        return "Greetings from EJB!";
    }


}
