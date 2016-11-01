package com.co4gsl.domain;

import com.sun.istack.internal.NotNull;

import java.util.Objects;

/**
 * Created by rpayal on 31/10/2016.
 */
public class Item {
    @NotNull
    private final String sku;
    @NotNull
    private final double price;

    public Item(String sku, double price) {
        super();
        this.sku = sku;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 &&
                Objects.equals(sku, item.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, price);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("sku='").append(sku).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
