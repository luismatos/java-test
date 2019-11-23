package uk.henry.grocery.discount;

import java.time.LocalDate;

abstract class AbstractDiscount {
    private final LocalDate now;
    private final LocalDate from;
    private final LocalDate to;

    AbstractDiscount(final LocalDate now, final LocalDate from, final LocalDate to) {
        this.now = now;
        this.from = from;
        this.to = to;
    }

    boolean isInactive() {
        return now.isBefore(from) || now.isAfter(to);
    }
}
