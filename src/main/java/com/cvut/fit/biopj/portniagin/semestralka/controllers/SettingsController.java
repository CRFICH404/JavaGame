package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SettingsController extends SceneController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    protected void onBackButtonClick(ActionEvent event) {
        Scene lastScene = backButton.getScene();
        SceneController.setNewScene(backButton, SceneLoader.getLastScene());
        SceneLoader.setLastScene(lastScene);
    }

}
