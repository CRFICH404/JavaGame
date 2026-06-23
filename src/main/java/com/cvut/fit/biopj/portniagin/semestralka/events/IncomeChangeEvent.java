package com.cvut.fit.biopj.portniagin.semestralka.events;

public class IncomeChangeEvent implements AppEvent{
    private final int newIncome;
    public IncomeChangeEvent(int newIncome) {
        this.newIncome = newIncome;
    }
    public int getNewIncome() {return this.newIncome;}
}
