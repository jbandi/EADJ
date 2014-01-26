package org.musicstore;

import org.musicstore.model.entities.Album;
import org.musicstore.model.entities.MusicOrder;
import org.musicstore.model.entities.OrderItem;
import org.musicstore.businesslogic.PriceCalculator;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateful
@SessionScoped
@Local(OrderService.class)
public class OrderServiceImpl implements OrderService, Serializable {

    @PersistenceContext(unitName = "EnterpriseMusicStore")
    private EntityManager em;

    @Inject
    ShoppingCartService shoppingCartService;

    @Inject
    PriceCalculator priceCalculator;

    private MusicOrder currentOrder;

    @Override
    public MusicOrder getCurrentOrder() {
        if (currentOrder == null)
            currentOrder = new MusicOrder();

        return currentOrder;
    }

    public void setCurrentOrder(MusicOrder currentOrder) {
        this.currentOrder = currentOrder;
    }

    @Override
    public MusicOrder createOrder() {
        for(Album album : shoppingCartService.getAlbumsInCart()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setAlbum(album);
            currentOrder.getOrderItems().add(orderItem);
        }

        double finalAmount = priceCalculator.calculatePrice(currentOrder);
        currentOrder.setFinalAmount(finalAmount);

        return currentOrder;
    }

    @Override
    public void submitOrder() {
        currentOrder.setFinalAmount(currentOrder.getTotalAmount());
        em.persist(currentOrder);

        shoppingCartService.notifyOrderSubmitted();
        currentOrder = null;
    }

    @Override
    public List<MusicOrder> getAllOrders(){
        List<MusicOrder> resultList = em.createNamedQuery(MusicOrder.FIND_ALL).getResultList();
        return resultList;
    }
}
