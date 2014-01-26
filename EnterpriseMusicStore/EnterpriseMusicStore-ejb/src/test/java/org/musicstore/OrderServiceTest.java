package org.musicstore;

import org.junit.Test;
import org.musicstore.businesslogic.PriceCalculator;
import org.musicstore.model.entities.MusicOrder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class OrderServiceTest {

    @Test
    public void creating_order_sets_final_amount(){

        Double calculatedPrice = new Double(10);

        // Set up mocks
        ShoppingCartService shoppingCartServiceMock = mock(ShoppingCartService.class);

        PriceCalculator priceCalculatorMock = mock(PriceCalculator.class);
        when(priceCalculatorMock.calculatePrice(any(MusicOrder.class))).thenReturn(calculatedPrice);

        MusicOrder musicOrderMock = mock(MusicOrder.class);

        // Arrange subject under test
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setCurrentOrder(musicOrderMock);
        orderService.shoppingCartService = shoppingCartServiceMock;
        orderService.priceCalculator = priceCalculatorMock;

        // Trigger actual test
        orderService.createOrder();

        // Interaction based testing: Inspect interactions
        verify(shoppingCartServiceMock).getAlbumsInCart();
        verify(priceCalculatorMock).calculatePrice(any(MusicOrder.class));
        verify(musicOrderMock).setFinalAmount(calculatedPrice);

        // Alternative for state based testing: Inspect state of order-service
        // assertEquals(calculatedPrice, orderService.getCurrentOrder().getFinalAmount());
    }

}
