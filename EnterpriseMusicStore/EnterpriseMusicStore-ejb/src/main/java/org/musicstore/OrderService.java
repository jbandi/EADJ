package org.musicstore;

import org.musicstore.model.entities.MusicOrder;

import java.util.List;

public interface OrderService {

    public List<MusicOrder> getAllOrders();

    public MusicOrder getCurrentOrder();
    public MusicOrder createOrder();
    public void submitOrder();

    List<MusicOrder> getOrderByEmail(String email);
}
