package com.cvut.fit.biopj.portniagin.semestralka.statusEffects;

import com.cvut.fit.biopj.portniagin.semestralka.enums.StatusEffectEnum;
import com.cvut.fit.biopj.portniagin.semestralka.eventListeners.StatusEffectActionListener;
import com.cvut.fit.biopj.portniagin.semestralka.events.*;

public abstract class StatusEffect implements StatusEffectActionListener {
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


    public abstract void onEvent(StatusEffectEvent event);
    public abstract StatusEffectEnum getStatusEffectEnum();
    public abstract boolean equals (Object o);

}
