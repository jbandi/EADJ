package org.musicstore.web;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.musicstore.Greeter;
import org.musicstore.model.entities.Genre;
import org.musicstore.repositories.GenreRepository;

@ManagedBean @RequestScoped
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

