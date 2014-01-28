package org.musicstore.businesslogic;

import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.ProducesAlternative;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.musicstore.model.entities.MusicOrder;
import org.musicstore.repositories.MusicOrderRepository;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(CdiRunner.class)
public class PriceCalculatorTest {

    @Test
    public void price_calculator_should_set_final_amount_to_total_amount_for_new_customers() throws Exception {

        Double totalAmount = new Double(10d);

        // Set up stubs
        MusicOrder order = mock(MusicOrder.class);
        when(order.getEmail()).thenReturn("my_email");
        when(order.getTotalAmount()).thenReturn(totalAmount);

        MusicOrderRepository orderRepositoryMock = mock(MusicOrderRepository.class);
        when(orderRepositoryMock.getOrdersByEmail(anyString())).thenReturn(new ArrayList<MusicOrder>());

        // Arrange the subject under test
        PriceCalculator priceCalculator = new PriceCalculator(orderRepositoryMock);
        //priceCalculator.musicOrderRepository = orderRepositoryMock; // Alternative Poor man's dependency injection: package private fields

        // Trigger the actual test
        Double calculatePrice = priceCalculator.calculatePrice(order);

        // State based testing: We check the final result of price calculation
        assertEquals(totalAmount, calculatePrice);
    }


    @Inject
    PriceCalculator priceCalculator;

    @Produces
    @ProducesAlternative
    @Mock
    MusicOrderRepository orderRepositoryMock; // CDI-Unit/Mockito will create a mock

    @Test
    public void price_calculator_should_set_final_amount_to_discounted_total_amount_for_existing_customers() throws Exception {

        Double totalAmount = new Double(10d);

        // Set up stubs
        MusicOrder order = mock(MusicOrder.class);
        when(order.getEmail()).thenReturn("my_email");
        when(order.getTotalAmount()).thenReturn(totalAmount);

        ArrayList<MusicOrder> existingOrders = new ArrayList<MusicOrder>();
        existingOrders.add(new MusicOrder());
        when(orderRepositoryMock.getOrdersByEmail(anyString())).thenReturn(existingOrders);

        // Trigger the actual test
        Double calculatePrice = priceCalculator.calculatePrice(order); // Dependency injection is done by CDI-Unit

        // State based testing: We check the final result of price calculation
        assertEquals(new Double(totalAmount * PriceCalculator.DISCOUNT_FACTOR), calculatePrice);
    }

}
