package com.cvut.fit.biopj.portniagin.semestralka.player;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.*;
import com.cvut.fit.biopj.portniagin.semestralka.events.AddToActiveInventoryFromShopEvent;
import com.cvut.fit.biopj.portniagin.semestralka.items.Inventory;
import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.StatusEffectsList;

import java.util.ArrayList;
import java.util.List;

public class PlayerDummy {
    private int currentHP;
    private int maxHP;
    private int playerXP;
    private int playerLVL;
    private int playerMoney;
    private int income;
    private StatusEffectsList effects;
    private Inventory inventory;
    private ActiveInventory activeInventory;
    private final Player dummyHolder;
    private final List<Runnable> deregisterHandlesEnemy = new ArrayList<>();
    private final List<Runnable> deregisterHandlesPlayer = new ArrayList<>();

    public PlayerDummy(Player player) {
        this.maxHP = 500;
        this.currentHP = this.maxHP;
        this.playerXP = 0;
        this.playerLVL = 1;
        this.income = 40;
        this.playerMoney = 60;
        this.effects = new StatusEffectsList();
        this.inventory = new Inventory(this);
        this.dummyHolder = player;
        this.activeInventory = new ActiveInventory(this);
        if(dummyHolder.isPlayer()) {
            deregisterHandlesPlayer.add(TowerOfGodApplication.getEventBus().addListener(RerollEvent.class, this::onReroll));
            deregisterHandlesPlayer.add(TowerOfGodApplication.getEventBus().addListener(LevelUpEvent.class, this::onLevelUp));
            deregisterHandlesPlayer.add(TowerOfGodApplication.getEventBus().addListener(StartOfDayEvent.class, this::onDayStart));
            deregisterHandlesPlayer.add(TowerOfGodApplication.getEventBus().addListener(BuyItemEvent.class, this::onBuyItemEvent));
            deregisterHandlesPlayer.add(TowerOfGodApplication.getEventBus().addListener(AddToActiveInventoryFromShopEvent.class, this::onAddToActiveInventoryEvent));
            deregisterHandlesPlayer.add(TowerOfGodApplication.getEventBus().addListener(DamagePlayerEvent.class, this::onDamagePlayerEvent));
        } else {
            deregisterHandlesEnemy.add(TowerOfGodApplication.getEventBus().addListener(DamageEnemyEvent.class, this::onDamageEnemyEvent));
        }
    }

    public void deregister() {
        if (dummyHolder.isPlayer()) {deregisterPlayer();}
        else  {deregisterEnemy();}
    }

    public void deregisterEnemy() {
        deregisterHandlesEnemy.forEach(Runnable::run);
        deregisterHandlesEnemy.clear();
        activeInventory.deregister();
    }

    public void deregisterPlayer() {
        deregisterHandlesPlayer.forEach(Runnable::run);
        deregisterHandlesPlayer.clear();
        activeInventory.deregister();
    }

    public PlayerDummy(int maxHP, int playerLVL, Player dummyHolder) {
        this.maxHP = maxHP;
        this.currentHP = this.maxHP;
        this.playerXP = 0;
        this.playerLVL = playerLVL;
        this.income = 40;
        this.playerMoney = 0;
        this.effects = new StatusEffectsList();
        this.inventory = new Inventory(this);
        this.activeInventory = new ActiveInventory(this);
        this.dummyHolder = dummyHolder;
    }

    public void onReroll(RerollEvent event){
        this.playerMoney -= event.getRerollCost();
        TowerOfGodApplication.getEventBus().fire(new MoneyChangeEvent(this.playerMoney));
    }

    public void onAddToActiveInventoryEvent(AddToActiveInventoryFromShopEvent event) {
        this.activeInventory.addItem(event.getIndex(),  event.getItem());
    }

    public void onBuyItemEvent(BuyItemEvent event){
        this.playerMoney -= event.getItem().getItemCost();
        TowerOfGodApplication.getEventBus().fire(new MoneyChangeEvent(this.playerMoney));
    }

    public void onLevelUp(LevelUpEvent event){
        this.playerMoney -= event.getLevelUpCost();
        TowerOfGodApplication.getEventBus().fire(new MoneyChangeEvent(this.playerMoney));
        this.maxHP += maxHP * playerLVL / 10;
        TowerOfGodApplication.getEventBus().fire(new PlayerMaxHealthChangeEvent(this.maxHP));
        this.currentHP = this.maxHP;
        TowerOfGodApplication.getEventBus().fire(new PlayerCurrentHealthChangeEvent(this.currentHP));
        this.income += 10;
        TowerOfGodApplication.getEventBus().fire(new IncomeChangeEvent(this.income));
    }

    public void onDayStart(StartOfDayEvent event){
        this.playerMoney += income;
        TowerOfGodApplication.getEventBus().fire(new MoneyChangeEvent(this.playerMoney));
        this.currentHP = this.maxHP;
        TowerOfGodApplication.getEventBus().fire(new PlayerCurrentHealthChangeEvent(this.currentHP));
    }

    public void onDamagePlayerEvent(DamagePlayerEvent event){
        this.currentHP -= event.getItem().getItemDamage();
        TowerOfGodApplication.getEventBus().fire(new PlayerCurrentHealthChangeEvent(currentHP));
        if(this.currentHP < 0){
            TowerOfGodApplication.getEventBus().fire(new FightEndEvent());
            TowerOfGodApplication.getEventBus().fire(new FightLostEvent());
        }
    }

    public void onDamageEnemyEvent(DamageEnemyEvent event){
        this.currentHP -= event.getItem().getItemDamage();
        TowerOfGodApplication.getEventBus().fire(new EnemyCurrentHealthChangeEvent(currentHP));
        if(this.currentHP < 0){
            TowerOfGodApplication.getEventBus().fire(new FightEndEvent());
            TowerOfGodApplication.getEventBus().fire(new FightWonEvent());
        }
    }

    public Player getDummyHolder() {
        return dummyHolder;
    }
    public int getMaxHP() {
        return maxHP;
    }
    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
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
    public int getPlayerMoney() {return playerMoney;}
    public void setPlayerMoney(int playerMoney) {this.playerMoney = playerMoney;}
    public StatusEffectsList getEffects() {return effects;}
    public void setEffects(StatusEffectsList effects) { this.effects = effects; }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public ActiveInventory getActiveInventory() {
        return activeInventory;
    }
    public void setActiveInventory(ActiveInventory activeInventory) {
        this.activeInventory = activeInventory;
    }
    public int getCurrentHP() { return currentHP; }
    public void setCurrentHP(int currentHP) { this.currentHP = currentHP; }
    public int getIncome() { return income; }
    public void setIncome(int income) { this.income = income; }
}
