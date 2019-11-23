package uk.henry.grocery;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal price;

    public Product(final BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
