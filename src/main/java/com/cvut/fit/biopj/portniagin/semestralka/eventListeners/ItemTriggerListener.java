package com.cvut.fit.biopj.portniagin.semestralka.eventListeners;

import com.cvut.fit.biopj.portniagin.semestralka.events.ItemTiggerEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.ItemTriggerOnItemEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.ItemTriggerOnPlayerDummyEvent;

public interface ItemTriggerListener extends GenericEventListener<ItemTiggerEvent> {
    void onEvent(ItemTiggerEvent event);
    void onEvent(ItemTriggerOnItemEvent event);
    void onEvent(ItemTriggerOnPlayerDummyEvent event);
}
