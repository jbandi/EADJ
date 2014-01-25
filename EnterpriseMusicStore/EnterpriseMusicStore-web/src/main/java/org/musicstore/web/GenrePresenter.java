package org.musicstore.web;

import org.musicstore.model.entities.Genre;
import org.musicstore.repositories.GenreRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class GenrePresenter {

    @EJB GenreRepository genreRepository;
    List<Genre> genres;

    @PostConstruct
    public void loadGenres(){
        genres = genreRepository.getGenres();
    }

    public List<Genre> getGenres(){
        return genres;
    }
}

