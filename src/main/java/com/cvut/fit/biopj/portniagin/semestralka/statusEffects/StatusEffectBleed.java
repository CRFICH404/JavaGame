package com.cvut.fit.biopj.portniagin.semestralka.statusEffects;

import com.cvut.fit.biopj.portniagin.semestralka.enums.StatusEffectEnum;

public class StatusEffectBleed extends StatusEffect{
    private final StatusEffectEnum statusEffectEnum;
    public StatusEffectBleed(int amount) {
        super(amount);
        this.statusEffectEnum = StatusEffectEnum.BLEED;
    }

    @Override
    public StatusEffectEnum getStatusEffectEnum() {
        return statusEffectEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.statusEffectEnum == ((StatusEffectBleed) o).statusEffectEnum;
    }
}
