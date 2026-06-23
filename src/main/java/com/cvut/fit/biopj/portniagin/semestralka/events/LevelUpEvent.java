package com.cvut.fit.biopj.portniagin.semestralka.events;

public class LevelUpEvent implements AppEvent{
    private final int levelUpCost;
    public LevelUpEvent(int levelUpCost) {
        this.levelUpCost = levelUpCost;
    }
    public int getLevelUpCost() {
        return levelUpCost;
    }
}
