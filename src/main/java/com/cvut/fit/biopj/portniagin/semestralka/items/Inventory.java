package com.cvut.fit.biopj.portniagin.semestralka.items;

import com.cvut.fit.biopj.portniagin.semestralka.player.PlayerDummy;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> items;
    private PlayerDummy inventoryHolder;

    public Inventory(PlayerDummy inventoryHolder) {
        items = new ArrayList<Item>();
        this.inventoryHolder = inventoryHolder;
    }
    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }
    public PlayerDummy getInventoryHolder() {
        return inventoryHolder;
    }
    public List<Item> getItems() {
        return items;
    }
    public void addItem(Item item) {
        items.add(item);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
}
