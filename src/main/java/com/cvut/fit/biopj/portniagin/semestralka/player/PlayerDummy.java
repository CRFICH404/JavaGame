package com.cvut.fit.biopj.portniagin.semestralka.player;

import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.StatusEffect;

import java.util.ArrayList;
import java.util.List;

public class PlayerDummy {
    private int playerHP;
    private int playerXP;
    private int playerLVL;
    private int playerMoney;
    private List<StatusEffect> effects;
    private Inventory inventory;
    private Inventory activeInventory;

    public PlayerDummy() {
        this.playerHP = 500;
        this.playerXP = 0;
        this.playerLVL = 1;
        this.playerMoney = 60;
        this.effects = new ArrayList<StatusEffect>();
        this.inventory = new Inventory();
        this.activeInventory = new Inventory();
    }

    public PlayerDummy(int playerHP, int playerXP, int playerLVL, int playerMoney, List<StatusEffect> effects, Inventory inventory, Inventory activeInventory) {
        this.playerHP = playerHP;
        this.playerXP = playerXP;
        this.playerLVL = playerLVL;
        this.playerMoney = playerMoney;
        this.effects = effects;
        this.inventory = inventory;
        this.activeInventory = activeInventory;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public int getPlayerXP() {
        return playerXP;
    }

    public void setPlayerXP(int playerXP) {
        this.playerXP = playerXP;
    }

    public int getPlayerLVL() {
        return playerLVL;
    }

    public void setPlayerLVL(int playerLVL) {
        this.playerLVL = playerLVL;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    public List<StatusEffect> getEffects() {
        return effects;
    }

    public void setEffects(List<StatusEffect> effects) {
        this.effects = effects;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getActiveInventory() {
        return activeInventory;
    }

    public void setActiveInventory(Inventory activeInventory) {
        this.activeInventory = activeInventory;
    }
}
