package org.musicstore.web;

import org.musicstore.ShoppingCartService;
import org.musicstore.model.entities.Album;
import org.musicstore.repositories.AlbumRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class AlbumPresenter implements Serializable {

    @EJB AlbumRepository albumRepository;
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

