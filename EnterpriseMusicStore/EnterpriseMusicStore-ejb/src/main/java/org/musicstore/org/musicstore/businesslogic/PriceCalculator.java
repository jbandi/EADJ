package org.musicstore.org.musicstore.businesslogic;

import org.musicstore.model.entities.Album;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

public class PriceCalculator implements Serializable {

    public double calculatePrice(List<Album> albums) {
        double sum = 0;
        for(Album album : albums)
            sum += album.getPrice();

        return sum;
    }

}
