package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.controllers.ItemController;
import com.cvut.fit.biopj.portniagin.semestralka.controllers.SceneController;
import com.cvut.fit.biopj.portniagin.semestralka.controllers.ShopBuyController;
import com.cvut.fit.biopj.portniagin.semestralka.controllers.ShopController;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneLoader {
    private static Scene lastScene;
    public static Scene getScene(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(path)));
        return new Scene(fxmlLoader.load(), GameSettings.getWidth(), GameSettings.getHeight());
    }

    public static Scene getScene(String path, SceneController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(path)));
        loader.setController(controller);
        return new Scene(loader.load(), GameSettings.getWidth(), GameSettings.getHeight());
    }
    public static Scene getScene(String path, double width, double height) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(path)));
        return new Scene(fxmlLoader.load(), width, height);
    }
    public static Node getNode (String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(fxmlFile)));
        return (Node) fxmlLoader.load();
    }
    public static Node getNode (String path, Item item, int [] cords) throws IOException {
        FXMLLoader loader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(path)));
        loader.setControllerFactory(type -> new ItemController(item, cords));
        return (Node) loader.load();
    }
    public static Node getNode (String path, int [] cords) throws IOException {
        FXMLLoader loader = new FXMLLoader(TowerOfGodApplication.class.getResource(ResourceFinder.getPathToFXML(path)));
        loader.setControllerFactory(type -> new ShopBuyController(cords));
        return (Node) loader.load();
    }

    public static Scene getLastScene() {
        return lastScene;
    }
    public static void setLastScene(Scene lastScene) {
        SceneLoader.lastScene = lastScene;
    }
}
