package com.thesaltynewfie.tesmod.network.Types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty("item_id")
    private int itemId;

    private int quantity;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
