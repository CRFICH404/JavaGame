package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.events.ItemCooldownChangedEvent;
import com.cvut.fit.biopj.portniagin.semestralka.items.Inventory;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

import java.util.Map;
import java.util.concurrent.*;

public class FightScheduler {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final Map<Item, ScheduledFuture<?>> taskMap = new ConcurrentHashMap<>();

    public void start(Inventory inventory) {
        for (Item item : inventory.getItems()) {
            schedule(item);
        }
        TowerOfGodApplication.getEventBus().addListener(
                ItemCooldownChangedEvent.class,
                event -> reschedule(event.getSource())
        );
    }

    public void stop() {
        taskMap.values().forEach(f -> f.cancel(false));
        taskMap.clear();
        executor.shutdown();
    }

    private void schedule(Item item) {
        long delayMillis = (long) (item.getItemCooldown() * 1000);
        ScheduledFuture<?> future = executor.scheduleWithFixedDelay(
                () -> {
                    try {
                        item.run();
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                },
                delayMillis,
                delayMillis,
                TimeUnit.MILLISECONDS
        );
        taskMap.put(item, future);
    }

    private void reschedule(Item item) {
        ScheduledFuture<?> existing = taskMap.get(item);
        if (existing != null) {
            existing.cancel(false);
        }
        schedule(item);
    }
}
