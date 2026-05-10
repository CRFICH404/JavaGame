package com.cvut.fit.biopj.portniagin.semestralka.statusEffects;

import com.cvut.fit.biopj.portniagin.semestralka.enums.StatusEffectEnum;

public class StatusEffectShield extends StatusEffect{
    StatusEffectEnum statusEffectEnum;
    public StatusEffectShield(int amount) {
        super(amount);
        this.statusEffectEnum = StatusEffectEnum.SHIELD;
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
}
