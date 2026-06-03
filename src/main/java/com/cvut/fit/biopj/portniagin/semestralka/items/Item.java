package com.cvut.fit.biopj.portniagin.semestralka.items;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.enums.RarityEnum;
import com.cvut.fit.biopj.portniagin.semestralka.events.ItemCooldownChangedEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.ItemTiggerEvent;
import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.StatusEffect;
import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.StatusEffectsList;

import java.util.ArrayList;

public class Item implements Runnable{

    private final int itemID;
    private final RarityEnum itemRarity;
    private final int itemCost;
    private int itemDamage;
    private int itemLevel;
    private int itemMulticast;
    private float itemCritChance;
    private float itemCooldown;
    private String itemName;
    private Inventory holder;
    private StatusEffectsList effects;
    private ItemSpecialEffect specialEffect;

    public Item(int itemID, RarityEnum itemRarity, int itemCost, int itemDamage, int itemLevel, int itemMulticast, float itemCritChance, float itemCooldown, String itemName, ArrayList<StatusEffect> effects) {
        this.itemID = itemID;
        this.itemRarity = itemRarity;
        this.itemCost = itemCost;
        this.itemDamage = itemDamage;
        this.itemLevel = itemLevel;
        this.itemMulticast = itemMulticast;
        this.itemCritChance = itemCritChance;
        this.itemCooldown = itemCooldown;
        this.itemName = itemName;
        this.effects = new StatusEffectsList(effects);
    }

    public Item(int itemID, RarityEnum itemRarity, int itemCost, int itemDamage, int itemLevel, int itemMulticast, float itemCritChance, float itemCooldown, String itemName, StatusEffect effect) {
        this.itemID = itemID;
        this.itemRarity = itemRarity;
        this.itemCost = itemCost;
        this.itemDamage = itemDamage;
        this.itemLevel = itemLevel;
        this.itemMulticast = itemMulticast;
        this.itemCritChance = itemCritChance;
        this.itemCooldown = itemCooldown;
        this.itemName = itemName;
        this.effects = new StatusEffectsList(effect);
    }

    public Item(int itemID, RarityEnum itemRarity, int itemCost, int itemDamage, int itemLevel, int itemMulticast, float itemCritChance, float itemCooldown, String itemName) {
        this.itemID = itemID;
        this.itemRarity = itemRarity;
        this.itemCost = itemCost;
        this.itemDamage = itemDamage;
        this.itemLevel = itemLevel;
        this.itemMulticast = itemMulticast;
        this.itemCritChance = itemCritChance;
        this.itemCooldown = itemCooldown;
        this.itemName = itemName;
        this.effects = new StatusEffectsList();
    }

    @Override
    public void run() {
        TowerOfGodApplication.getEventBus().fire(new ItemTiggerEvent(this));
    }
    @Override
    public String toString() {
        String itemEffects = "";
        for(StatusEffect effect : this.effects.getEffects()){

        }
        return String.format("[ID: %d Name: %s, Cost: %d, %s, Damage: %d, Lvl: %d, Multicast: %d, Crit: %.0f, CD: %.1f [%s], %s]",
                itemID, itemName, itemCost, itemRarity.getValue(), itemDamage, itemLevel, itemMulticast, itemCritChance*100, itemCooldown, effects.toString(), specialEffect.toString());
    }

    public String getItemName() {
        return itemName;
    }
    public RarityEnum getItemRarity() {
        return itemRarity;
    }
    public Inventory getHolder() {return holder; }
    public ItemSpecialEffect getSpecialEffect() {return specialEffect; }
    public StatusEffectsList getEffects() {
        return effects;
    }
    public float getItemCritChance() {
        return itemCritChance;
    }
    public float getItemCooldown() {
        return itemCooldown;
    }
    public int getItemID() {
        return itemID;
    }
    public int getItemCost() {
        return itemCost;
    }
    public int getItemLevel() {
        return itemLevel;
    }
    public int getItemDamage() {
        return itemDamage;
    }

    public void setItemDamage(int itemDamage) {
        this.itemDamage = itemDamage;
    }
    public void setItemCritChance(float itemCritChance) {
        this.itemCritChance = itemCritChance;
    }
    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }
    public void setItemCooldown(float itemCooldown) {
        this.itemCooldown = itemCooldown;
        TowerOfGodApplication.getEventBus().fire(new ItemCooldownChangedEvent(this));
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setEffects(ArrayList<StatusEffect> effects) {
        this.effects.setEffects(effects);
    }
    public void setItemSpecialEffect(ItemSpecialEffect specialEffect) {this.specialEffect = specialEffect;}
    public void setHolder(Inventory holder) {this.holder = holder;}
}
