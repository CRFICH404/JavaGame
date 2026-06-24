package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.controllers.ItemController;
import com.cvut.fit.biopj.portniagin.semestralka.controllers.SceneController;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.function.Supplier;

public class SceneLoader {
    private static Scene lastScene;
    public static Scene getScene(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(fxmlFile)));
        return new Scene(fxmlLoader.load(), GameSettings.getWidth(), GameSettings.getHeight());
    }

    public static Scene getScene(String fxmlFile, SceneController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(fxmlFile)));
        loader.setController(controller);
        return new Scene(loader.load(), GameSettings.getWidth(), GameSettings.getHeight());
    }
    public static Scene getScene(String fxmlFile, double width, double height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(fxmlFile)));
        return new Scene(fxmlLoader.load(), width, height);
    }
    public static Node getNode (String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(fxmlFile)));
        return (Node) fxmlLoader.load();
    }
    public static Node getNode(String fxmlFile, Item item, int[] cords) throws IOException {
        return getNode(fxmlFile, () -> new ItemController(item, cords));
    }

    public static Node getNode(String fxmlFile, Supplier<? extends SceneController> factory) throws IOException {
        FXMLLoader loader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(fxmlFile)));
        loader.setControllerFactory(type -> factory.get());
        return (Node) loader.load();
    }

    public static Scene getLastScene() {
        return lastScene;
    }
    public static void setLastScene(Scene lastScene) {
        SceneLoader.lastScene = lastScene;
    }
}
