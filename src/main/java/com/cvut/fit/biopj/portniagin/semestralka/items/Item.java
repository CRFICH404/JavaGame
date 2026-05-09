package com.cvut.fit.biopj.portniagin.semestralka.items;

import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.StatusEffect;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Item implements Callable {

    private int id;
    private int rarity;
    private int itemCost;
    private int itemDamage;
    private int itemLevel;
    private float critChance;
    private float itemCooldown;
    private String itemName;
    private List<StatusEffect> effects;

    public Item(int id, int rarity, int itemCost, int itemDamage, int itemLevel, float critChance, float itemCooldown, String itemName, ArrayList<StatusEffect> effects) {
        this.id = id;
        this.rarity = rarity;
        this.itemCost = itemCost;
        this.itemDamage = itemDamage;
        this.itemLevel = itemLevel;
        this.critChance = critChance;
        this.itemCooldown = itemCooldown;
        this.itemName = itemName;
        this.effects = effects;
    }

    public Item(int id, int rarity, int itemCost, int itemDamage, int itemLevel, float critChance, float itemCooldown, String itemName, StatusEffect effect) {
        this.id = id;
        this.rarity = rarity;
        this.itemCost = itemCost;
        this.itemDamage = itemDamage;
        this.itemLevel = itemLevel;
        this.critChance = critChance;
        this.itemCooldown = itemCooldown;
        this.itemName = itemName;
        this.effects = new ArrayList<StatusEffect>();
        addEffect(effect);
    }

    @Override
    public Object call() throws Exception {
        return null;
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

    public void mergeEffectList(@NotNull List<StatusEffect> list) {
        for (StatusEffect e : list) {
            addEffect(e);
        }
    }

    public float getCritChance() {
        return critChance;
    }

    public void setCritChance(float critChance) {
        this.critChance = critChance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getItemCost() {
        return itemCost;
    }

    public void setItemCost(int itemCost) {
        this.itemCost = itemCost;
    }

    public int getItemDamage() {
        return itemDamage;
    }

    public void setItemDamage(int itemDamage) {
        this.itemDamage = itemDamage;
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public float getItemCooldown() {
        return itemCooldown;
    }

    public void setItemCooldown(float itemCooldown) {
        this.itemCooldown = itemCooldown;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<StatusEffect> getEffects() {
        return effects;
    }

    public void setEffects(List<StatusEffect> effects) {
        this.effects = effects;
    }
}
