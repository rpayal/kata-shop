package com.co4gsl.service;

import com.co4gsl.domain.Checkout;
import com.co4gsl.domain.Item;
import com.co4gsl.rules.IPricingRule;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.co4gsl.rules.DealPriceRule.dealPriceRuleBuilder;
import static com.co4gsl.rules.IPricingRule.DEAL_PRICE_RULE;
import static com.co4gsl.rules.IPricingRule.SINGLE_PRICE_RULE;
import static com.co4gsl.rules.SinglePriceRule.singlePriceRuleBuilder;
import static org.junit.Assert.assertEquals;

/**
 * Created by rpayal on 31/10/2016.
 */
public class CheckoutServiceTest {
    private CheckoutService target;
    private Map<String, IPricingRule> pricingRules;

    private static final Item A = new Item("A", 50);
    private static final Item B = new Item("B", 30);
    private static final Item C = new Item("C", 20);
    private static final Item D = new Item("D", 15);

    @Before
    public void setup() {
        target = new CheckoutService();

        pricingRules = new HashMap();
        pricingRules.put(A.getSku()+SINGLE_PRICE_RULE, singlePriceRuleBuilder()
                .itemSKU(A.getSku()+SINGLE_PRICE_RULE)
                .item(A).build());
        pricingRules.put(B.getSku()+SINGLE_PRICE_RULE, singlePriceRuleBuilder()
                .itemSKU(B.getSku()+SINGLE_PRICE_RULE)
                .item(B).build());
        pricingRules.put(C.getSku()+SINGLE_PRICE_RULE, singlePriceRuleBuilder()
                .itemSKU(C.getSku()+SINGLE_PRICE_RULE)
                .item(C).build());
        pricingRules.put(D.getSku()+SINGLE_PRICE_RULE, singlePriceRuleBuilder()
                .itemSKU(D.getSku()+SINGLE_PRICE_RULE)
                .item(D).build());
        pricingRules.put(A.getSku()+DEAL_PRICE_RULE, dealPriceRuleBuilder()
                .itemSKU(A.getSku()+DEAL_PRICE_RULE).dealQty(3)
                .specialPrice(130)
                .item(A).build());
        pricingRules.put(B.getSku()+DEAL_PRICE_RULE, dealPriceRuleBuilder()
                .itemSKU(B.getSku()+DEAL_PRICE_RULE).dealQty(2)
                .specialPrice(45)
                .item(B).build());
    }

    @Test
    public void calculateTotalPriceOfUniqueItems() {
        Checkout checkout = new Checkout(pricingRules).scan(A).scan(C).scan(B);
        assertEquals(100d, target.calculateTotalPrice(checkout), 0.01);
    }

    @Test
    public void calculateTotalPriceOfMultipleItems() {
        Checkout checkout = new Checkout(pricingRules).scan(A).scan(D).scan(A);

        assertEquals(115d, target.calculateTotalPrice(checkout), 0.01);
    }

    @Test
    public void calculateTotalPriceOfMultipleItemsWithMultipleOffers() {
        Checkout checkout = new Checkout(pricingRules)
                .scan(A).scan(D).scan(A).scan(A)
                .scan(B).scan(A).scan(A).scan(A);

        assertEquals(305d, target.calculateTotalPrice(checkout), 0.01);
    }

    @Test
    public void calculateTotalPriceOfMultipleItemsWithMultipleOffersSce2() {
        Checkout checkout = new Checkout(pricingRules)
                .scan(B).scan(A).scan(B).scan(B);

        assertEquals(125d, target.calculateTotalPrice(checkout), 0.01);
    }

    @Test
    public void calculateTotalPriceOfMultipleItemsWithMultipleOffersForDiffItems() {
        Checkout checkout = new Checkout(pricingRules)
                .scan(A).scan(D).scan(A).scan(A)
                .scan(B).scan(A).scan(A).scan(A)
                .scan(B);

        assertEquals(320d, target.calculateTotalPrice(checkout), 0.01);
    }
}