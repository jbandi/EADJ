package org.musicstore.tests.integration;

import org.musicstore.tests.support.GenreBuilder;
import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import org.eclipse.persistence.sessions.server.ServerSession;
//import org.eclipse.persistence.tools.schemaframework.SchemaManager;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.musicstore.model.entities.Genre;
import org.musicstore.repositories.GenreRepositoryImpl;

public class GenreRepositoryTest {

    EntityManagerFactory emf;
    EntityManager em;
    JavaEEGloss gloss;

    @Before
    public void setUp() {
        gloss = new JavaEEGloss();

        emf = Persistence.createEntityManagerFactory("EnterpriseMusicStoreTest");
        em = emf.createEntityManager();

//        ServerSession session = em.unwrap(ServerSession.class);
//        SchemaManager schemaManager = new SchemaManager(session);
//        schemaManager.replaceDefaultTables(true, true);

        gloss.addEM("EnterpriseMusicStore", em);
    }

    @After
    public void tearDown(){
        em.close();
        emf.close();
    }

    @Test
    public void should_return_all_genres() {
        GenreRepositoryImpl genreRepository = gloss.make(GenreRepositoryImpl.class);

        GenreBuilder genreBuilder = new GenreBuilder(em);
        genreBuilder.create().withName("Rock").persist();
        genreBuilder.create().withName("Jazz").persist();
        genreBuilder.create().withName("RnB").persist();

        List<Genre> genres = genreRepository.getGenres();
        assertEquals(3, genres.size());
    }
}
