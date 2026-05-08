package com.cvut.fit.biopj.portniagin.semestralka;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ResourceFinder {

    private static final String pathToFXML = "/com/cvut/fit/biopj/portniagin/semestralka/fxml/";
    private static final String pathToGraphicsFolder = "@../grafics/";

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
}
