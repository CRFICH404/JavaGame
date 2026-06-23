package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBLoader;
import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBUploader;
import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBconnector;
import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.encription.PasswordEncryptor;
import com.cvut.fit.biopj.portniagin.semestralka.player.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;

public class LogInController extends SceneController implements Initializable {
    private String username;
    private String password;
    @FXML
    public Button buttonLogIn;
    @FXML
    public Button buttonRegister;
    @FXML
    public Label buttonClickReactionTextLabel;
    @FXML
    private TextField userIDTextField;
    @FXML
    private TextField passwordTextField;


    @FXML
    protected void onRegisterButtonClick() {
        buttonClickReactionTextLabel.setText("Register Button Clicked");
        this.username = this.userIDTextField.getText().toLowerCase();
        if(passwordTextField.getText().length() < 8 || passwordTextField.getText().length() > 32) {
            buttonClickReactionTextLabel.setTextFill(Color.RED);
            buttonClickReactionTextLabel.setText("Password should contain from 8 to 32 symbols.");
            return;
        }
        if (userIDTextField.getText().length() < 6 || userIDTextField.getText().length() > 32) {
            buttonClickReactionTextLabel.setTextFill(Color.RED);
            buttonClickReactionTextLabel.setText("Username should contain from 6 to 32 symbols.");
            return;
        }
        try{
            PasswordEncryptor encryptor = new PasswordEncryptor();
            this.password = encryptor.encrypt(passwordTextField.getText());
            System.out.println(this.password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            DBconnector dBconnector = new DBconnector();
            DBLoader dbLoader = new DBLoader(dBconnector);
            if(dbLoader.usernameExists(this.username)){
                buttonClickReactionTextLabel.setTextFill(Color.RED);
                buttonClickReactionTextLabel.setText("Username Already Exists");
                dBconnector.close();
                return;
            }
            DBUploader dbUploader = new DBUploader(dBconnector);
            User user = dbUploader.registerUser(this.username, this.password);
            if(user != null){
                buttonClickReactionTextLabel.setTextFill(Color.GREEN);
                buttonClickReactionTextLabel.setText("Registration Successful");
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onLoginButtonClick() {
        buttonClickReactionTextLabel.setText("Login button clicked");
        this.username = this.userIDTextField.getText().toLowerCase();
        if (userIDTextField.getText().length() < 6 || userIDTextField.getText().length() > 32) {
            buttonClickReactionTextLabel.setTextFill(Color.RED);
            buttonClickReactionTextLabel.setText("Username doesn't exist.");
            return;
        }
        try{
            PasswordEncryptor encryptor = new PasswordEncryptor();
            this.password = encryptor.encrypt(passwordTextField.getText());
            System.out.println(this.password);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        try{
            DBconnector dbconnector = new DBconnector();
            DBLoader dbLoader = new DBLoader(dbconnector);
            System.out.println(username);
            if(!dbLoader.usernameExists(this.username)){
                buttonClickReactionTextLabel.setTextFill(Color.RED);
                this.buttonClickReactionTextLabel.setText("Username Doesn't Exists");
                dbconnector.close();
                return;
            }
            User user = dbLoader.findByUsernameAndPassword(this.username, this.password);
            if(user != null){
                user.setLoginStatus(true);
                TowerOfGodApplication.setUser(user);
                System.out.println("Login Successful");
            }
            else {
                System.out.println("User load failed");
                buttonClickReactionTextLabel.setTextFill(Color.RED);
                buttonClickReactionTextLabel.setText("Login Failed");
                dbconnector.close();
                return;
            }
            dbconnector.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            SceneLoader.setLastScene(SceneController.stage.getScene());
            SceneController.setNewScene(SceneLoader.getScene("main-menu.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
