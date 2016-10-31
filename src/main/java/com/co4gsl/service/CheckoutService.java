package com.co4gsl.service;

import com.co4gsl.domain.Item;

import java.util.Map;

/**
 * Created by rpayal on 31/10/2016.
 */
public class CheckoutService {
    public double calculateTotalPrice(Map<Item, Long> basket) {
        double totalPrice = 0d;
        for (Map.Entry<Item, Long> entry : basket.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }
}
