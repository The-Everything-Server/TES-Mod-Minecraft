package com.thesaltynewfie.tesmod.Types;

import java.util.List;

public class Order {
    private List<GiftItem> giftItems;

    public List<GiftItem> getItems() {
        return giftItems;
    }

    public void setItems(List<GiftItem> giftItems) {
        this.giftItems = giftItems;
    }
}
