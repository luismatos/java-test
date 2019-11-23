package uk.henry.grocery;

import java.math.BigDecimal;

public class Item {

    private final BigDecimal price;
    private final BigDecimal quantity;

    public Item(final BigDecimal price, final BigDecimal quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
}
