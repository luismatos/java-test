package uk.henry.grocery;

import java.math.BigDecimal;
import java.util.Optional;

public class Product {

    private final BigDecimal price;
    private final LoafDiscount discount;

    public Product(final BigDecimal price) {
        this(price, null);
    }

    public Product(final BigDecimal price, final LoafDiscount discount) {
        this.price = price;
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Optional<LoafDiscount> getDiscount() {
        return Optional.ofNullable(discount);
    }
}
