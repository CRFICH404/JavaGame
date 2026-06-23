package com.cvut.fit.biopj.portniagin.semestralka.events;

public class RerollEvent implements AppEvent{
    private final int rerollCost;
    public RerollEvent() {
        this.rerollCost = 0;
    }
    public RerollEvent(int rerollCost) {
        this.rerollCost = rerollCost;
    }
    public int getRerollCost() {
        return this.rerollCost;
    }
}
