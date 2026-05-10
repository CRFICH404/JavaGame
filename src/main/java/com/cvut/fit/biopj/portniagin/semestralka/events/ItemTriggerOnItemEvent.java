package com.cvut.fit.biopj.portniagin.semestralka.events;


import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

public class ItemTriggerOnItemEvent extends ItemTiggerEvent {
    private final Item targetItem;
    public ItemTriggerOnItemEvent(Item sourceItem, Item targetItem) {
        super(sourceItem);
        this.targetItem = targetItem;
    }
    public Item getTargetItem() {
        return targetItem;
    }
}
