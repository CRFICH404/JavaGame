package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

public class AddToActiveInventoryEvent implements AppEvent {
    private final Item item;
    private final int index;
    public AddToActiveInventoryEvent(int index, Item item) { this.item = item; this.index = index;}
    public Item getItem() { return item; }
    public int getIndex() { return index; }
}
