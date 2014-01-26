package org.musicstore.persistencetests;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.musicstore.model.entities.Genre;
import org.musicstore.repositories.GenreRepositoryImpl;
import org.musicstore.tests.support.GenreBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GenreRepositoryTest {

    EntityManagerFactory emf;
    EntityManager em;
    JavaEEGloss gloss;

    private IDatabaseTester databaseTester;

    @Before
    public void setUp() throws Exception {

        // Create database
        emf = Persistence.createEntityManagerFactory("EnterpriseMusicStoreTest");
        em = emf.createEntityManager();

        // Set up database to initial state
        IDataSet inputDataSet = new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("testdata/genres_setup.xml"));
        ReplacementDataSet dataSet = new ReplacementDataSet(inputDataSet);
        dataSet.addReplacementObject("[NULL]", null);
        databaseTester = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:mem:test", "sa", "");
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();

        gloss = new JavaEEGloss();
        gloss.addEM("EnterpriseMusicStore", em);
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void should_return_all_genres() throws Exception {
        GenreRepositoryImpl genreRepository = gloss.make(GenreRepositoryImpl.class);

        GenreBuilder genreBuilder = new GenreBuilder(em);
        genreBuilder.create().withName("Rock").persist();
        genreBuilder.create().withName("Jazz").persist();
        genreBuilder.create().withName("RnB").persist();

        List<Genre> genres = genreRepository.getGenres();
        assertEquals(4, genres.size());

        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("GENRE");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("testdata/genres_expected.xml"));
        ITable expectedTable = expectedDataSet.getTable("GENRE");

        Assertion.assertEquals(expectedTable, actualTable);
    }
}
