package org.musicstore;

import java.util.List;
import org.musicstore.model.entities.Album;

public interface ShoppingCartService {
    public void addAlbum(Album album);
    public List<Album> getAlbumsInCart();
    public double getTotalAmount();
}
