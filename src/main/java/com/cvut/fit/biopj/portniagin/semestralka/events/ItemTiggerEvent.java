package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

public class ItemTiggerEvent implements AppEvent{
    private final Item sourceItem;
    public ItemTiggerEvent(Item sourceItem) {
        this.sourceItem = sourceItem;
    }
    public Item getSourceItem() {
        return sourceItem;
    }
}
