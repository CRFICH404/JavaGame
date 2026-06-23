package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.*;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActiveInventoryController extends SceneController implements Initializable {
    @FXML GridPane activeInventoryGridPane;

    private final boolean[][] occupiedSlots = new boolean[3][2]; // [row][col]

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupDropTarget();
        reRenderGridPane();
        TowerOfGodApplication.getEventBus().addListener(ActiveInventoryUpdatedEvent.class, this::onActiveInventoryUpdateEvent);
    }

    private void setupDropTarget() {
        activeInventoryGridPane.setOnDragOver(event -> {
            if (event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        activeInventoryGridPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                String payload = db.getString();
                if (payload.startsWith("itemShopGridPane")) {
                    try {
                        int shopIndex = Integer.parseInt(payload.substring("itemShopGridPane".length() + 1));
                        Item item = TowerOfGodApplication.getItemShop().getItemAtIndex(shopIndex);
                        int[] slot = resolveSlot(event.getX(), event.getY());
                        int col = slot[0];
                        int row = slot[1];
                        if (item != null && TowerOfGodApplication.getPlayer() != null
                                && TowerOfGodApplication.getPlayer().getPlayerDummy().getPlayerMoney() >= item.getItemCost()
                                && !occupiedSlots[row][col] && (row * 2 + col) < 6) {
                            TowerOfGodApplication.getEventBus().fire(new AddToActiveInventoryEvent((row * 2 + col), item));
                            TowerOfGodApplication.getEventBus().fire(new RemoveItemFromShopEvent(item));
                            TowerOfGodApplication.getEventBus().fire(new BuyItemEvent(item));
                            placeItemAt(col, row, item);
                            success = true;
                        }
                    } catch (Exception e) {
                        System.err.println("Active inventory drop failed: " + e.getMessage());
                    }
                }
                if (payload.startsWith("activeInventoryGridPane")){
                    try{
                        if(TowerOfGodApplication.getPlayer() == null){return;}
                        int index = Integer.parseInt(payload.substring("activeInventoryGridPane".length() + 1));
                        Item item = TowerOfGodApplication.getPlayer().getPlayerDummy().getActiveInventory().getItemAtIndex(index);
                        if(item == null){return;}
                        int[] slot = resolveSlot(event.getX(), event.getY());
                        int col = slot[0];
                        int row = slot[1];
                        TowerOfGodApplication.getEventBus().fire(new SwapActiveInventoryItemsEvent(index, (row * 2 + col)));
                    }catch (Exception e){
                        System.err.println("Active inventory drop failed: " + e.getMessage());
                    }
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    // Converts local mouse coordinates into a grid (col, row) cell.
    // Subtracts padding, then divides the content area into equal-sized cells
    // matching the number of column/row constraints defined in the FXML.
    private int[] resolveSlot(double mouseX, double mouseY) {
        Insets padding = activeInventoryGridPane.getPadding();
        double contentWidth  = activeInventoryGridPane.getWidth()  - padding.getLeft() - padding.getRight();
        double contentHeight = activeInventoryGridPane.getHeight() - padding.getTop()  - padding.getBottom();

        int numCols = activeInventoryGridPane.getColumnConstraints().size();
        int numRows = activeInventoryGridPane.getRowConstraints().size();

        int col = (int) ((mouseX - padding.getLeft()) / contentWidth  * numCols);
        int row = (int) ((mouseY - padding.getTop())  / contentHeight * numRows);

        col = Math.max(0, Math.min(numCols - 1, col));
        row = Math.max(0, Math.min(numRows - 1, row));

        System.out.println("col: " + col + ", row: " + row);

        return new int[]{col, row};
    }

    private void placeItemAt(int col, int row, Item item) {
        try {
            Pane newPane = (Pane) SceneLoader.getNode("item-pane.fxml", item, new int[]{row, col});
            activeInventoryGridPane.add(newPane, col, row);
            occupiedSlots[row][col] = true;
        } catch (Exception e) {
            System.err.println("Active inventory drop failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void onActiveInventoryUpdateEvent(ActiveInventoryUpdatedEvent event){
        reRenderGridPane();
    }

    public void reRenderGridPane() {
        try {
            Platform.runLater(()->{
                activeInventoryGridPane.getChildren().clear();
                populateActiveInventoryGridPane();
            });
            System.out.println("Reroll successful");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void populateActiveInventoryGridPane() {
        int iter = 0;
        for(Item item : TowerOfGodApplication.getPlayer().getPlayerDummy().getActiveInventory().getItems()){
            if(item == null){iter++; continue;}
            System.out.println(String.format("Adding item %s", item.toString()));
            try {
                int [] cords = {iter/2, iter%2};
                System.out.println(String.format("Adding to Col: %d, Row: %d.", iter % 2, iter / 2));
                activeInventoryGridPane.add(SceneLoader.getNode("item-pane.fxml", item, cords), iter % 2, iter / 2);
                iter++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
