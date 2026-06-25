package com.cvut.fit.biopj.portniagin.semestralka.session;

import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBLoader;
import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBUploader;
import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBconnector;
import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private int day;
    private int currentHealth;
    private int currentWins;
    private final int MAX_WINS = 10;
    private final int MAX_HEALTH = 10;
    private final List<Runnable> deregister = new ArrayList<>();

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
    }

    public void registerInEventBus(){
        deregister.add(TowerOfGodApplication.getEventBus().addListener(FightWonEvent.class, this::onFightWonEvent));
        deregister.add(TowerOfGodApplication.getEventBus().addListener(FightLostEvent.class, this::onFightLostEvent));
        deregister.add(TowerOfGodApplication.getEventBus().addListener(StartOfDayEvent.class, this::onStartOfDayEvent));
    }

    public void deregister(){
        deregister.forEach(Runnable::run);
        deregister.clear();
    }

    public void onStartOfDayEvent(StartOfDayEvent startOfDayEvent){
        this.day++;
        TowerOfGodApplication.getEventBus().fire(new RedrawSessionPlateEvent());
    }
    public void onFightWonEvent(FightWonEvent fightWonEvent){
        this.currentWins++;
        if(this.currentWins >= MAX_WINS){
            updateRating();
            TowerOfGodApplication.getEventBus().fire(new SessionEndEvent());
            return;
        }
        TowerOfGodApplication.getEventBus().fire(new RedrawSessionPlateEvent());
    }
    public void onFightLostEvent(FightLostEvent fightLostEvent){
        this.currentHealth--;
        if(this.currentHealth <= 0){
            updateRating();
            TowerOfGodApplication.getEventBus().fire(new SessionEndEvent());
            return;
        }
        TowerOfGodApplication.getEventBus().fire(new RedrawSessionPlateEvent());
    }
    public void updateRating(){
        int ratingChange = currentWins * 10 - 30;
        if(this.currentWins == MAX_WINS){ratingChange = 100;}
        try{
            ratingChange += TowerOfGodApplication.getPlayer().getRating();
            DBconnector connector = new DBconnector();
            DBUploader uploader = new DBUploader(connector);
            uploader.updateRating(TowerOfGodApplication.getUser().getUserID(), ratingChange);
            connector.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void resetSession(){
        this.currentHealth = 10;
        this.currentWins = 0;
        this.day = 1;
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
