package com.cvut.fit.biopj.portniagin.semestralka.controllers;

import com.cvut.fit.biopj.portniagin.semestralka.application.SceneLoader;
import javafx.fxml.Initializable;
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
            case "left": borderPane.setLeft(SceneLoader.getNode(fxmlFile));
            case "right": borderPane.setRight(SceneLoader.getNode(fxmlFile));
            case "center": borderPane.setCenter(SceneLoader.getNode(fxmlFile));
            case "bottom": borderPane.setBottom(SceneLoader.getNode(fxmlFile));
            case "top": borderPane.setTop(SceneLoader.getNode(fxmlFile));
        }
    }
    public void loadViewToContainer(String fxmlFile, GridPane gridPane) throws IOException{
        gridPane.getChildren().add(SceneLoader.getNode(fxmlFile));
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
