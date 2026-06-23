package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreparationScreenController extends SceneController implements Initializable {
    @FXML private VBox shopVBox;
    @FXML private VBox inventoryAndHPBarVBox;
    @FXML private HBox levelAndItemsHBox;

    @Override
    public void initialize (URL url, ResourceBundle rb){
        inventoryAndHPBarVBox.getChildren().clear();
        try {
            loadViewToContainer("user-info.fxml", inventoryAndHPBarVBox);
            loadViewToContainer("inventory-and-hp.fxml", inventoryAndHPBarVBox);
            loadViewToContainer("money-level-inventory.fxml", levelAndItemsHBox);
            loadViewToContainer("shop-panel.fxml", shopVBox);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
