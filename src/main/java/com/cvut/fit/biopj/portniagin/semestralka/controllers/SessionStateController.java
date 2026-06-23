package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class SessionStateController extends SceneController implements Initializable {
    @FXML public ImageView dayImageView;
    @FXML public Label dayCounter;
    @FXML public ImageView trophyImageView;
    @FXML public Label winCounter;
    @FXML public ImageView playerHealthImageView;
    @FXML public Label playerHealthCounter;

    @Override
    public void initialize (URL url, ResourceBundle rb){
        Session session = TowerOfGodApplication.getSession();
        this.dayCounter.setText(String.valueOf(session.getDay()));
        this.playerHealthCounter.setText(String.valueOf(session.getCurrentHealth())+"/"+session.getMAX_HEALTH());
        this.winCounter.setText(String.valueOf(session.getCurrentWins())+"/"+session.getMAX_WINS());
    }

}
