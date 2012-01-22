package org.musicstore;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import org.musicstore.model.entities.Album;
import org.musicstore.model.entities.Genre;

@Singleton
@Startup
public class SeedDataCreator {

    @PersistenceContext(unitName = "EnterpriseMusicStore")
    private EntityManager em;

    @PostConstruct
    public void importData() {

        Genre rock = createGenre("Rock");
        Genre jazz = createGenre("Jazz");
        Genre pop = createGenre("Pop");

        createAlbum("Hells Bells", rock, 10, "hells_bells.jpg");
        createAlbum("Torch Songs", pop, 12d, "torch_songs.jpg");
        createAlbum("Beautiful Garbage", pop, 9d, "beautiful_garbage.jpg");
    }

    public Genre createGenre(String genreName) {
        Genre genre = new Genre();
        genre.setName(genreName);
        persist(genre);
        return genre;
    }

    private Album createAlbum(String name, Genre genre, double price, String artUrl) {
        Album album = new Album();
        album.setName(name);
        album.setGenre(genre);
        album.setPrice(price);
        album.setAlbumArtUrl(artUrl);
        persist(album);
        return album;
    }

    private void persist(Object entity) {
        em.persist(entity);
    }
}


