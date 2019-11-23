package uk.henry.grocery.basket;

import uk.henry.grocery.discount.Discount;

import java.math.BigDecimal;
import java.util.Optional;

public class Product {

    private final BigDecimal price;
    private final Discount discount;

    public Product(final BigDecimal price) {
        this(price, null);
    }

    public Product(final BigDecimal price, final Discount discount) {
        this.price = price;
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Optional<Discount> getDiscount() {
        return Optional.ofNullable(discount);
    }
}
