package com.cvut.fit.biopj.portniagin.semestralka.eventListeners;

import com.cvut.fit.biopj.portniagin.semestralka.events.*;

public interface StatusEffectActionListener extends GenericEventListener<StatusEffectEvent>{
    public void onEvent(StatusEffectAdditionToItemEvent event);
    public void onEvent(StatusEffectAdditionToPlayerDummyEvent event);
    public void onEvent(StatusEffectTriggerEvent event);
}
