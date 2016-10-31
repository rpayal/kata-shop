package com.co4gsl.service;

import com.co4gsl.domain.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by rpayal on 31/10/2016.
 */
public class CheckoutServiceTest {
    private CheckoutService target;

    private static final Item A = new Item("A", 50);
    private static final Item B = new Item("B", 30);
    private static final Item C = new Item("C", 20);
    private static final Item D = new Item("D", 15);

    @Before
    public void setup() {
       target = new CheckoutService();
    }


    @Test
    public void calculateTotalPriceOfUniqueItems() {
        final Map<Item, Long> basket = new HashMap<Item, Long>();
        basket.put(A, 1l);
        basket.put(D, 1l);

        assertEquals(65d, target.calculateTotalPrice(basket), 0.01);
    }

    @Test
    public void calculateTotalPriceOfMultipleItems() {
        final Map<Item, Long> basket = new HashMap<Item, Long>();
        basket.put(A, 2l);
        basket.put(D, 1l);

        assertEquals(115d, target.calculateTotalPrice(basket), 0.01);
    }
}