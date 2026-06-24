package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {
    public static Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void loadViewToContainer(String fxmlFile, HBox hBox) throws IOException {
        hBox.getChildren().add(SceneLoader.getNode(fxmlFile));
    }

    public void loadViewToContainer(String fxmlFile, VBox vBox) throws IOException{
        vBox.getChildren().add(SceneLoader.getNode(fxmlFile));
    }
    public void loadViewToContainer(String fxmlFile, BorderPane borderPane, String part) throws IOException{
        switch (part){
            case "left":   borderPane.setLeft(SceneLoader.getNode(fxmlFile));    break;
            case "right":  borderPane.setRight(SceneLoader.getNode(fxmlFile));   break;
            case "center": borderPane.setCenter(SceneLoader.getNode(fxmlFile));  break;
            case "bottom": borderPane.setBottom(SceneLoader.getNode(fxmlFile));  break;
            case "top":    borderPane.setTop(SceneLoader.getNode(fxmlFile));     break;
        }
    }
    public void loadViewToContainer(String fxmlFile, GridPane gridPane, int col, int row) throws IOException{
        gridPane.add(SceneLoader.getNode(fxmlFile), col, row);
    }
    public void loadViewToContainer(Node nodeToload, Node nodeToLoadInto){
        if(nodeToLoadInto instanceof VBox){
            ((VBox) nodeToLoadInto).getChildren().add(nodeToload);
        }
        if(nodeToLoadInto instanceof HBox){
            ((HBox) nodeToLoadInto).getChildren().add(nodeToLoadInto);
        }
    }
    public void loadViewToContainer(Node nodeToload, Node nodeToLoadInto, int col, int row) throws IOException{
        if(nodeToLoadInto instanceof VBox || nodeToLoadInto instanceof HBox){
            loadViewToContainer(nodeToload, nodeToLoadInto);
        }
        if(nodeToLoadInto instanceof GridPane){
            ((GridPane) nodeToLoadInto).add(nodeToLoadInto, col, row);
        }
    }
    public static void setNewScene(Button button, Scene newScene){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(newScene);
    }

    public static void setNewScene(Scene newScene){
        SceneController.stage.setScene(newScene);
    }

    public static void setStage(Stage stage){
        SceneController.stage = stage;
    }


}
