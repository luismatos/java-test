package uk.henry.grocery;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest {
    @Test
    void addTwoItems_getTwoItem() {
        Basket basket = new Basket();

        Item item1 = new Item(new BigDecimal(5), new BigDecimal(2));
        basket.addItem(item1);

        Item item2 = new Item(new BigDecimal(5), new BigDecimal(2));
        basket.addItem(item2);

        List<Item> items = basket.getItems();

        assertThat(items).hasSize(2);
        assertThat(items).contains(item1, item2);
    }
}
