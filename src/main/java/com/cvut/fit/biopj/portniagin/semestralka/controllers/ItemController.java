package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.ResourceFinder;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController extends SceneController implements Initializable {
    @FXML private Pane itemHolderPane;
    @FXML private ImageView itemImageView;

    private final Item item;
    private final int [] coordinatesInGridPane;

    private static Pane dragSourcePane;

    public ItemController(Item item, int [] cords)
    {this.item=item;
    this.coordinatesInGridPane = cords;}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            BufferedImage bufferedImage = ImageIO.read(new File(ResourceFinder.getPathToItemGraphics(item.getItemName())));
            itemImageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setupDragSource();
    }

    private void setupDragSource() {
        itemHolderPane.setOnDragDetected(event -> {
            dragSourcePane = itemHolderPane;
            Dragboard db = itemHolderPane.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            if(itemHolderPane.getParent() instanceof GridPane && itemHolderPane.getParent().getId() != null){
                switch (itemHolderPane.getParent().getId()) {
                    case "activeInventoryGridPane":
                        content.putString("activeInventoryGridPane " + (coordinatesInGridPane[0] * 2 + coordinatesInGridPane[1]));
                        System.out.println("activeInventoryGridPane " + (coordinatesInGridPane[0] * 2 + coordinatesInGridPane[1]));
                        break;
                    case "itemShopGridPane":
                        content.putString("itemShopGridPane " + (coordinatesInGridPane[0] * 4 + coordinatesInGridPane[1]));
                        System.out.println("itemShopGridPane " + (coordinatesInGridPane[0] * 4 + coordinatesInGridPane[1]));
                        break;
                    default:
                        break;
                }
            }
            db.setContent(content);
            db.setDragView(itemHolderPane.snapshot(new SnapshotParameters(), null));
            event.consume();
        });
    }

    public static Pane getDragSourcePane() {
        return dragSourcePane;
    }

    public int[] getCoordinatesInGridPane() {
        return coordinatesInGridPane;
    }
}
