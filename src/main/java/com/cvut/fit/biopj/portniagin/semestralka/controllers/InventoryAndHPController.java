package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.BuyItemEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.RemoveItemFromShopEvent;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryAndHPController extends SceneController implements Initializable {
    @FXML
    private VBox inventoryAndHPVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Pane pane = new Pane();
            pane.setPrefHeight(60);
            Pane pane1 = new Pane();
            pane1.setPrefHeight(60);
            inventoryAndHPVBox.getChildren().add(pane);
            loadViewToContainer("active-inventory.fxml", inventoryAndHPVBox);
            inventoryAndHPVBox.getChildren().add(pane1);
            loadViewToContainer("health-points.fxml", inventoryAndHPVBox);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        inventoryAndHPVBox.setOnDragOver(event -> {
            if (event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        inventoryAndHPVBox.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                String content = db.getString();
                if (content.startsWith("shop:")) {
                    try {
                        int shopIndex = Integer.parseInt(content.substring(5));
                        Item item = TowerOfGodApplication.getItemShop().getItemAtIndex(shopIndex);
                        if (item != null && TowerOfGodApplication.getPlayer() != null
                                && TowerOfGodApplication.getPlayer().getPlayerDummy().getPlayerMoney() >= item.getItemCost()) {
                            TowerOfGodApplication.getEventBus().fire(new BuyItemEvent(item));
                            TowerOfGodApplication.getEventBus().fire(new RemoveItemFromShopEvent(item));
                            success = true;
                        }
                    } catch (Exception e) {
                        System.err.println("Inventory drop failed: " + e.getMessage());
                    }
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }
}
