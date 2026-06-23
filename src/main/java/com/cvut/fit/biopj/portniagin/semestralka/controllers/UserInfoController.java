package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBLoader;
import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBconnector;
import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController extends SceneController implements Initializable {
    @FXML
    private Label usernameLabel;
    @FXML
    private Label ratingLabel;

    private String username;
    private String rating;

    public UserInfoController() {
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
        this.usernameLabel.setText(username);
        this.ratingLabel.setText(rating);
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(Label usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public Label getRatingLabel() {
        return ratingLabel;
    }

    public void setRatingLabel(Label ratingLabel) {
        this.ratingLabel = ratingLabel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
