package com.cvut.fit.biopj.portniagin.semestralka.statusEffects;

import com.cvut.fit.biopj.portniagin.semestralka.enums.StatusEffectEnum;
import com.cvut.fit.biopj.portniagin.semestralka.events.StatusEffectAdditionToItemEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StatusEffectAdditionToPlayerDummyEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StatusEffectEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StatusEffectTriggerEvent;

public class StatusEffectShield extends StatusEffect{
    StatusEffectEnum statusEffectEnum;
    public StatusEffectShield(int amount) {
        super(amount);
        this.statusEffectEnum = StatusEffectEnum.SHIELD;
    }

    @Override
    public void onEvent(StatusEffectEvent event) {

    }

    @Override
    public StatusEffectEnum getStatusEffectEnum() {
        return statusEffectEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.statusEffectEnum == ((StatusEffectShield) o).statusEffectEnum;
    }

    @Override
    public void onEvent(StatusEffectAdditionToItemEvent event) {

    }

    @Override
    public void onEvent(StatusEffectAdditionToPlayerDummyEvent event) {

    }

    @Override
    public void onEvent(StatusEffectTriggerEvent event) {

    }
}
