package uk.henry.grocery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Buy 2 tins of soup and get a loaf of bread half price
 */
public class LoafDiscount {
    private final LocalDate now;
    private final LocalDate from;
    private final LocalDate to;

    private final Product soup;

    public LoafDiscount(final LocalDate now, final LocalDate from, final LocalDate to, final Product soup) {
        this.now = now;
        this.from = from;
        this.to = to;
        this.soup = soup;
    }

    public BigDecimal discount(final Basket basket, final Item itemWithDiscount) {
        if (now.isBefore(from) || now.isAfter(to)) {
            return BigDecimal.ZERO;
        }

        final Optional<Item> soup = basket.getItems().stream()
                .filter(e -> e.getProduct().equals(this.soup))
                .findFirst();

        if (soup.isEmpty() || soup.get().getQuantity().compareTo(new BigDecimal(2)) < 0) {
            return BigDecimal.ZERO;
        }

        return itemWithDiscount.getProduct().getPrice().divide(new BigDecimal(2));
    }
}
