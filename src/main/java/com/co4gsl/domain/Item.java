package com.co4gsl.domain;

/**
 * Created by rpayal on 31/10/2016.
 */
public class Item {
    private final String sku;
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

}
