package com.co4gsl.rules;

import com.co4gsl.domain.Item;

import java.util.Objects;

/**
 * Created by rpayal on 31/10/2016.
 */
public class DealPriceRule implements IPricingRule {
    private final String itemSKU;
    private final Item item;
    private final long dealQty;
    private final double specialPrice;

    DealPriceRule(DealPriceRuleBuilder dealPriceRuleBuilder) {
        this.itemSKU = dealPriceRuleBuilder.itemSKU;
        this.item = dealPriceRuleBuilder.item;
        this.dealQty = dealPriceRuleBuilder.dealQty;
        this.specialPrice = dealPriceRuleBuilder.specialPrice;
    }

    public static DealPriceRuleBuilder dealPriceRuleBuilder() {
        return new DealPriceRuleBuilder();
    }

    public String getItemSkuRuleType() {
        return itemSKU;
    }

    public Item getItem() {
        return item;
    }

    public double getPrice() {
        return specialPrice;
    }

    public long getQty() {
        return dealQty;
    }

    public final static class DealPriceRuleBuilder {
        private String itemSKU;
        private Item item;
        private long dealQty;
        private double specialPrice;

        public DealPriceRuleBuilder itemSKU(String itemSKU) {
            this.itemSKU = itemSKU;
            return this;
        }

        public DealPriceRuleBuilder item(Item item) {
            this.item = item;
            return this;
        }

        public DealPriceRuleBuilder dealQty(long qty) {
            this.dealQty = qty;
            return this;
        }

        public DealPriceRuleBuilder specialPrice(long specialPrice) {
            this.specialPrice = specialPrice;
            return this;
        }

        public DealPriceRule build() {
            return new DealPriceRule(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DealPriceRule that = (DealPriceRule) o;
        return dealQty == that.dealQty &&
                Double.compare(that.specialPrice, specialPrice) == 0 &&
                Objects.equals(itemSKU, that.itemSKU) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemSKU, item, dealQty, specialPrice);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DealPriceRule{");
        sb.append("itemSKU='").append(itemSKU).append('\'');
        sb.append(", itemSKU=").append(item);
        sb.append(", dealQty=").append(dealQty);
        sb.append(", specialPrice=").append(specialPrice);
        sb.append('}');
        return sb.toString();
    }
}
