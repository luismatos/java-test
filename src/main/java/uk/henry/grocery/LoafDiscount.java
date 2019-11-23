package uk.henry.grocery;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Buy 2 tins of soup and get a loaf of bread half price
 */
public class LoafDiscount {

    private final Product soup;

    public LoafDiscount(final Product soup) {
        this.soup = soup;
    }

    public BigDecimal discount(final Basket basket, final Item itemWithDiscount) {
        final Optional<Item> soup = basket.getItems().stream()
                .filter(e -> e.getProduct().equals(this.soup))
                .findFirst();

        if (soup.isEmpty() || soup.get().getQuantity().compareTo(new BigDecimal(2)) < 0) {
            return BigDecimal.ZERO;
        }

        return itemWithDiscount.getProduct().getPrice().divide(new BigDecimal(2));
    }
}
