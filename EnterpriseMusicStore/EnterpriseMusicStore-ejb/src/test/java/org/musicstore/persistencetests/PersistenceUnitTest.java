package org.musicstore.persistencetests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.ejb.Ejb3Configuration;
import org.junit.Test;
import org.musicstore.model.entities.Album;

public class PersistenceUnitTest {

    @Test
    public void instantiating_entity_manager_factory_should_succeed() {

        // This throws an exception if schema and mapping-meta-data are different
        // This throws an exception if any named query is not correct
        Persistence.createEntityManagerFactory("EnterpriseMusicStoreIntegrationTest");
    }
}
