package org.musicstore;

import org.musicstore.model.entities.Album;
import org.musicstore.model.entities.MusicOrder;
import org.musicstore.model.entities.OrderItem;
import org.musicstore.businesslogic.PriceCalculator;
import org.musicstore.repositories.MusicOrderRepository;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateful
@ConversationScoped
@Local(OrderServiceLocal.class)
@Remote(OrderService.class)
public class OrderServiceImpl implements OrderService, Serializable {

    @Inject
    private Conversation conversation;

    @PersistenceContext(unitName = "EnterpriseMusicStore")
    private EntityManager em;

    @Inject
    ShoppingCartService shoppingCartService;

    @Inject
    MusicOrderRepository orderRepository;

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

        currentOrder = null;
        conversation.end();
    }

    @Override
    public List<MusicOrder> getAllOrders(){
        return orderRepository.getOrders();
    }

    @Override
    public List<MusicOrder> getOrderByEmail(String email) {
        return orderRepository.getOrdersByEmail(email);
    }
}
