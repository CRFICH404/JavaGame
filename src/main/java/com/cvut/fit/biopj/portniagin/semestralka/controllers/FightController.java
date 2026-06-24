package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.FightScheduler;
import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.FightEndEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.FightLostEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StartOfCombatEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StartOfDayEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FightController extends SceneController implements Initializable {

    @FXML public Button forfeitButton;
    @FXML public BorderPane fightBorderPane;
    @FXML public HBox sessionStateVBox;
    @FXML public VBox playerDummyVBox;
    @FXML public VBox enemyDummyVBox;
    @FXML public Button startOfCombatButton;
    @FXML public Button backToShopButton;

    private final FightScheduler fightScheduler = new FightScheduler();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        try{
            loadViewToContainer(SceneLoader.getNode("user-info.fxml", () -> new UserInfoController(true)), playerDummyVBox);
            loadViewToContainer(SceneLoader.getNode("inventory-and-hp.fxml", () -> new InventoryAndHPController(true)), playerDummyVBox);
            loadViewToContainer(SceneLoader.getNode("user-info.fxml", () -> new UserInfoController(false)), enemyDummyVBox);
            loadViewToContainer(SceneLoader.getNode("inventory-and-hp.fxml", () -> new InventoryAndHPController(false)), enemyDummyVBox);
            loadViewToContainer("session-state.fxml", sessionStateVBox);
            TowerOfGodApplication.getEventBus().addListener(FightEndEvent.class, this::onFightEndEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void onForfeitButtonClick(ActionEvent actionEvent) {
        TowerOfGodApplication.getEventBus().fire(new FightEndEvent());
        TowerOfGodApplication.getEventBus().fire(new FightLostEvent());
    }

    public void onStartOfCombatButtonClicked(ActionEvent actionEvent) {
        startOfCombatButton.setVisible(false);
        startOfCombatButton.setDisable(true);
        System.out.println("Start of Combat Button clicked");
        TowerOfGodApplication.getEventBus().fire(new StartOfCombatEvent());
        fightScheduler.start(TowerOfGodApplication.getPlayer(), TowerOfGodApplication.getEnemyPlayer());
    }

    public void onBackToShopButtonClicked(ActionEvent actionEvent) {
        TowerOfGodApplication.getEventBus().fire(new StartOfDayEvent());
        SceneController.stage.setScene(SceneLoader.getLastScene());
        SceneLoader.setLastScene(forfeitButton.getScene());
    }

    public void onFightEndEvent(FightEndEvent fightEndEvent) {
        fightScheduler.stop();
        TowerOfGodApplication.getEnemyPlayer().deregister();
        backToShopButton.setDisable(false);
        backToShopButton.setVisible(true);
    }
}
