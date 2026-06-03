package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class ShopBuyController {
    @FXML
    Button buyItemButton;
    @FXML
    private void onBuyItemButtonClicked(ActionEvent event) throws IOException {
        System.out.println("You clicked on the item");
    }
}
