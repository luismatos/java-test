package uk.henry.grocery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class BasketTest {
    private Basket basket;
    private LocalDate now = LocalDate.now();

    @BeforeEach
    void setUp() {
        basket = new Basket();
        now = LocalDate.now();
    }

    @Test
    @DisplayName("multiple items without discount")
    void should_calculateTotal_When_MultipleItemsWithoutDiscount() {
        basket.addItem(new Item(new Product(new BigDecimal(12.34)), new BigDecimal(2)));
        basket.addItem(new Item(new Product(new BigDecimal(56.78)), new BigDecimal(3)));

        assertThat(basket.total()).isEqualTo(new BigDecimal(195.02).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    @DisplayName("3 tins of soup and 2 loaves of bread, bought today")
    void should_ApplyDiscount_When_soupAndLoafWithDiscountToday() {
        final Product soupProduct = new Product(new BigDecimal(0.65));
        final Item soupItem = new Item(soupProduct, new BigDecimal(3));
        basket.addItem(soupItem);

        final LoafDiscount loafDiscount = new LoafDiscount(now, now.minusDays(1), now.plusDays(7), soupProduct);
        final Product loafProduct = new Product(new BigDecimal(0.80), loafDiscount);
        final Item loafItem = new Item(loafProduct, new BigDecimal(2));
        basket.addItem(loafItem);

        assertThat(basket.total()).isEqualTo(new BigDecimal(3.15).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    @DisplayName("3 tins of soup and 2 loaves of bread, bought in 8 days time")
    void should_ApplyDiscount_When_SoupAndLoafWithDiscountInEightDays() {
        final Product soupProduct = new Product(new BigDecimal(0.65));
        final Item soupItem = new Item(soupProduct, new BigDecimal(3));
        basket.addItem(soupItem);

        final LoafDiscount loafDiscount = new LoafDiscount(now.plusDays(8), now.minusDays(1), now.plusDays(7), soupProduct);
        final Product loafProduct = new Product(new BigDecimal(0.80), loafDiscount);
        final Item loafItem = new Item(loafProduct, new BigDecimal(2));
        basket.addItem(loafItem);

        assertThat(basket.total()).isEqualTo(new BigDecimal(3.55).setScale(2, RoundingMode.HALF_UP));
    }
}
