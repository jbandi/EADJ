package org.musicstore.web;

import org.musicstore.OrderService;
import org.musicstore.OrderServiceLocal;
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
    @Inject OrderServiceLocal orderService;

    public String createOrder(){

        orderService.createOrder();

        return "orderSubmission.xhtml?faces-redirect=true";
    }

    public String submitOrder(){

        orderService.submitOrder();

        return "orderConfirmation.xhtml?faces-redirect=true";
    }

    public MusicOrder getMusicOrder() {
        return orderService.getCurrentOrder();
    }
}
