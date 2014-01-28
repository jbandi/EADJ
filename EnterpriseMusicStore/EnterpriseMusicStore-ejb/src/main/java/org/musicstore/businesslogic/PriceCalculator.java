package org.musicstore.businesslogic;

import org.musicstore.model.entities.Album;
import org.musicstore.model.entities.MusicOrder;
import org.musicstore.repositories.MusicOrderRepository;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class PriceCalculator implements Serializable {

    public static final double DISCOUNT_FACTOR = 0.9;

    MusicOrderRepository musicOrderRepository;

    @Inject
    public PriceCalculator(MusicOrderRepository musicOrderRepository) {
        this.musicOrderRepository = musicOrderRepository;
    }

    public double calculatePrice(List<Album> albums) {
        double sum = 0;
        for(Album album : albums)
            sum += album.getPrice();

        return sum;
    }

    public double calculatePrice(MusicOrder currentOrder) {

        List<MusicOrder> previousOrders = musicOrderRepository.getOrdersByEmail(currentOrder.getEmail());
        if (previousOrders.size() > 0)
            return currentOrder.getTotalAmount() * DISCOUNT_FACTOR;
        else
            return currentOrder.getTotalAmount();
    }
}
