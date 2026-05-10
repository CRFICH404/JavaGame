package com.cvut.fit.biopj.portniagin.semestralka.statusEffects;

import com.cvut.fit.biopj.portniagin.semestralka.enums.StatusEffectEnum;

public class StatusEffectShock extends StatusEffect{
    StatusEffectEnum statusEffectEnum;
    public StatusEffectShock(int amount) {
        super(amount);
        this.statusEffectEnum = StatusEffectEnum.SHOCK;
    }

    @Override
    public StatusEffectEnum getStatusEffectEnum() {
        return statusEffectEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.statusEffectEnum == ((StatusEffectShock) o).statusEffectEnum;
    }
}
