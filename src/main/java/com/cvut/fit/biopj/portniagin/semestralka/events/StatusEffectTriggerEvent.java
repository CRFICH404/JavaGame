package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.player.PlayerDummy;
import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.StatusEffect;

public class StatusEffectTriggerEvent extends StatusEffectEvent{
    private final PlayerDummy target;
    public StatusEffectTriggerEvent(StatusEffect effect,  PlayerDummy target) {
        super(effect);
        this.target = target;
    }
    public PlayerDummy getTarget(){
        return target;
    }
}
