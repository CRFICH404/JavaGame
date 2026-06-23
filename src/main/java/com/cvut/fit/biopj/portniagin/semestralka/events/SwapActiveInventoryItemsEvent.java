package com.cvut.fit.biopj.portniagin.semestralka.events;

public class SwapActiveInventoryItemsEvent implements AppEvent{
    private final int index1,  index2;
    public SwapActiveInventoryItemsEvent(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
    }
    public int getIndex1() {
        return index1;
    }
    public int getIndex2() {
        return index2;
    }
}
