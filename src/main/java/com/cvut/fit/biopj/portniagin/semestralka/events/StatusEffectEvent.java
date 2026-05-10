package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.StatusEffect;

public class StatusEffectEvent implements AppEvent{
    private final StatusEffect effect;
    public StatusEffectEvent(StatusEffect effect){
        this.effect = effect;
    }
    public StatusEffect getEffect(){
        return effect;
    }
}
