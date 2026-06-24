package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.EnemyCurrentHealthChangeEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.EnemyMaxHealthChangeEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.PlayerCurrentHealthChangeEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.PlayerMaxHealthChangeEvent;
import com.cvut.fit.biopj.portniagin.semestralka.player.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class HealthBarController extends SceneController implements Initializable {
    @FXML
    private Label currentHealthPointsLable;
    @FXML
    private ProgressBar healthPointsProgressBar;

    private int currentHP;
    private int maxHP;
    private final boolean isPlayer;

    public HealthBarController (boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    @Override
    public void initialize (URL url, ResourceBundle rb){
        Player player = isPlayer ? TowerOfGodApplication.getPlayer() : TowerOfGodApplication.getEnemyPlayer();
        this.currentHP = player.getPlayerDummy().getCurrentHP();
        this.maxHP = player.getPlayerDummy().getMaxHP();
        setHealthPointsProgressBar();
        setCurrentHealthPointsLable();
        registerInEventBus();
    }

    public void registerInEventBus(){
        if(isPlayer){
            TowerOfGodApplication.getEventBus().addListener(PlayerMaxHealthChangeEvent.class, this::onPlayerMaxHealthChangeEvent);
            TowerOfGodApplication.getEventBus().addListener(PlayerCurrentHealthChangeEvent.class, this::onPlayerCurrentHealthChangeEvent);
        }else{
            TowerOfGodApplication.getEventBus().addListener(EnemyMaxHealthChangeEvent.class, this::onEnemyMaxHealthChangedEvent);
            TowerOfGodApplication.getEventBus().addListener(EnemyCurrentHealthChangeEvent.class, this::onEnemyCurrentHealthChangedEvent);
        }
    }

    private void onEnemyCurrentHealthChangedEvent(EnemyCurrentHealthChangeEvent event) {
        this.currentHP = event.getCurrentHP();
        setCurrentHealthPointsLable();
        setHealthPointsProgressBar();
    }

    private void onEnemyMaxHealthChangedEvent(EnemyMaxHealthChangeEvent event) {
        this.maxHP = event.getNewMaxHP();
        setCurrentHealthPointsLable();
        setHealthPointsProgressBar();
    }

    public void onPlayerMaxHealthChangeEvent(PlayerMaxHealthChangeEvent event){
        this.maxHP = event.getNewMaxHP();
        setCurrentHealthPointsLable();
        setHealthPointsProgressBar();
    }

    public void onPlayerCurrentHealthChangeEvent(PlayerCurrentHealthChangeEvent event){
        this.currentHP = event.getCurrentHP();
        setCurrentHealthPointsLable();
        setHealthPointsProgressBar();
    }

    public void setCurrentHealthPointsLable() {this.currentHealthPointsLable.setText(this.currentHP + "/" + this.maxHP);}
    public void setHealthPointsProgressBar() {this.healthPointsProgressBar.setProgress((double) this.currentHP / this.maxHP);}
    public boolean isPlayer() {return isPlayer;}
}
