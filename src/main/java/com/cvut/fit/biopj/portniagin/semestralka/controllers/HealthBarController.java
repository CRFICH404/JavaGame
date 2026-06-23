package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.CurrentHealthChangeEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.MaxHealthChangeEvent;
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

    int currentHP;
    int maxHP;

    @Override
    public void initialize (URL url, ResourceBundle rb){
        this.currentHP = TowerOfGodApplication.getPlayer().getPlayerDummy().getCurrentHP();
        this.maxHP = TowerOfGodApplication.getPlayer().getPlayerDummy().getMaxHP();
        setHealthPointsProgressBar();
        setCurrentHealthPointsLable();
        TowerOfGodApplication.getEventBus().addListener(MaxHealthChangeEvent.class, this::onMaxHealthChangeEvent);
        TowerOfGodApplication.getEventBus().addListener(CurrentHealthChangeEvent.class, this::onCurrentHealthChangeEvent);
    }

    public void onMaxHealthChangeEvent(MaxHealthChangeEvent event){
        this.maxHP = event.getNewMaxHP();
        setCurrentHealthPointsLable();
        setHealthPointsProgressBar();
    }

    public void onCurrentHealthChangeEvent(CurrentHealthChangeEvent event){
        this.currentHP = event.getCurrentHP();
        setCurrentHealthPointsLable();
        setHealthPointsProgressBar();
    }

    public void setCurrentHealthPointsLable() {this.currentHealthPointsLable.setText(this.currentHP + "/" + this.maxHP);}
    public void setHealthPointsProgressBar() {this.healthPointsProgressBar.setProgress((double) this.currentHP / this.maxHP);}

}
