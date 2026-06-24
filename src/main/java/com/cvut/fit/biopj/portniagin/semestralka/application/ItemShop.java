package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.eventListeners.GenericEventListener;
import com.cvut.fit.biopj.portniagin.semestralka.eventListeners.RerollEventListener;
import com.cvut.fit.biopj.portniagin.semestralka.events.BuyItemEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.ItemShopUpdatedEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.RemoveItemFromShopEvent;
import com.cvut.fit.biopj.portniagin.semestralka.events.RerollEvent;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemShop {
    private final int MAX_ITEMS = 8;
    private final Item [] items = new Item [MAX_ITEMS];
    private boolean isFrozen = false;

    public ItemShop() {
        populateItemShop();
        TowerOfGodApplication.getEventBus().addListener(RerollEvent.class, this::onRerollEvent);
        TowerOfGodApplication.getEventBus().addListener(BuyItemEvent.class, this::onBuyItemEvent);
    }

    public void freeShopItems(){
        Arrays.fill(items, null);
        TowerOfGodApplication.getEventBus().fire(new ItemShopUpdatedEvent());
    }
    public void addItem(Item item){

    }
    public void removeItem(Item item){
        for(int i = 0; i < MAX_ITEMS; i++){
            if(items[i] == null){continue;}
            if(items[i].equals(item)){
                items[i] = null;
                TowerOfGodApplication.getEventBus().fire(new ItemShopUpdatedEvent());
                return;
            }
        }
    }
    public void removeItemAtIndex(int index){
        if(index >= 0 && index < MAX_ITEMS){
            items[index] = null;
            TowerOfGodApplication.getEventBus().fire(new ItemShopUpdatedEvent());
        }
    }
    public Item getItemAtIndex(int index){
        if(index >= 0 && index < MAX_ITEMS){
            return items[index];
        }
        return null;
    }
    public void populateItemShop(){
        List<Item> allItems = TowerOfGodApplication.getItems();
        for(int i = 0; i < MAX_ITEMS; i++){
            if(items[i] == null){
                Random rand = new Random();
                items[i] = allItems.get(rand.nextInt(allItems.size()));
            }
        }
        System.out.println("Items in Shop");
        for(Item item : items){
            System.out.println(item.toString());
        }
        TowerOfGodApplication.getEventBus().fire(new ItemShopUpdatedEvent());
    }

    public void onRerollEvent(RerollEvent event){
        repopulateItemShop();
    }

    public void repopulateItemShop(){
        freeShopItems();
        populateItemShop();
    }
    public void onBuyItemEvent(BuyItemEvent event){
        removeItem(event.getItem());
    }
    public Item [] getItems(){
        return items;
    }
    public boolean isEmpty(){
        for(int i = 0; i < MAX_ITEMS; i++){
            if(items[i] != null){
                return false;
            }
        }
        return true;
    }
    public boolean isFrozen(){return isFrozen;}
    public void switchFrozen(){
        isFrozen = !isFrozen;
    }
    public void setFreezeTrue(){
        isFrozen = true;
    }
    public void setFreezeFalse(){
        isFrozen = false;
    }
}
