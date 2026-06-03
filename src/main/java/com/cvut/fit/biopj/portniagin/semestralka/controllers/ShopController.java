package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.ItemShop;
import com.cvut.fit.biopj.portniagin.semestralka.application.ResourceFinder;
import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
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

    @Override
    public void initialize(URL url, ResourceBundle rb){
        if(ItemShop.getItems().isEmpty()){ItemShop.populateItemShop();}
        try {
            loadViewToContainer("session-state.fxml", sessionStateHBox);
            populateShopGridPane();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onFightButtonClick(ActionEvent event) throws IOException {
        SceneLoader.setLastScene(SceneController.stage.getScene());
        SceneController.setNewScene(SceneLoader.getScene("fight-scene.fxml"));
    }
    @FXML
    private void onRerollButtonClick(ActionEvent event) throws IOException {
        System.out.println("Reroll button clicked");
        ItemShop.populateItemShop();
        Platform.runLater(()->{
            itemShopGridPane.getChildren().clear();
            populateShopGridPane();
        });
    }
    @FXML
    private void onFreezeButtonClick(ActionEvent event) throws IOException {
        System.out.println("Freeze button clicked");
        ItemShop.switchFrozen();
        System.out.println("Frozen: " + ItemShop.isFrozen());
    }

    @FXML
    private void onLevelUpButtonClick(ActionEvent event) throws IOException {
        System.out.println("Level up button clicked");
    }

    private void populateShopGridPane(){
        int iter = 0;
        for(Item item : ItemShop.getItems()){
            System.out.println(String.format("Adding item %s", item.toString()));
            try {
                VBox vBox = (VBox) SceneLoader.getNode("item-buy-vbox.fxml");
                Pane pane = (Pane) SceneLoader.getNode("item-pane.fxml");

                for(Node node : pane.getChildren()){
                    if(node instanceof ImageView){
                        BufferedImage bufferedImage = ImageIO.read(new File(ResourceFinder.getPathToItemGraphics(item.getItemName())));
                        ((ImageView)node).setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                    }
                }

                vBox.getChildren().add(pane);

                System.out.println(String.format("Adding to Col: %d, Row: %d.", iter % 4, iter / 4));
                itemShopGridPane.add(vBox, iter % 4, iter / 4);
                iter++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
