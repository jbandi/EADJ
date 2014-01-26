package org.musicstore.web;

import org.musicstore.ShoppingCartService;
import org.musicstore.model.entities.Album;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class ShoppingCartPresenter implements Serializable {

    @Inject ShoppingCartService shoppingCartService;
    private List<Album> albumsInCart = new ArrayList<Album>();

    public void loadAlbumsInCart() {
        albumsInCart = shoppingCartService.getAlbumsInCart();
    }

    public List<Album> getAlbumsInCart() {
        albumsInCart = shoppingCartService.getAlbumsInCart();
        return albumsInCart;
    }

    public double getTotalAmount() {
        return shoppingCartService.getTotalAmount();
    }
}
