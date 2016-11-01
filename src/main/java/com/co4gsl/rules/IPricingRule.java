package com.co4gsl.rules;

import com.co4gsl.domain.Item;

/**
 * Created by rpayal on 31/10/2016.
 */
public interface IPricingRule {
    static final String SINGLE_PRICE_RULE = "S";
    static final String DEAL_PRICE_RULE = "D";

    /**
     * @return String containing itemSKU + RuleType
     */
    String getItemSkuRuleType();

    Item getItem();

    double getPrice();

    long getQty();
}
