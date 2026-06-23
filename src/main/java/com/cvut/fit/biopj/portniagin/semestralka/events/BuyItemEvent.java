package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

public class BuyItemEvent implements AppEvent{
    private final Item item;
    public BuyItemEvent(Item item) {
        this.item = item;
    }
    public Item getItem() {return item;}
}
