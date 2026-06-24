package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.eventListeners.GenericEventListener;
import com.cvut.fit.biopj.portniagin.semestralka.events.AppEvent;
import javafx.application.Platform;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<?>, List<Consumer<AppEvent>>> listeners = new ConcurrentHashMap<>();

    public <T extends AppEvent> Runnable addListener(Class<T> type, GenericEventListener<T> listener) {
        Consumer<AppEvent> wrapper = event -> listener.onEvent(type.cast(event));
        listeners.computeIfAbsent(type, k -> new CopyOnWriteArrayList<>()).add(wrapper);
        return () -> listeners.getOrDefault(type, new CopyOnWriteArrayList<>()).remove(wrapper);
    }

    public void fire(AppEvent event) {
        List<Consumer<AppEvent>> handlers = listeners.get(event.getClass());
        if (handlers == null) return;
        if (Platform.isFxApplicationThread()) {
            handlers.forEach(h -> h.accept(event));
        } else {
            Platform.runLater(() -> handlers.forEach(h -> h.accept(event)));
        }
    }

    private void notifyListeners(Class<?> type, AppEvent event) {
        List<Consumer<AppEvent>> handlers = listeners.get(type);
        if (handlers != null) {
            handlers.forEach(h -> h.accept(event));
        }
    }
}
