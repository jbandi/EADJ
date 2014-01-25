package org.musicstore;

import org.musicstore.model.entities.Album;
import org.musicstore.repositories.AlbumRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful @SessionScoped
@Local(ShoppingCartService.class)
public class ShoppingCartServiceImpl implements ShoppingCartService, Serializable {

    @EJB AlbumRepository albumRepository;
    private List<Album> albumsInCart = new ArrayList<>();

    @Override
    public void addAlbum(Album album) {
        albumsInCart.add(album);
    }

    @Override
    public List<Album> getAlbumsInCart() {
        return albumsInCart;
    }

    @Override
    public double getTotalAmount() {
        double sum = 0;
        for(Album album : albumsInCart)
            sum += album.getPrice();

        return sum;
    }
    
    
}
