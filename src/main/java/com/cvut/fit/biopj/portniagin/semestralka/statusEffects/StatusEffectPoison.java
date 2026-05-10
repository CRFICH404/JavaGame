package com.cvut.fit.biopj.portniagin.semestralka.statusEffects;

import com.cvut.fit.biopj.portniagin.semestralka.enums.StatusEffectEnum;

public class StatusEffectPoison extends StatusEffect{
    StatusEffectEnum statusEffectEnum;
    public StatusEffectPoison(int amount) {
        super(amount);
        this.statusEffectEnum = StatusEffectEnum.POISON;
    }

    @Override
    public StatusEffectEnum getStatusEffectEnum() {
        return statusEffectEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.statusEffectEnum == ((StatusEffectPoison) o).statusEffectEnum;
    }
}
