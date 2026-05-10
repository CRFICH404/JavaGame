package com.cvut.fit.biopj.portniagin.semestralka.enums;

public enum StatusEffectEnum {
    BURN("Burn"),
    BLEED("Bleed"),
    POISON("Poison"),
    HEAL("Heal"),
    SHIELD("Shield"),
    SHOCK("Shock");

    private final String effect;

    StatusEffectEnum(String effect) {
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }
}
