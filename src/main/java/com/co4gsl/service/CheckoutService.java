package com.co4gsl.service;

import com.co4gsl.domain.Checkout;
import com.co4gsl.domain.Item;
import com.co4gsl.rules.DealPriceRule;
import com.co4gsl.rules.IPricingRule;

import java.util.Map;

import static com.co4gsl.rules.IPricingRule.DEAL_PRICE_RULE;
import static com.co4gsl.rules.IPricingRule.SINGLE_PRICE_RULE;

/**
 * Created by rpayal on 31/10/2016.
 */
public class CheckoutService {
    public double calculateTotalPrice(Checkout checkout) {
        final Map<Item, Long> basket = checkout.getBasket();

        Double result = basket.entrySet().stream()
                .mapToDouble(i -> getTotal(i.getKey(), i.getValue(), checkout.getPricingRules()))
                .sum();

        return result;
    }

    private double getTotal(Item item, Long qty, Map<String, IPricingRule>  pricingRules) {
        double similarItemTotal = 0;
        if (qty > 1) {
            if (pricingRules.containsKey(item.getSku()+DEAL_PRICE_RULE)) {
                DealPriceRule rule = (DealPriceRule) pricingRules.get(item.getSku()+DEAL_PRICE_RULE);
                if (qty >= rule.getQty()) {
                    similarItemTotal += rule.getPrice();
                    qty -= rule.getQty();
                    if (qty > 0) {
                        similarItemTotal += getTotal(item, qty, pricingRules);
                    }
                } else {
                    similarItemTotal += getItemTotal(item, qty, pricingRules);
                }
            }
        } else {
            similarItemTotal += getItemTotal(item, 1, pricingRules);
        }
        return similarItemTotal;
    }

    private double getItemTotal(Item item, long qty, Map<String, IPricingRule> pricingRules) {
        IPricingRule rule = pricingRules.get(item.getSku()+SINGLE_PRICE_RULE);
        return qty * rule.getItem().getPrice();
    }
}
