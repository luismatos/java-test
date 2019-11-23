package uk.henry.grocery.basket;

import java.math.BigDecimal;

public class Item {

    private final Product product;
    private final BigDecimal quantity;

    public Item(final Product product, final BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal discount(final Basket basket) {
        if (product.getDiscount().isEmpty()) {
            return BigDecimal.ZERO;
        }

        return product.getDiscount().get().discount(basket, this);
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
