package uk.henry.grocery.basket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {

    private final List<Item> items = new ArrayList<>();

    public void addItem(final Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal discount() {
        return this.items.stream()
                .map(i -> i.discount(this))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal total() {
        return this.items.stream()
                .map(Item::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(discount())
                .setScale(2, RoundingMode.HALF_UP);
    }
}
