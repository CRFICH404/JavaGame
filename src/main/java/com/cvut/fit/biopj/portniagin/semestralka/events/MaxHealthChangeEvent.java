package com.cvut.fit.biopj.portniagin.semestralka.events;

public class MaxHealthChangeEvent implements AppEvent{
    private final int newMaxHP;
    public MaxHealthChangeEvent(int newMaxHP) {
        this.newMaxHP = newMaxHP;
    }
    public int getNewMaxHP() {return this.newMaxHP;}
}
