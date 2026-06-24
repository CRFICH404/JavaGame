package com.cvut.fit.biopj.portniagin.semestralka.events;

public class PlayerMaxHealthChangeEvent implements AppEvent{
    private final int newMaxHP;
    public PlayerMaxHealthChangeEvent(int newMaxHP) {
        this.newMaxHP = newMaxHP;
    }
    public int getNewMaxHP() {return this.newMaxHP;}
}
