package com.cvut.fit.biopj.portniagin.semestralka.events;

public class EnemyCurrentHealthChangeEvent implements AppEvent{
    private final int currentHP;
    public EnemyCurrentHealthChangeEvent(Integer currentHP){
        this.currentHP = currentHP;
    }
    public int getCurrentHP(){
        return currentHP;
    }
}
