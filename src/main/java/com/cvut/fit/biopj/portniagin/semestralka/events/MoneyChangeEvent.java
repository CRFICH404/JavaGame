package com.cvut.fit.biopj.portniagin.semestralka.events;

public class MoneyChangeEvent implements AppEvent{
    private final int newAmount;
    public MoneyChangeEvent(int newAmount){
        this.newAmount = newAmount;
    }
    public int getNewAmount(){return this.newAmount;}
}
