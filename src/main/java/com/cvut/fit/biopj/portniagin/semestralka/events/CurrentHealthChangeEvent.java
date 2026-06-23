package com.cvut.fit.biopj.portniagin.semestralka.events;

public class CurrentHealthChangeEvent implements AppEvent{
    private final int newCurrentHP;
    public CurrentHealthChangeEvent (int newCurrentHP){
        this.newCurrentHP = newCurrentHP;
    }
    public int getCurrentHP(){
        return this.newCurrentHP;
    }
}
