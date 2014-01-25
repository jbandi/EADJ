package org.musicstore.web;

import org.musicstore.ShoppingCartService;
import org.musicstore.model.entities.Album;
import org.musicstore.repositories.AlbumRepository;
import org.musicstore.repositories.AlbumRepositoryImpl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class AlbumPresenter implements Serializable {

    @Inject AlbumRepository albumRepository;
    @Inject ShoppingCartService shoppingCartService;

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

    public String addAlbumToCart(Long albumId) {
        Album albumToAdd = albumRepository.getAlbum(albumId);
        shoppingCartService.addAlbum(albumToAdd);
        return "shoppingCart.xhtml?faces-redirect=true";
    }
}

