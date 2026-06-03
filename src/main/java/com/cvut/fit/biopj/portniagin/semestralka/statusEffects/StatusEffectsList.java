package com.cvut.fit.biopj.portniagin.semestralka.statusEffects;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StatusEffectsList {
    private ArrayList<StatusEffect> effects;
    public StatusEffectsList(){
        effects = new ArrayList<>();
    }
    public StatusEffectsList(StatusEffect effect){
        effects = new ArrayList<>();
        effects.add(effect);
    }
    public StatusEffectsList(ArrayList<StatusEffect> statusEffects){
        this.effects = statusEffects;
    }

    public void addEffect(StatusEffect effect) {
        boolean found = false;
        if (effects != null) {
            for (StatusEffect e : effects) {
                if(e.equals(effect)) {
                    e.setAmount(e.getAmount() + effect.getAmount());
                    found = true;
                }
            }
            if(!found) {effects.add(effect);}
        }
        else {
            effects = new ArrayList<StatusEffect>();
            effects.add(effect);
        }
    }

    public void mergeEffects(StatusEffectsList statusEffectsList) {
        for (StatusEffect e : statusEffectsList.effects) {
            addEffect(e);
        }
    }

    public ArrayList<StatusEffect> getEffects() {
        return effects;
    }

    public void setEffects(@NotNull ArrayList<StatusEffect> effects) {
        this.effects = effects;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (StatusEffect e : effects) {
            str.append(e.toString());
            str.append(" ");
        }
        return str.toString();
    }
}
