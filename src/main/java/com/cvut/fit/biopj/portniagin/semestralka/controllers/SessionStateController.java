package com.cvut.fit.biopj.portniagin.semestralka.controllers;

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

    }
}
