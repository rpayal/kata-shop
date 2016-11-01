package com.co4gsl.rules;

import com.co4gsl.domain.Item;

import java.util.Objects;

/**
 * Created by rpayal on 31/10/2016.
 */
public class SinglePriceRule implements IPricingRule {
    private final String itemSKU;
    private final Item item;
    private final double price;

    SinglePriceRule(SinglePriceRuleBuilder singlePriceRuleBuilder) {
        this.itemSKU = singlePriceRuleBuilder.itemSKU;
        this.item = singlePriceRuleBuilder.item;
        this.price = singlePriceRuleBuilder.price;
    }

    public static SinglePriceRuleBuilder singlePriceRuleBuilder() {
        return new SinglePriceRuleBuilder();
    }

    public String getItemSkuRuleType() {
        return itemSKU;
    }

    public Item getItem() {
        return item;
    }

    public double getPrice() {
        return item.getPrice();
    }

    public long getQty() {
        return 1l;
    }

    public final static class SinglePriceRuleBuilder {
        private String itemSKU;
        private Item item;
        private double price;

        public SinglePriceRuleBuilder itemSKU(String itemSKU) {
            this.itemSKU = itemSKU;
            return this;
        }

        public SinglePriceRuleBuilder item(Item item) {
            this.item = item;
            return this;
        }

        public SinglePriceRuleBuilder price(double price) {
            this.price = price;
            return this;
        }

        public SinglePriceRule build() {
            return new SinglePriceRule(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinglePriceRule that = (SinglePriceRule) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(itemSKU, that.itemSKU) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemSKU, item, price);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SinglePriceRule{");
        sb.append("itemSKU='").append(itemSKU).append('\'');
        sb.append(", itemSKU=").append(item);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
