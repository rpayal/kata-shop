package com.co4gsl.domain;

import com.co4gsl.rules.IPricingRule;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by rpayal on 01/11/2016.
 */
public class Checkout {
    private final Map<String, IPricingRule> pricingRules;
    private final Map<Item, Long> basket = new HashMap<Item, Long>();

    public Checkout(Map<String, IPricingRule>  pricingRules) {
        super();
        this.pricingRules = pricingRules;
    }

    public Map<Item, Long> getBasket() {
        return basket;
    }

    public Map<String, IPricingRule>  getPricingRules() {
        return pricingRules;
    }

    public Checkout scan(Item item) {
        Long qty = basket.get(item);
        if (qty != null) {
            qty++;
        } else {
            qty = 1l;
        }
        basket.put(item, qty);
        return this;
    }
}
