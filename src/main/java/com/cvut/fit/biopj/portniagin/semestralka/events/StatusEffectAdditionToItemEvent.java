package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.StatusEffect;

public class StatusEffectAdditionToItemEvent extends StatusEffectEvent{
    private final Item target;
    public StatusEffectAdditionToItemEvent(StatusEffect effect, Item target) {
        super(effect);
        this.target = target;
    }
    public Item getTarget(){
        return target;
    }
}
