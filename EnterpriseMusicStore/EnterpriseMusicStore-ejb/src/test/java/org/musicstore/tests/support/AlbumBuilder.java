package org.musicstore.tests.support;

import javax.persistence.EntityManager;
import org.musicstore.model.entities.Album;
import org.musicstore.model.entities.Genre;

public class AlbumBuilder {
    private final EntityManager em;
    private Album album;

    public AlbumBuilder(EntityManager em) {
        this.em = em;
    }

    public AlbumBuilder create(){
        this.album = new Album();
        return this;
    }

    public AlbumBuilder withName(String name){
        this.album.setName(name);
        return this;
    }

    public AlbumBuilder withGenre(Genre genre){
        this.album.setGenre(genre);
        return this;
    }

    public Album persist(){
        em.getTransaction().begin();
        em.persist(album);
        em.flush();
        em.getTransaction().commit();

        return album;
    }

}
