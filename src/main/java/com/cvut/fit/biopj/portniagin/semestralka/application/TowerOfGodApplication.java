package com.cvut.fit.biopj.portniagin.semestralka.application;
import com.cvut.fit.biopj.portniagin.semestralka.controllers.SceneController;
import com.cvut.fit.biopj.portniagin.semestralka.events.SessionEndEvent;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import com.cvut.fit.biopj.portniagin.semestralka.player.Player;
import com.cvut.fit.biopj.portniagin.semestralka.player.User;
import com.cvut.fit.biopj.portniagin.semestralka.session.Session;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TowerOfGodApplication extends Application {

    private final static EventBus eventBus;
    private static final List<Item> items;
    private static User user;
    private static Session session;
    private static Player player;
    private static Player enemyPlayer;
    private static ItemShop shop;


    static {
        try {
            items = ItemLoader.loadItems();
            eventBus = new EventBus();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void start(Stage stage) throws IOException {
        SceneController.setStage(stage);
        stage.setTitle("TowerOfGod");
        stage.setFullScreenExitHint("");
        stage.setFullScreen(GameSettings.isFullscreen());
        stage.setScene(SceneLoader.getScene("log-in-screen.fxml"));
        stage.show();
    }



    public static EventBus getEventBus() {
        return eventBus;
    }
    public static List<Item> getItems() { return items; }
    public static User getUser() { return user; }
    public static void setUser(User newUser){
        user = newUser;
    }
    public static Session getSession() { return session; }
    public static void setSession(Session newSession){ session = newSession; }
    public static Player getPlayer() { return player; }
    public static void setPlayer(Player newPlayer){ player = newPlayer; }
    public static Player getEnemyPlayer () { return enemyPlayer; }
    public static void setEnemyPlayer(Player newEnemyPlayer) {enemyPlayer = newEnemyPlayer;}
    public static void setItemShop(ItemShop itemShop) { shop = itemShop; }
    public static ItemShop getItemShop() { return shop; }
}
