package com.cvut.fit.biopj.portniagin.semestralka.enums;

public enum SpecialEffectEnum {
    NONE("NONE"),
    ADDDAMAGE("INCREASE DAMAGE"),
    ADDMULTICAST("INCREASE MULTICAST"),
    ADDCRITCHANCE("INCREASE CRITICAL HIT CHANCE"),
    REDUCECOOLDOWN("REDUCE COOLDOWN");

    private final String specialEffect;

    SpecialEffectEnum(String specialEffect) {
        this.specialEffect = specialEffect;
    }

    public String getSpecialEffect() {
        return specialEffect;
    }
}
