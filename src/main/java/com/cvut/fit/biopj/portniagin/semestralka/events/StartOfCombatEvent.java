package com.cvut.fit.biopj.portniagin.semestralka.events;

import java.util.ArrayList;

public class StartOfCombatEvent implements AppEvent{
    private final boolean isCombat;
    public StartOfCombatEvent(boolean isCombat) {
        this.isCombat = isCombat;
    }
    public boolean isCombat() {
        return isCombat;
    }
}
