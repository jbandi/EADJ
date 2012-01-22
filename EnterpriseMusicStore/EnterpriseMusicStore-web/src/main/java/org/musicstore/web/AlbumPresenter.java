package org.musicstore.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.musicstore.model.entities.Album;
import org.musicstore.repositories.AlbumRepository;

@ManagedBean @SessionScoped
public class AlbumPresenter {

    @EJB AlbumRepository albumRepository;
    Album album;

    private Long id;

    public void loadAlbum(){
        album = albumRepository.getAlbum(id);
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

