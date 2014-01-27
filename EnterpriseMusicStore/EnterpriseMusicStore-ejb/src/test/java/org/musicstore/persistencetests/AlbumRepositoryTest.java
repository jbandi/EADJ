package org.musicstore.persistencetests;

import org.musicstore.tests.support.GenreBuilder;
import org.musicstore.tests.support.AlbumBuilder;
import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.musicstore.model.entities.Album;
import org.musicstore.model.entities.Genre;
import org.musicstore.repositories.AlbumQuery;
import org.musicstore.repositories.AlbumRepositoryImpl;

public class AlbumRepositoryTest {

    EntityManagerFactory emf;
    EntityManager em;
    JavaEEGloss gloss;

    @Before
    public void setUp() {
        gloss = new JavaEEGloss();
        emf = Persistence.createEntityManagerFactory("EnterpriseMusicStoreTest");
        em = emf.createEntityManager();
        gloss.addEM("EnterpriseMusicStore", em);
    }
    
    @After
    public void tearDown(){
        em.close();
        emf.close();
    }

    @Test
    public void should_return_all_albums() {
        AlbumRepositoryImpl albumRepositoryImpl = gloss.make(AlbumRepositoryImpl.class);

        GenreBuilder genreBuilder = new GenreBuilder(em);
        Genre rock = genreBuilder.create().withName("Rock").persist();
        Genre jazz = genreBuilder.create().withName("Jazz").persist();
        Genre rnb = genreBuilder.create().withName("RnB").persist();

        AlbumBuilder albumBuilder = new AlbumBuilder(em);
        albumBuilder.create().withName("Hells Bells").withGenre(rock).persist();
        albumBuilder.create().withName("Chill Out Jazz").withGenre(jazz).persist();
        albumBuilder.create().withName("Black 2010").withGenre(rnb).persist();

        List<Album> albums = albumRepositoryImpl.getAlbums(new AlbumQuery());
        assertEquals(3, albums.size());
    }

    @Test
    public void should_return_album_with_id() {
        AlbumRepositoryImpl albumRepositoryImpl = gloss.make(AlbumRepositoryImpl.class);

        AlbumBuilder albumBuilder = new AlbumBuilder(em);
        Album hellsBells = albumBuilder.create().withName("Hells Bells").persist();
        albumBuilder.create().withName("Chill Out Jazz").persist();

        Album album = albumRepositoryImpl.getAlbum(hellsBells.getId());
        assertEquals("Hells Bells", album.getName());
    }

    @Test
    public void should_return_albums_with_matching_name() {
        AlbumRepositoryImpl albumRepositoryImpl = gloss.make(AlbumRepositoryImpl.class);

        GenreBuilder genreBuilder = new GenreBuilder(em);
        Genre rock = genreBuilder.create().withName("Rock").persist();
        Genre jazz = genreBuilder.create().withName("Jazz").persist();
        Genre rnb = genreBuilder.create().withName("RnB").persist();

        AlbumBuilder albumBuilder = new AlbumBuilder(em);
        albumBuilder.create().withName("Hells Bells").withGenre(rock).persist();
        albumBuilder.create().withName("Chill Out Jazz").withGenre(jazz).persist();
        albumBuilder.create().withName("My Jazzy").withGenre(jazz).persist();
        albumBuilder.create().withName("Black 2010").withGenre(rnb).persist();

        final AlbumQuery albumQuery = new AlbumQuery();
        albumQuery.setTitle("Hells Bells");

        List<Album> albums = albumRepositoryImpl.getAlbums(albumQuery);
        assertEquals(1, albums.size());
    }

    @Test
    public void should_return_albums_with_matching_genre() {
        AlbumRepositoryImpl albumRepositoryImpl = gloss.make(AlbumRepositoryImpl.class);

        GenreBuilder genreBuilder = new GenreBuilder(em);
        Genre rock = genreBuilder.create().withName("Rock").persist();
        Genre jazz = genreBuilder.create().withName("Jazz").persist();
        Genre rnb = genreBuilder.create().withName("RnB").persist();

        AlbumBuilder albumBuilder = new AlbumBuilder(em);
        albumBuilder.create().withName("Hells Bells").withGenre(rock).persist();
        albumBuilder.create().withName("Chill Out Jazz").withGenre(jazz).persist();
        albumBuilder.create().withName("My Jazzy").withGenre(jazz).persist();
        albumBuilder.create().withName("Black 2010").withGenre(rnb).persist();

        final AlbumQuery albumQuery = new AlbumQuery();
        albumQuery.setGenreName("Jazz");

        List<Album> albums = albumRepositoryImpl.getAlbums(albumQuery);
        assertEquals(2, albums.size());
    }
}
