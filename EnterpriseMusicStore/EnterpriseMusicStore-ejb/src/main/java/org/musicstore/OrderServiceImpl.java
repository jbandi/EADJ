package org.musicstore;

import org.musicstore.model.entities.MusicOrder;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@Local(OrderService.class)
public class OrderServiceImpl implements OrderService, Serializable {

    @PersistenceContext(unitName = "EnterpriseMusicStore")
    private EntityManager em;

    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public void submitOrder(MusicOrder order) {
        em.persist(order);

        shoppingCartService.notifyOrderSubmitted();
    }

    @Override
    public List<MusicOrder> getAllOrders(){
        List<MusicOrder> resultList = em.createNamedQuery(MusicOrder.FIND_ALL).getResultList();
        return resultList;
    }
}
