package com.cvut.fit.biopj.portniagin.semestralka.events;

import com.cvut.fit.biopj.portniagin.semestralka.player.PlayerDummy;
import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.StatusEffect;

public class StatusEffectAdditionToPlayerDummyEvent extends StatusEffectEvent {
    private final PlayerDummy target;
    public StatusEffectAdditionToPlayerDummyEvent(StatusEffect effect, PlayerDummy target) {
        super(effect);
        this.target = target;
    }
    public PlayerDummy getTarget() {
        return target;
    }

}
