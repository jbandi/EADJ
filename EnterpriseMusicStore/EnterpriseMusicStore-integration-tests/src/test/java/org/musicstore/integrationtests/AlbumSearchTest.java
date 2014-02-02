package org.musicstore.integrationtests;

import org.junit.Before;
import org.junit.Test;
import org.musicstore.model.entities.Album;
import org.musicstore.repositories.AlbumQuery;
import org.musicstore.repositories.AlbumRepository;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

import static junit.framework.Assert.*;

public class AlbumSearchTest {

    InitialContext jndi;
    private AlbumRepository albumRepository;

    @Before
    public void setUp() throws NamingException {

        jndi = JBossUtil.createInitialContext();

        albumRepository = (AlbumRepository) jndi.lookup("ejb:EnterpriseMusicStore/EnterpriseMusicStoreEJB//AlbumRepositoryImpl!org.musicstore.repositories.AlbumRepository");
    }

    @Test
    public void perform_album_search_by_title(){

        final AlbumQuery albumQuery = new AlbumQuery();
        albumQuery.setTitle("Hells Bells");

        List<Album> albums = albumRepository.getAlbums(albumQuery);
        assertEquals(1, albums.size());
    }

    @Test
    public void perform_album_search_by_genre(){

        final AlbumQuery albumQuery = new AlbumQuery();
        albumQuery.setGenreName("Pop");

        List<Album> albums = albumRepository.getAlbums(albumQuery);
        assertEquals(2, albums.size());
    }

}
