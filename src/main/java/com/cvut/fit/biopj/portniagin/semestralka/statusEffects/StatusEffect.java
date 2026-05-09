package com.cvut.fit.biopj.portniagin.semestralka.statusEffects;

public class StatusEffect {
    String effectType;
    int amount;
    public StatusEffect(String effectType, int amount) {
        this.effectType = effectType;
        this.amount = amount;
    }
    public String getEffectType() {
        return effectType;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (effectType.equals(((StatusEffect) o).getEffectType())) return true;
        return false;
    }
}
