package org.musicstore.tests.integration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import org.musicstore.model.entities.Album;

public class PersistenceUnitTest {

    @Test
    public void initialize() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EnterpriseMusicStoreTest");
        EntityManager em = emf.createEntityManager();
        Album album = em.find(Album.class, 1L);
        em.close();
        emf.close();
    }

}
