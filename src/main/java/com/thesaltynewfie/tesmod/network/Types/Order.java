package com.thesaltynewfie.tesmod.network.Types;

import java.util.List;

public class Order {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
