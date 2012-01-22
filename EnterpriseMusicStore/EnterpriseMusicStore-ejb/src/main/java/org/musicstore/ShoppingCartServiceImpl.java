package org.musicstore;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import org.musicstore.model.entities.Album;
import org.musicstore.repositories.AlbumRepository;

@Stateful
@Remote(ShoppingCartService.class)
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @EJB AlbumRepository albumRepository;
    private List<Album> albumsInCart = new ArrayList<Album>();

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
