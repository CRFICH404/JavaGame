package com.cvut.fit.biopj.portniagin.semestralka.session;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.FightLostEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.FightWonEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.RedrawSessionPlateEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StartOfDayEvent;

public class Session {
    private int day;
    private int currentHealth;
    private int currentWins;
    private final int MAX_WINS = 10;
    private final int MAX_HEALTH = 10;

    public Session(){
        this.day = 1;
        this.currentHealth = 10;
        this.currentWins = 0;
        registerInEventBus();
    }
    public Session(int day, int currentHealth, int currentWins) {
        this.day = day;
        this.currentHealth = currentHealth;
        this.currentWins = currentWins;
        registerInEventBus();
    }

    public void registerInEventBus(){
        TowerOfGodApplication.getEventBus().addListener(FightWonEvent.class, this::onFightWonEvent);
        TowerOfGodApplication.getEventBus().addListener(FightLostEvent.class, this::onFightLostEvent);
        TowerOfGodApplication.getEventBus().addListener(StartOfDayEvent.class, this::onStartOfDayEvent);
    }

    public void onStartOfDayEvent(StartOfDayEvent startOfDayEvent){
        this.day++;
        TowerOfGodApplication.getEventBus().fire(new RedrawSessionPlateEvent());
    }
    public void onFightWonEvent(FightWonEvent fightWonEvent){
        this.currentWins++;
        TowerOfGodApplication.getEventBus().fire(new RedrawSessionPlateEvent());
    }
    public void onFightLostEvent(FightLostEvent fightLostEvent){
        this.currentHealth--;
        TowerOfGodApplication.getEventBus().fire(new RedrawSessionPlateEvent());
    }

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
    public int getCurrentWins() {
        return currentWins;
    }
    public void setCurrentWins(int currentWins) {
        this.currentWins = currentWins;
    }
    public int getMAX_WINS() {
        return MAX_WINS;
    }
    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }
}
