package com.cvut.fit.biopj.portniagin.semestralka.items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        items = new ArrayList<Item>();
    }
    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public void addItem(Item item) {
        items.add(item);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
}
