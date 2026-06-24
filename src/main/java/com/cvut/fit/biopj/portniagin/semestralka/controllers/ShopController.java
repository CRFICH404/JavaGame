package com.cvut.fit.biopj.portniagin.semestralka.controllers;

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
        TowerOfGodApplication.setEnemyPlayer(new Player(1337, "test-enemy321", false));
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
        int iter = 0;
        for(Item item : TowerOfGodApplication.getItemShop().getItems()){
            if(item == null){iter++; continue;}
            System.out.println(String.format("Adding item %s", item.toString()));
            try {
                int [] cords = {iter/4, iter%4};
                System.out.println(String.format("Adding to Col: %d, Row: %d.", iter % 4, iter / 4));
                itemShopGridPane.add(SceneLoader.getNode("item-pane.fxml", () -> new ItemController(item, cords)), iter % 4, iter / 4);
                iter++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
