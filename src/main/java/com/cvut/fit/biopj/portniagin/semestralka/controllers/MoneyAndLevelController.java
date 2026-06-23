package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.eventListeners.GenericEventListener;
import com.cvut.fit.biopj.portniagin.semestralka.events.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import org.apache.poi.ss.formula.functions.T;

import java.net.URL;
import java.util.ResourceBundle;

public class MoneyAndLevelController extends SceneController implements Initializable {
    public ImageView goldCoinImageView;
    public TilePane inventoryTilePane;
    public Label totalGoldLabel;
    public Label currentIncomeLabel;
    public Label currentLevel;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        totalGoldLabel.setText(String.valueOf(TowerOfGodApplication.getPlayer().getPlayerDummy().getPlayerMoney()));
        currentIncomeLabel.setText(String.valueOf(TowerOfGodApplication.getPlayer().getPlayerDummy().getIncome()));
        currentLevel.setText(String.valueOf(TowerOfGodApplication.getPlayer().getPlayerDummy().getPlayerLVL()));
        TowerOfGodApplication.getEventBus().addListener(MoneyChangeEvent.class, this::onMoneyChangeEvent);
        TowerOfGodApplication.getEventBus().addListener(LevelUpEvent.class, this::onLevelUpEvent);
        TowerOfGodApplication.getEventBus().addListener(IncomeChangeEvent.class, this::onIncomeChangeEvent);
    }

    public void onMoneyChangeEvent(MoneyChangeEvent event){
        totalGoldLabel.setText(String.valueOf(TowerOfGodApplication.getPlayer().getPlayerDummy().getPlayerMoney()));
    }

    public void onLevelUpEvent(LevelUpEvent event){
        currentLevel.setText(String.valueOf(TowerOfGodApplication.getPlayer().getPlayerDummy().getPlayerLVL() + 1));
    }

    public void onIncomeChangeEvent(IncomeChangeEvent event){
        currentIncomeLabel.setText(String.valueOf(event.getNewIncome()));
    }
}
