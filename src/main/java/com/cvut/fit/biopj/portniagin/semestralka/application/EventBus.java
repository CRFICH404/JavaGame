package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.eventListeners.GenericEventListener;
import com.cvut.fit.biopj.portniagin.semestralka.events.AppEvent;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<?>, List<Consumer<AppEvent>>> listeners = new ConcurrentHashMap<>();

    public <T extends AppEvent> void addListener(Class<T> type, GenericEventListener<T> listener) {
        listeners
                .computeIfAbsent(type, k -> new CopyOnWriteArrayList<>())
                .add(event -> listener.onEvent(type.cast(event)));
    }

    public void fire(AppEvent event) {
        List<Consumer<AppEvent>> handlers = listeners.get(event.getClass());
        if (handlers != null) {
            handlers.forEach(h -> h.accept(event));
        }
    }

    private void notifyListeners(Class<?> type, AppEvent event) {
        List<Consumer<AppEvent>> handlers = listeners.get(type);
        if (handlers != null) {
            handlers.forEach(h -> h.accept(event));
        }
    }
}
