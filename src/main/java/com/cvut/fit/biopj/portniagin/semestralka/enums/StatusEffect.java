package com.cvut.fit.biopj.portniagin.semestralka.enums;

public enum StatusEffect {
    BURN("Burn"),
    BLEED("Bleed"),
    POISON("Poison"),
    HEAL("Heal"),
    SHIELD("Shield");

    private final String effect;

    StatusEffect(String effect) {
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }
}
