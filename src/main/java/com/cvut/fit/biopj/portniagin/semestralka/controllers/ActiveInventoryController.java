package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActiveInventoryController extends SceneController implements Initializable {
    @FXML GridPane activeInventoryGridPane;
    @FXML private Button invButton1;
    @FXML private Button invButton2;
    @FXML private Button invButton3;
    @FXML private Button invButton4;
    @FXML private Button invButton5;
    @FXML private Button invButton6;

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    @FXML
    public void onInvButton1Click (){
        System.out.println("Clicked on inv button 1");
    }
    @FXML
    public void onInvButton2Click (){
        System.out.println("Clicked on inv button 2");
    }
    @FXML
    public void onInvButton3Click (){
        System.out.println("Clicked on inv button 3");
    }
    @FXML
    public void onInvButton4Click (){
        System.out.println("Clicked on inv button 4");
    }
    @FXML
    public void onInvButton5Click (){
        System.out.println("Clicked on inv button 5");
    }
    @FXML
    public void onInvButton6Click (){
        System.out.println("Clicked on inv button 6");
    }
}
