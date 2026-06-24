package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryAndHPController extends SceneController implements Initializable {
    @FXML
    private VBox inventoryAndHPVBox;
    private final boolean isPlayer;

    public InventoryAndHPController(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Pane pane = new Pane();
            pane.setPrefHeight(60);
            Pane pane1 = new Pane();
            pane1.setPrefHeight(60);
            inventoryAndHPVBox.getChildren().add(pane);
            loadViewToContainer(SceneLoader.getNode("active-inventory.fxml", () -> new ActiveInventoryController(isPlayer)), inventoryAndHPVBox);
            inventoryAndHPVBox.getChildren().add(pane1);
            loadViewToContainer(SceneLoader.getNode("health-points.fxml", () -> new HealthBarController(isPlayer)), inventoryAndHPVBox);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPlayer() {
        return isPlayer;
    }
}
