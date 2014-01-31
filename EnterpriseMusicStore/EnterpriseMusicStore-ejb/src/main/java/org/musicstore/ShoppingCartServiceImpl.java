package org.musicstore;

import org.musicstore.model.entities.Album;
import org.musicstore.businesslogic.PriceCalculator;
import org.musicstore.repositories.AlbumRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
@ConversationScoped
@Local(ShoppingCartService.class)
public class ShoppingCartServiceImpl implements ShoppingCartService, Serializable {

    @Inject
    private Conversation conversation;

    @Inject
    private AlbumRepository albumRepository;

    private PriceCalculator priceCalculator;

    @Inject
    public void setPriceCalculator(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    private List<Album> albumsInCart = new ArrayList<>();

    @PostConstruct
    public void init() {
        if (conversation.isTransient())
            conversation.begin();
    }

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

        double sum = priceCalculator.calculatePrice(albumsInCart);
        return sum;
    }
}
