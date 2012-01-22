package org.musicstore.tests.support;

import javax.persistence.EntityManager;
import org.musicstore.model.entities.Genre;

public class GenreBuilder {
    private final EntityManager em;
    private String name;
    private Genre genre;

    public GenreBuilder(EntityManager em) {
        this.em = em;
    }

    public GenreBuilder create(){
        this.genre = new Genre();
        return this;
    }

    public GenreBuilder withName(String name){
        this.name = name;
        return this;
    }

    public Genre persist(){
        genre.setName(name);
        em.getTransaction().begin();
        em.persist(genre);
        em.flush();
        em.getTransaction().commit();

        return genre;
    }

}
