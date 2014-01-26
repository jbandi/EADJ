package org.musicstore.web;

import org.musicstore.OrderService;
import org.musicstore.ShoppingCartService;
import org.musicstore.model.entities.Album;
import org.musicstore.model.entities.MusicOrder;
import org.musicstore.model.entities.OrderItem;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class OrderPresenter implements Serializable {

    @Inject ShoppingCartService shoppingCartService;
    @Inject OrderService orderService;

    private MusicOrder musicOrder = new MusicOrder();

    public String submitOrder(){

        for(Album album : shoppingCartService.getAlbumsInCart()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAlbum(album);
            musicOrder.getOrderItems().add(orderItem);
        }

        orderService.submitOrder(musicOrder);

        return "orderConfirmation.xhtml?faces-redirect=true";
    }

    public MusicOrder getMusicOrder() {
        return musicOrder;
    }
}
