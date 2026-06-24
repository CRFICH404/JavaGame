package com.cvut.fit.biopj.portniagin.semestralka.events;

public class EnemyMaxHealthChangeEvent implements AppEvent{
    private final int newMaxHealth;
    public EnemyMaxHealthChangeEvent(int newMaxHealth){
        this.newMaxHealth=newMaxHealth;
    }
    public int getNewMaxHP(){
        return newMaxHealth;
    }
}
