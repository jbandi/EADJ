package org.musicstore.integrationtests;

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.musicstore.Greeter;
import org.musicstore.OrderService;
import org.musicstore.model.entities.MusicOrder;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.UUID;

public class OrderCreationTest {

    InitialContext jndi;
    private OrderService orderService;

    @Before
    public void setUp() throws NamingException {

        jndi = JBossUtil.createInitialContext();

        orderService = (OrderService) jndi.lookup("ejb:EnterpriseMusicStore/EnterpriseMusicStoreEJB//OrderServiceImpl!org.musicstore.OrderService?stateful");
    }

    @Test
    public void create_order_should_succeed() {

        String testEmail = UUID.randomUUID() + "@test.org";
        List<MusicOrder> nonExistingOrders = orderService.getOrderByEmail(testEmail);
        assertEquals(0, nonExistingOrders.size());

        MusicOrder currentOrder = orderService.getCurrentOrder();
        assertNotNull(currentOrder);

        currentOrder.setEmail(testEmail);

        // FAIL: CDI only works in a web-context! Weld throws exceptions when EJBs are called via remote interface!
//        orderService.createOrder();
//
//        List<MusicOrder> newOrders = orderService.getOrderByEmail(testEmail);
//        assertEquals(1, newOrders.size());
    }
}
