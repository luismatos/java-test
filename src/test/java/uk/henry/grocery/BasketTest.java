package uk.henry.grocery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

class BasketTest {
    private Basket basket;
    private LocalDate now;
    private long daysEndOfNextMonth;

    @BeforeEach
    void setUp() {
        basket = new Basket();
        now = LocalDate.now();

        final LocalDate endOfNextMonth = YearMonth.of(now.getYear(), now.getMonth()).plusMonths(1).atEndOfMonth();
        daysEndOfNextMonth = ChronoUnit.DAYS.between(now, endOfNextMonth);
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

    @Test
    @DisplayName("6 apples and a bottle of milk, bought today")
    void should_ApplyDiscount_When_ApplesWithDiscountAndMilkToday() {
        final AppleDiscount appleDiscount = new AppleDiscount(now, now.plusDays(3), now.plusDays(daysEndOfNextMonth));
        final Product appleProduct = new Product(new BigDecimal(0.10), appleDiscount);
        final Item appleItem = new Item(appleProduct, new BigDecimal(6));
        basket.addItem(appleItem);

        final LoafDiscount loafDiscount = new LoafDiscount(now, now.minusDays(1), now.plusDays(7), null);
        final Product milkProduct = new Product(new BigDecimal(1.30), loafDiscount);
        final Item milkItem = new Item(milkProduct, new BigDecimal(1));

        basket.addItem(milkItem);

        assertThat(basket.total()).isEqualTo(new BigDecimal(1.90).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    @DisplayName("6 apples and a bottle of milk, bought in 5 days time")
    void should_ApplyDiscount_When_ApplesWithDiscountAndMilkInFiveDays() {
        final AppleDiscount appleDiscount = new AppleDiscount(now.plusDays(5), now.plusDays(3), now.plusDays(daysEndOfNextMonth));
        final Product appleProduct = new Product(new BigDecimal(0.10), appleDiscount);
        final Item appleItem = new Item(appleProduct, new BigDecimal(6));
        basket.addItem(appleItem);

        final LoafDiscount loafDiscount = new LoafDiscount(now.plusDays(5), now.minusDays(1), now.plusDays(7), null);
        final Product milkProduct = new Product(new BigDecimal(1.30), loafDiscount);
        final Item milkItem = new Item(milkProduct, new BigDecimal(1));

        basket.addItem(milkItem);

        assertThat(basket.total()).isEqualTo(new BigDecimal(1.84).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    @DisplayName("3 apples, 2 tins of soup and a loaf of bread, bought in 5 days time")
    void should_ApplyDiscount_When_ApplesWithDiscountSoupAndLoafWithDiscountInFiveDays() {
        final AppleDiscount appleDiscount = new AppleDiscount(now.plusDays(5), now.plusDays(3), now.plusDays(daysEndOfNextMonth));
        final Product appleProduct = new Product(new BigDecimal(0.10), appleDiscount);
        final Item appleItem = new Item(appleProduct, new BigDecimal(3));
        basket.addItem(appleItem);

        final Product soupProduct = new Product(new BigDecimal(0.65));
        final Item soupItem = new Item(soupProduct, new BigDecimal(2));
        basket.addItem(soupItem);

        final LoafDiscount loafDiscount = new LoafDiscount(now.plusDays(5), now.minusDays(1), now.plusDays(7), soupProduct);
        final Product loafProduct = new Product(new BigDecimal(0.80), loafDiscount);
        final Item loafItem = new Item(loafProduct, new BigDecimal(1));
        basket.addItem(loafItem);

        assertThat(basket.total()).isEqualTo(new BigDecimal(1.97).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    @DisplayName("No items in the basket")
    void should_ReturnZero_When_noItemsInTheBasket() {
        assertThat(basket.total()).isEqualTo(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
    }
}
