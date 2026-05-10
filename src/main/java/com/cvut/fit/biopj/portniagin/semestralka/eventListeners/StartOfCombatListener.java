package com.cvut.fit.biopj.portniagin.semestralka.eventListeners;

import com.cvut.fit.biopj.portniagin.semestralka.events.StartOfCombatEvent;

public interface StartOfCombatListener extends GenericEventListener<StartOfCombatEvent>{
    void onEvent(StartOfCombatEvent event);
}
