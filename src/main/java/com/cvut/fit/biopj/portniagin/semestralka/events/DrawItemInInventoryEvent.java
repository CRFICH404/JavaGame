package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

public class DrawItemInInventoryEvent implements AppEvent{
    private final Item item;
    public DrawItemInInventoryEvent(Item item)
    {
        this.item = item;
    }
    public Item getItem()
    {return this.item;}
}
