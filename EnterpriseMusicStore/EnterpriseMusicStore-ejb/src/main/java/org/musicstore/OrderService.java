package org.musicstore;

import org.musicstore.model.entities.MusicOrder;

import java.util.List;

public interface OrderService {

    public void submitOrder(MusicOrder order);
    public List<MusicOrder> getAllOrders();
}
