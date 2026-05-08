package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.TowerOfGodApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsController extends SceneController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    protected void onBackButtonClick(ActionEvent event) {
        Scene lastScene = backButton.getScene();
        SceneController.setNewScene(backButton, TowerOfGodApplication.getLastScene());
        TowerOfGodApplication.setLastScene(lastScene);
    }
}
