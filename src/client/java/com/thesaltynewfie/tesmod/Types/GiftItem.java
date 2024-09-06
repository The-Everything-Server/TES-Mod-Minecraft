package com.thesaltynewfie.tesmod.Types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GiftItem {
    @JsonProperty("item_id")
    private String itemId;

    private int quantity;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
