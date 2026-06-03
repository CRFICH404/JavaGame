package com.cvut.fit.biopj.portniagin.semestralka.application;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ResourceFinder {

    private static final String pathToFXML = "/com/cvut/fit/biopj/portniagin/semestralka/fxml/";
    private static final String pathToGraphicsFolder = "@../grafics/";
    private static final String pathToItemGraphicsFolder = "src/main/resources/com/cvut/fit/biopj/portniagin/semestralka/grafics/items/";

    @NotNull
    @Contract(pure = true)
    public static String getPathToFXML(String pathTo){
        return pathToFXML + pathTo;
    }
    @NotNull
    @Contract(pure = true)
    public static String getPathToGraphics(String pathTo){
        return pathToGraphicsFolder + pathTo;
    }
    @NotNull
    @Contract(pure = true)
    public static String getPathToItemGraphics(String pathTo){
        System.out.println(pathToItemGraphicsFolder + pathTo + ".webp");
        return pathToItemGraphicsFolder + pathTo + ".webp"; }
}
