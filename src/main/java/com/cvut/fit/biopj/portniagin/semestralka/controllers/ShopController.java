package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBLoader;
import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBUploader;
import com.cvut.fit.biopj.portniagin.semestralka.DBHandlers.DBconnector;
import com.cvut.fit.biopj.portniagin.semestralka.application.ItemShop;
import com.cvut.fit.biopj.portniagin.semestralka.application.ResourceFinder;
import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.ItemShopUpdatedEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.LevelUpEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.RemoveItemFromShopEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.RerollEvent;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import com.cvut.fit.biopj.portniagin.semestralka.player.Player;
import com.cvut.fit.biopj.portniagin.semestralka.session.DaySnapshot;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShopController extends SceneController implements Initializable {
    @FXML HBox sessionStateHBox;
    @FXML GridPane itemShopGridPane;
    @FXML public Button fightButton;
    @FXML public Button rerollButton;
    @FXML public Button freezeButton;
    @FXML public Button levelUpButton;

    private int levelUpCost = 30;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        TowerOfGodApplication.getItemShop().repopulateItemShop();
        TowerOfGodApplication.getEventBus().addListener(RerollEvent.class, this::onRerollEvent);
        TowerOfGodApplication.getEventBus().addListener(ItemShopUpdatedEvent.class, this::onItemShopUpdatedEvent);
        try {
            loadViewToContainer("session-state.fxml", sessionStateHBox);
            populateShopGridPane();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onFightButtonClick(ActionEvent event) throws IOException {
        TowerOfGodApplication.setEnemyPlayer(null);
        try{
            DBconnector connector = new DBconnector();
            DBLoader loader = new DBLoader(connector);
            DaySnapshot snapshot = loader.loadRandomDaySnapshotForDay(TowerOfGodApplication.getSession().getDay());
            if(snapshot == null || snapshot.getPlayer() == null){
                TowerOfGodApplication.setEnemyPlayer(null);
            }else{
                TowerOfGodApplication.setEnemyPlayer(snapshot.getPlayer());
            }
            connector.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        catch (Exception e){
            throw new IOException();
        }
        if(TowerOfGodApplication.getEnemyPlayer() == null){TowerOfGodApplication.setEnemyPlayer(new Player(1337, "test-enemy321", false));}
        try{
            DBconnector connector = new DBconnector();
            DBUploader uploader = new DBUploader(connector);
            uploader.saveDaySnapshot(TowerOfGodApplication.getUser().getUserID(), new DaySnapshot(TowerOfGodApplication.getSession(), TowerOfGodApplication.getPlayer()));
            connector.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch (Exception e){
            throw new IOException();
        }
        SceneLoader.setLastScene(SceneController.stage.getScene());
        SceneController.setNewScene(SceneLoader.getScene("fight-scene.fxml"));
    }
    @FXML
    private void onRerollButtonClick(ActionEvent event) throws IOException {
        int rerollCost = 3;
        if(TowerOfGodApplication.getPlayer().getPlayerDummy().getPlayerMoney() >= rerollCost){
            TowerOfGodApplication.getEventBus().fire(new RerollEvent(rerollCost));
        }
        else{
            System.out.println("Not enough money for reroll");
        }
    }
    @FXML
    private void onFreezeButtonClick(ActionEvent event) throws IOException {
        System.out.println("Freeze button clicked");
        TowerOfGodApplication.getItemShop().switchFrozen();
        System.out.println("Frozen: " + TowerOfGodApplication.getItemShop().isFrozen());
    }

    @FXML
    private void onLevelUpButtonClick(ActionEvent event) throws IOException {
        if(TowerOfGodApplication.getPlayer().getPlayerDummy().getPlayerMoney() >= levelUpCost){
            TowerOfGodApplication.getEventBus().fire(new LevelUpEvent(levelUpCost));
            levelUpCost += ((TowerOfGodApplication.getPlayer().getPlayerDummy().getPlayerLVL()) * levelUpCost);
            System.out.println("Level up successful");
        }
        else {
            System.out.println("Not enough money for level up");
        }
    }

    public void onItemShopUpdatedEvent(ItemShopUpdatedEvent event) {
        Platform.runLater(() -> {
            itemShopGridPane.getChildren().clear();
            populateShopGridPane();
        });
    }

    public void onRerollEvent(RerollEvent event) {
        try {
            Platform.runLater(()->{
                itemShopGridPane.getChildren().clear();
                populateShopGridPane();
            });
            System.out.println("Reroll successful");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private void populateShopGridPane(){
        Item[] items = TowerOfGodApplication.getItemShop().getItems();
        for (int i = 0; i < items.length; i++) {
            int col = i % 4;
            int row = i / 4;
            if (items[i] == null) {
                javafx.scene.layout.Pane empty = new javafx.scene.layout.Pane();
                empty.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                empty.setStyle("-fx-background-color: #120a02; -fx-border-color: #6b3a15; -fx-border-width: 1;");
                itemShopGridPane.add(empty, col, row);
            } else {
                final Item item = items[i];
                final int[] cords = {row, col};
                try {
                    javafx.scene.Node node = SceneLoader.getNode("shop-item-pane.fxml", () -> new ItemController(item, cords));
                    GridPane.setFillWidth(node, true);
                    GridPane.setFillHeight(node, true);
                    itemShopGridPane.add(node, col, row);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
