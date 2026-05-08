package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.SceneLoader;
import com.cvut.fit.biopj.portniagin.semestralka.TowerOfGodApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopController extends SceneController implements Initializable {
    @FXML HBox sessionStateHBox;
    @FXML public Button fightButton;
    @FXML public Button rerollButton;
    @FXML public Button freezeButton;
    @FXML public Button levelUpButton;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            loadViewToContainer("session-state.fxml", sessionStateHBox);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onFightButtonClick(ActionEvent event) throws IOException {
        TowerOfGodApplication.setLastScene(SceneController.stage.getScene());
        SceneController.setNewScene(SceneLoader.getScene("fight-scene.fxml"));
    }
    @FXML
    private void onRerollButtonClick(ActionEvent event) throws IOException {

    }
    @FXML
    private void onFreezeButtonClick(ActionEvent event) throws IOException {

    }
    @FXML
    private void onLevelUpButtonClick(ActionEvent event) throws IOException {

    }

}
