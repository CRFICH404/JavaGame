package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.BuyItemOnCordsEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopBuyController implements Initializable {
    @FXML
    Button buyItemButton;
    int [] coordinatesInGridPane;
    public ShopBuyController(int [] coordinatesInGridPane) {
        this.coordinatesInGridPane = coordinatesInGridPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onBuyItemButtonClicked(ActionEvent event) throws IOException {
        System.out.println("You clicked on the item at Row: " + coordinatesInGridPane[0] + " Column: " + coordinatesInGridPane[1]);
        TowerOfGodApplication.getEventBus().fire(new BuyItemOnCordsEvent(coordinatesInGridPane));
    }
    public int[] getCoordinatesInGridPane() {
        return coordinatesInGridPane;
    }
}
