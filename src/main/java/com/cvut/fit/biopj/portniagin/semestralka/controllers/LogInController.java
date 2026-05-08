package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LogInController extends SceneController implements Initializable {
    private String userID;
    private String password;

    @FXML
    private TextField userIDTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    protected void onRegisterButtonClick() {
        System.out.println("Register button clicked");
    }
    @FXML
    protected void onLoginButtonClick() {
        System.out.println("Login button clicked");
    }
}
