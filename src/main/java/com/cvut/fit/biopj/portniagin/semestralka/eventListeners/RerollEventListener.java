package com.cvut.fit.biopj.portniagin.semestralka.eventListeners;

import com.cvut.fit.biopj.portniagin.semestralka.events.RerollEvent;

public interface RerollEventListener extends GenericEventListener<RerollEvent> {
    void onEvent(RerollEvent event);
}
