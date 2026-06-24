package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBLoader;
import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBconnector;
import com.cvut.fit.biopj.portniagin.semestralka.application.GameSettings;
import com.cvut.fit.biopj.portniagin.semestralka.application.ItemShop;
import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.RerollEvent;
import com.cvut.fit.biopj.portniagin.semestralka.player.Player;
import com.cvut.fit.biopj.portniagin.semestralka.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends SceneController implements Initializable {
    @FXML
    private VBox playerInfoVBoX;
    @FXML
    private Button playButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;

    String username;
    String rating;

    public MainMenuController() {
        if(TowerOfGodApplication.getUser() == null) {
            this.username = "test";
            this.rating = "120";
            return;
        }
        this.username = TowerOfGodApplication.getUser().getUsername();
        try{
            DBconnector dBconnector = new DBconnector();
            DBLoader dbLoader = new DBLoader(dBconnector);
            this.rating = String.valueOf(dbLoader.getRatingByUsername(username.toLowerCase()));
            dBconnector.close();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            loadViewToContainer("user-info.fxml", playerInfoVBoX);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void onPlayButtonClick() throws IOException {
        TowerOfGodApplication.setSession(new Session());
        if(TowerOfGodApplication.getPlayer() != null) {
            TowerOfGodApplication.getPlayer().deregister();
        }
        TowerOfGodApplication.setPlayer(new Player(Integer.parseInt(rating), this.username, true));
        TowerOfGodApplication.setEnemyPlayer(null);
        TowerOfGodApplication.setItemShop(new ItemShop());
        TowerOfGodApplication.getEventBus().addListener(RerollEvent.class, event -> {TowerOfGodApplication.getItemShop().populateItemShop();});
        SceneLoader.setLastScene(playButton.getScene());
        setNewScene(playButton, SceneLoader.getScene("preparation-screen.fxml"));
        System.out.println("Play button clicked");
    }
    @FXML
    protected void onSettingsButtonClick() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        SceneLoader.setLastScene(settingsButton.getScene());
        stage.setScene(SceneLoader.getScene("settings-menu.fxml", GameSettings.getWidth(), GameSettings.getHeight()));
        System.out.println("Settings button clicked");
    }
    @FXML
    protected void onExitButtonClick() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
