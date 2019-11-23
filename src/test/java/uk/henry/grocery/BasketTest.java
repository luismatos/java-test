package uk.henry.grocery;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {
    @Test
    void addTwoItems_getTwoItem() {
        Basket basket = new Basket();

        Item item1 = new Item(new Product(new BigDecimal(5)), new BigDecimal(2));
        basket.addItem(item1);

        Item item2 = new Item(new Product(new BigDecimal(5)), new BigDecimal(2));
        basket.addItem(item2);

        List<Item> items = basket.getItems();

        assertThat(items).hasSize(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void calculateTotal() {
        Basket basket = new Basket();
        basket.addItem(new Item(new Product(new BigDecimal(12.34)), new BigDecimal(2)));
        basket.addItem(new Item(new Product(new BigDecimal(56.78)), new BigDecimal(3)));

        assertThat(basket.total()).isEqualTo(new BigDecimal(195.02).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void soupAndLoafWithDiscount() {
        Basket basket = new Basket();

        final Product soupProduct = new Product(new BigDecimal(0.65));
        final Item soupItem = new Item(soupProduct, new BigDecimal(3));
        basket.addItem(soupItem);

        final LoafDiscount loafDiscount = new LoafDiscount(soupProduct);
        final Product loafProduct = new Product(new BigDecimal(0.80), loafDiscount);
        final Item loafItem = new Item(loafProduct, new BigDecimal(2));
        basket.addItem(loafItem);

        assertEquals(new BigDecimal(3.15).setScale(2, RoundingMode.HALF_UP), basket.total());
    }
}
