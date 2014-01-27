package org.musicstore.tests.jsfunit;

import org.musicstore.OrderServiceLocal;
import org.musicstore.model.entities.MusicOrder;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

public class OrderCreationTest extends org.apache.cactus.ServletTestCase {

    private OrderServiceLocal orderService;

    protected void setUp() throws Exception {
        InitialContext jndi = new InitialContext();
        orderService = (OrderServiceLocal) jndi.lookup("java:app/EnterpriseMusicStoreEJB/OrderServiceImpl!org.musicstore.OrderServiceLocal");
    }

    public void test_order_creation_should_succeed() throws NamingException {

        String testEmail = UUID.randomUUID() + "@test.org";
        List<MusicOrder> nonExistingOrders = orderService.getOrderByEmail(testEmail);
        assertEquals(0, nonExistingOrders.size());

        MusicOrder currentOrder = orderService.getCurrentOrder();
        assertNotNull(currentOrder);

        currentOrder.setEmail(testEmail);

        orderService.createOrder();
        orderService.submitOrder();

        List<MusicOrder> newOrders = orderService.getOrderByEmail(testEmail);
        assertEquals(1, newOrders.size());

    }
}
