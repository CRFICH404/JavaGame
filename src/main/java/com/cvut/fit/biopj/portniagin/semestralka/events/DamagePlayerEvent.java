package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

public class DamagePlayerEvent implements AppEvent{
    private final Item item;
    public DamagePlayerEvent(Item item) {
        this.item = item;
    }
    public Item getItem() {
        return item;
    }
}
