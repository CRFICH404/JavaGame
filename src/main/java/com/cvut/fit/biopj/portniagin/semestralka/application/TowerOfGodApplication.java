package com.cvut.fit.biopj.portniagin.semestralka.application;
import com.cvut.fit.biopj.portniagin.semestralka.controllers.SceneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TowerOfGodApplication extends Application {
    private final static EventBus eventBus =  new EventBus();
    @Override
    public void start(Stage stage) throws IOException {
        SceneController.setStage(stage);
        stage.setTitle("TowerOfGod");
        stage.setFullScreenExitHint("");
        stage.setFullScreen(GameSettings.isFullscreen());
        stage.setScene(SceneLoader.getScene("main-menu.fxml"));
        stage.show();
    }
    public static EventBus getEventBus() {
        return eventBus;
    }
}
