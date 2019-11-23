package uk.henry.grocery;

import java.math.BigDecimal;

public class Item {

    private final Product product;
    private final BigDecimal quantity;

    public Item(final Product product, final BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal total() {
        return product.getPrice().multiply(quantity);
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
}
