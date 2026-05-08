package com.cvut.fit.biopj.portniagin.semestralka;

import javafx.stage.Stage;

public class GameSettings {
    private static double width = 1280;
    private static double height = 720;
    private static boolean fullscreen = false;

    public static double getWidth() {
        return width;
    }
    public static void setWidth(double newWidth) {
        width = newWidth;
    }
    public static double getHeight() {
        return height;
    }
    public static void setHeight(double newHeight) {
        height = newHeight;
    }
    public static boolean isFullscreen() {
        return fullscreen;
    }
    public static void setFullscreen(boolean newFullscreen) {
        fullscreen = newFullscreen;
    }
}
