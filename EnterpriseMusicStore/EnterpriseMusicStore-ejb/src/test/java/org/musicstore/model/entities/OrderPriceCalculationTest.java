package org.musicstore.model.entities;

import org.junit.Before;
import org.junit.Test;
import org.musicstore.model.entities.Album;
import org.musicstore.model.entities.MusicOrder;
import org.musicstore.model.entities.OrderItem;

import static org.junit.Assert.assertEquals;

public class OrderPriceCalculationTest {

    private Album album1;
    private Album album2;
    private OrderItem orderItem1;
    private OrderItem orderItem2;
    private MusicOrder order;

    @Before
    public void setUp(){
        album1 = new Album();
        album1.setPrice(20d);
        album2 = new Album();
        album2.setPrice(10d);

        orderItem1 = new OrderItem();
        orderItem1.setAlbum(album1);
        orderItem2 = new OrderItem();
        orderItem2.setAlbum(album2);

        order = new MusicOrder();
        order.getOrderItems().add(orderItem1);
        order.getOrderItems().add(orderItem2);
    }


    @Test
    public void order_should_sum_up_album_prices_for_total_amount() throws Exception {

        assertEquals(new Double(30), order.getTotalAmount());
    }

    @Test
    public void order_final_amount_should_equal_total_amount_if_not_set() throws Exception {

        assertEquals(order.getTotalAmount(), order.getFinalAmount());
    }

    @Test
    public void order_final_amount_can_be_set() throws Exception {

        order.setFinalAmount(15d);
        assertEquals(new Double(15d), order.getFinalAmount());
    }
}
