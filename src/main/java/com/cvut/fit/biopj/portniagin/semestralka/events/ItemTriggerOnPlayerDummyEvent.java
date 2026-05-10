package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import com.cvut.fit.biopj.portniagin.semestralka.player.PlayerDummy;

public class ItemTriggerOnPlayerDummyEvent extends ItemTiggerEvent {
    private final PlayerDummy target;
    public ItemTriggerOnPlayerDummyEvent(Item sourceItem, PlayerDummy target) {
        super(sourceItem);
        this.target = target;
    }
    public PlayerDummy getTarget() {
        return target;
    }
}
