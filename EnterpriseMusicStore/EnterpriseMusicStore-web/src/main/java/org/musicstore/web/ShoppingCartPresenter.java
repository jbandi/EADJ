package org.musicstore.web;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.musicstore.ShoppingCartService;
import org.musicstore.model.entities.Album;

@ManagedBean @SessionScoped
public class ShoppingCartPresenter {

    ShoppingCartService shoppingCartService;
    private List<Album> albumsInCart = new ArrayList<Album>();

    @PostConstruct
    public void init() throws NamingException {
        InitialContext ctx = new InitialContext();

        if (shoppingCartService == null)
            shoppingCartService = (ShoppingCartService) ctx.lookup("org.musicstore.ShoppingCartService");
    }

    public String addAlbumToCart(Album album) {

        shoppingCartService.addAlbum(album);
        return "shoppingCart.xhtml?faces-redirect=true";
    }

    public void loadAlbumsInCart() {
        albumsInCart = shoppingCartService.getAlbumsInCart();
    }

    public List<Album> getAlbumsInCart() {
        albumsInCart = shoppingCartService.getAlbumsInCart();
        return albumsInCart;
    }

    public int getItemCount() {
        return albumsInCart.size();
    }

    public double getTotalAmount() {
        return shoppingCartService.getTotalAmount();
    }
}
