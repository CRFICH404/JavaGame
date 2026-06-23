package com.cvut.fit.biopj.portniagin.semestralka.events;

public class BuyItemOnCordsEvent implements AppEvent{
    private final int[] coordinatesInGridPane;
    public BuyItemOnCordsEvent(int[] coordinatesInGridPane) {
        this.coordinatesInGridPane = coordinatesInGridPane;
    }
    public int[] getCoordinatesInGridPane() {
        return coordinatesInGridPane;
    }
}
