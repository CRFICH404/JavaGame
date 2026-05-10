package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import com.cvut.fit.biopj.portniagin.semestralka.player.PlayerDummy;

public class ItemTiggerEvent implements AppEvent{
    private final Item sourceItem;
    public ItemTiggerEvent(Item sourceItem) {
        this.sourceItem = sourceItem;
    }
    public Item getSourceItem() {
        return sourceItem;
    }
}
