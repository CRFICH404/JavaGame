package com.cvut.fit.biopj.portniagin.semestralka.statusEffects;

import com.cvut.fit.biopj.portniagin.semestralka.enums.StatusEffectEnum;
import com.cvut.fit.biopj.portniagin.semestralka.events.StatusEffectAdditionToItemEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StatusEffectAdditionToPlayerDummyEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StatusEffectTriggerEvent;

public abstract class StatusEffect implements StatusEffectTriggerListener, StatusEffectAdditionListener {
    int amount;
    public StatusEffect() {
        this.amount = 0;
    }
    public StatusEffect(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void onStatusEffectAdditionToItem(StatusEffectAdditionToItemEvent event){
        event.getTarget().getEffects().add(event.getEffect());
    }

    @Override
    public void onStatusEffectAdditionToPlayerDummy(StatusEffectAdditionToPlayerDummyEvent event){
        event.getTarget().getEffects().addEffect(event.getEffect());
    }

    @Override
    public abstract void onStatusEffectTrigger(StatusEffectTriggerEvent event);
    public abstract StatusEffectEnum getStatusEffectEnum();
    public abstract boolean equals (Object o);

}
