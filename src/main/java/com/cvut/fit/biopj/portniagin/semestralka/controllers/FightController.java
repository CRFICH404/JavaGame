package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.FightScheduler;
import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.FightEndEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.FightLostEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.SessionEndEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StartOfCombatEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.StartOfDayEvent;
import com.cvut.fit.biopj.portniagin.semestralka.session.Session;
import javafx.application.Platform;
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
    @FXML public Button toMenuButton;

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
            TowerOfGodApplication.getEventBus().addListener(SessionEndEvent.class, this::onSessionEndEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onForfeitButtonClick(ActionEvent actionEvent) {
        TowerOfGodApplication.getEventBus().fire(new FightEndEvent());
        TowerOfGodApplication.getEventBus().fire(new FightLostEvent());
        startOfCombatButton.setVisible(false);
        startOfCombatButton.setDisable(true);
    }

    public void onStartOfCombatButtonClicked(ActionEvent actionEvent) {
        startOfCombatButton.setVisible(false);
        startOfCombatButton.setDisable(true);
        TowerOfGodApplication.getPlayer().getPlayerDummy().setCombatActive(true);
        TowerOfGodApplication.getEnemyPlayer().getPlayerDummy().setCombatActive(true);
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
        TowerOfGodApplication.getPlayer().getPlayerDummy().setCombatActive(false);
        TowerOfGodApplication.getEnemyPlayer().deregister();
        backToShopButton.setDisable(false);
        backToShopButton.setVisible(true);
        forfeitButton.setDisable(true);
        forfeitButton.setVisible(false);
    }

    public void onSessionEndEvent(SessionEndEvent event) {
        startOfCombatButton.setVisible(false);
        startOfCombatButton.setDisable(true);
        backToShopButton.setDisable(true);
        backToShopButton.setVisible(false);
        forfeitButton.setDisable(true);
        forfeitButton.setVisible(false);
        toMenuButton.setVisible(true);
        toMenuButton.setDisable(false);
    }

    public void onToMenuButtonClicked(ActionEvent actionEvent) {
        TowerOfGodApplication.getSession().deregister();
        TowerOfGodApplication.setSession(new Session());
        Platform.runLater(() -> {
            try {
                SceneController.setNewScene(SceneLoader.getScene("main-menu.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
