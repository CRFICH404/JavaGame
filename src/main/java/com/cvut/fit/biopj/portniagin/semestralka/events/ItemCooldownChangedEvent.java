package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

public class ItemCooldownChangedEvent implements AppEvent {
    private final Item source;

    public ItemCooldownChangedEvent(Item source) {
        this.source = source;
    }

    public Item getSource() {
        return source;
    }
}
