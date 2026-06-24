package com.cvut.fit.biopj.portniagin.semestralka.events;

public class PlayerCurrentHealthChangeEvent implements AppEvent{
    private final int newCurrentHP;
    public PlayerCurrentHealthChangeEvent(int newCurrentHP){
        this.newCurrentHP = newCurrentHP;
    }
    public int getCurrentHP(){
        return this.newCurrentHP;
    }
}
