package uk.henry.grocery.discount;

import uk.henry.grocery.basket.Basket;
import uk.henry.grocery.basket.Item;

import java.math.BigDecimal;

public interface Discount {
    BigDecimal discount(Basket basket, Item itemWithDiscount);
}
