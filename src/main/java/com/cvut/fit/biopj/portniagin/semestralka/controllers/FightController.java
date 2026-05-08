package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.TowerOfGodApplication;
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

    @Override
    public void initialize(URL url, ResourceBundle rb){
        try{
            loadViewToContainer("inventory-and-hp.fxml", playerDummyVBox);
            loadViewToContainer("inventory-and-hp.fxml", enemyDummyVBox);
            loadViewToContainer("session-state.fxml", sessionStateVBox);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void onForfeitButtonClick(ActionEvent actionEvent) {
        SceneController.stage.setScene(TowerOfGodApplication.getLastScene());
        TowerOfGodApplication.setLastScene(forfeitButton.getScene());
    }

}
