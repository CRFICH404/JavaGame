package com.cvut.fit.biopj.portniagin.semestralka;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneLoader {

    public static Scene getScene(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(path)));
        return new Scene(fxmlLoader.load(), GameSettings.getWidth(), GameSettings.getHeight());
    }
    public static Scene getScene(String path, double width, double height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(path)));
        return new Scene(fxmlLoader.load(), width, height);
    }
    public static Node getNode (String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(fxmlFile)));
        return (Node) fxmlLoader.load();
    }

}
