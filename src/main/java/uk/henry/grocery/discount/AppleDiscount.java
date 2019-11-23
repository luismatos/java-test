package uk.henry.grocery.discount;

import uk.henry.grocery.basket.Basket;
import uk.henry.grocery.basket.Item;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Apples have a 10% discount
 */
public class AppleDiscount extends AbstractDiscount implements Discount {

    public AppleDiscount(final LocalDate now, final LocalDate from, final LocalDate to) {
        super(now, from, to);
    }

    @Override
    public BigDecimal discount(final Basket basket, final Item itemWithDiscount) {
        if (isInactive()) {
            return BigDecimal.ZERO;
        }

        return itemWithDiscount.total().multiply(new BigDecimal(0.10));
    }
}
