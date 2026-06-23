package com.cvut.fit.biopj.portniagin.semestralka.events;

public class StartOfDayEvent implements AppEvent{
    private final int daysPassed;
    public StartOfDayEvent(){
        this.daysPassed = 1;
    }
    public int getDaysPassed() {
        return daysPassed;
    }
}
