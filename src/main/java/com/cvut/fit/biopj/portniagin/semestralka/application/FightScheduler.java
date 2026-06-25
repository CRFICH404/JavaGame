package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.events.DamageEnemyEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.DamagePlayerEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.ItemCooldownChangedEvent;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import com.cvut.fit.biopj.portniagin.semestralka.player.Player;
import javafx.application.Platform;

import java.util.Map;
import java.util.concurrent.*;

public class FightScheduler {

    private final ScheduledExecutorService playerExecutor = Executors.newScheduledThreadPool(1);
    private final ScheduledExecutorService enemyExecutor  = Executors.newScheduledThreadPool(1);

    private final Map<Item, ScheduledFuture<?>> playerTaskMap = new ConcurrentHashMap<>();
    private final Map<Item, ScheduledFuture<?>> enemyTaskMap  = new ConcurrentHashMap<>();
    private final Map<Item, Runnable>           actionMap     = new ConcurrentHashMap<>();
    private Runnable deregisterCooldownListener;

    public void start(Player player, Player enemyPlayer) {
        if(player != null) {
            for (Item item : player.getPlayerDummy().getActiveInventory().getItems()) {
                if(item == null){continue;}
                schedulePlayerItem(item, () -> Platform.runLater(() ->
                        TowerOfGodApplication.getEventBus().fire(new DamageEnemyEvent(item))));
            }
        }
        if(enemyPlayer != null) {
            for (Item item : enemyPlayer.getPlayerDummy().getActiveInventory().getItems()) {
                if(item == null){continue;}
                scheduleEnemyItem(item, () -> Platform.runLater(() ->
                        TowerOfGodApplication.getEventBus().fire(new DamagePlayerEvent(item))));
            }
        }
        deregisterCooldownListener = TowerOfGodApplication.getEventBus().addListener(
                ItemCooldownChangedEvent.class,
                event -> reschedule(event.getSource())
        );
    }

    public void stop() {
        playerTaskMap.values().forEach(f -> f.cancel(false));
        enemyTaskMap.values().forEach(f -> f.cancel(false));
        playerTaskMap.clear();
        enemyTaskMap.clear();
        actionMap.clear();
        if (deregisterCooldownListener != null) {
            deregisterCooldownListener.run();
            deregisterCooldownListener = null;
        }
        playerExecutor.shutdown();
        enemyExecutor.shutdown();
    }

    public void stopPlayer() {
        playerTaskMap.values().forEach(f -> f.cancel(false));
        playerTaskMap.clear();
        playerExecutor.shutdown();
    }

    public void stopEnemy() {
        enemyTaskMap.values().forEach(f -> f.cancel(false));
        enemyTaskMap.clear();
        enemyExecutor.shutdown();
    }

    public void schedulePlayerItem(Item item, Runnable action) {
        long delayMillis = (long) (item.getItemCooldown() * 100);
        ScheduledFuture<?> future = playerExecutor.scheduleWithFixedDelay(
                () -> {
                    try {
                        action.run();
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                },
                delayMillis,
                delayMillis,
                TimeUnit.MILLISECONDS
        );
        playerTaskMap.put(item, future);
        actionMap.put(item, action);
    }

    public void scheduleEnemyItem(Item item, Runnable action) {
        long delayMillis = (long) (item.getItemCooldown() * 100);
        ScheduledFuture<?> future = enemyExecutor.scheduleWithFixedDelay(
                () -> {
                    try {
                        action.run();
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                },
                delayMillis,
                delayMillis,
                TimeUnit.MILLISECONDS
        );
        enemyTaskMap.put(item, future);
        actionMap.put(item, action);
    }

    private void reschedule(Item item) {
        Runnable action = actionMap.get(item);
        if (action == null) return;

        if (playerTaskMap.containsKey(item)) {
            playerTaskMap.get(item).cancel(false);
            playerTaskMap.remove(item);
            schedulePlayerItem(item, action);
        } else if (enemyTaskMap.containsKey(item)) {
            enemyTaskMap.get(item).cancel(false);
            enemyTaskMap.remove(item);
            scheduleEnemyItem(item, action);
        }
    }
}
