package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.GameSettings;
import com.cvut.fit.biopj.portniagin.semestralka.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.SceneLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController extends SceneController implements Initializable {
    @FXML
    private Button playButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;

    @FXML
    protected void onPlayButtonClick() throws IOException {
        TowerOfGodApplication.setLastScene(playButton.getScene());
        setNewScene(playButton, SceneLoader.getScene("preparation-screen.fxml"));
        System.out.println("Play button clicked");
    }
    @FXML
    protected void onSettingsButtonClick() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        TowerOfGodApplication.setLastScene(settingsButton.getScene());
        stage.setScene(SceneLoader.getScene("settings-menu.fxml", GameSettings.getWidth(), GameSettings.getHeight()));
        System.out.println("Settings button clicked");
    }
    @FXML
    protected void onExitButtonClick() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
