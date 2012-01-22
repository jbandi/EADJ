package org.musicstore.tests.unit;

import org.musicstore.ShoppingCartServiceImpl;
import javax.persistence.Persistence;
import org.musicstore.Greeter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.stvconsultants.easygloss.javaee.JavaEEGloss;
import java.util.List;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.musicstore.GreeterService;
import org.musicstore.ShoppingCartService;
import org.musicstore.model.entities.Album;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OrderPriceCalculationTest {

    @Test
    public void shopping_cart_should_sum_up_album_prices() throws Exception {
        Album album1 = new Album();
        album1.setPrice(20d);
        Album album2 = new Album();
        album2.setPrice(10d);

        ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();

        shoppingCartService.addAlbum(album1);
        shoppingCartService.addAlbum(album2);

        assertEquals(30d, shoppingCartService.getTotalAmount(), 0);


        List mockedList = mock(List.class);

        mockedList.add(album1);
        mockedList.add(album2);

        verify(mockedList, times(2)).add(any());
    }

    @Test
    public void shopping_cart_should_add_shipping_cost() throws Exception {

    }
}
