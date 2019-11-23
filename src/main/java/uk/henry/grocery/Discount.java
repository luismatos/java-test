package uk.henry.grocery;

import java.math.BigDecimal;

public interface Discount {
    BigDecimal discount(Basket basket, Item itemWithDiscount);
}
