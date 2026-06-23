package com.cvut.fit.biopj.portniagin.semestralka.player;

import com.cvut.fit.biopj.portniagin.semestralka.application.TowerOfGodApplication;
import com.cvut.fit.biopj.portniagin.semestralka.events.ActiveInventoryUpdatedEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.SwapActiveInventoryItemsEvent;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

import java.util.Arrays;

public class ActiveInventory {
    private final int MAX_ITEMS = 6;
    private final Item [] items = new Item [MAX_ITEMS];
    private PlayerDummy holder;

    public ActiveInventory() {
        TowerOfGodApplication.getEventBus().addListener(SwapActiveInventoryItemsEvent.class, this::onSwapItemsEvent);
    }

    public ActiveInventory(PlayerDummy holder){
        this.holder = holder;
        TowerOfGodApplication.getEventBus().addListener(SwapActiveInventoryItemsEvent.class, this::onSwapItemsEvent);
    }

    private void onSwapItemsEvent(SwapActiveInventoryItemsEvent swapActiveInventoryItemsEvent) {
        swapActiveInventoryItems(swapActiveInventoryItemsEvent.getIndex1(), swapActiveInventoryItemsEvent.getIndex2());
    }

    public void freeShopItems(){
        Arrays.fill(items, null);
        TowerOfGodApplication.getEventBus().fire(new ActiveInventoryUpdatedEvent());
    }
    public boolean addItem(int index, Item item){
        if(index >= MAX_ITEMS ||  index < 0){
            return false;
        }
        if(items[index] != null){
            System.out.println(String.format("Removing item %s", item.toString()));
            return false;
        }
        items[index] = item;
        TowerOfGodApplication.getEventBus().fire(new ActiveInventoryUpdatedEvent());
        return true;
    }
    public void removeItem(Item item){
        for(int i = 0; i < MAX_ITEMS; i++){
            if(items[i].equals(item)){
                items[i] = null;
                TowerOfGodApplication.getEventBus().fire(new ActiveInventoryUpdatedEvent());
                return;
            }
        }
    }
    public void removeItemAtIndex(int index){
        if(index >= 0 && index < MAX_ITEMS){
            items[index] = null;
            TowerOfGodApplication.getEventBus().fire(new ActiveInventoryUpdatedEvent());
        }
    }
    public Item getItemAtIndex(int index){
        if(index >= 0 && index < MAX_ITEMS){
            return items[index];
        }
        return null;
    }
    public boolean isEmpty(){
        for(int i = 0; i < MAX_ITEMS; i++){
            if(items[i] != null){
                return false;
            }
        }
        return true;
    }
    public Item [] getItems(){
        return items;
    }
    public PlayerDummy getHolder(){return holder;}

    public int getMAX_ITEMS() {
        return MAX_ITEMS;
    }

    public void setHolder(PlayerDummy holder) {
        this.holder = holder;
    }

    public void swapActiveInventoryItems(int index1, int index2){
        Item temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
        TowerOfGodApplication.getEventBus().fire(new ActiveInventoryUpdatedEvent());
    }
}
