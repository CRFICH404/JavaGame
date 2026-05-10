package com.cvut.fit.biopj.portniagin.semestralka.eventListeners;

import com.cvut.fit.biopj.portniagin.semestralka.events.AppEvent;

public interface GenericEventListener<T extends AppEvent> {
    void onEvent(T event);
}
