package uk.henry.grocery;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<Item> items = new ArrayList<>();

    public void addItem(final Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
