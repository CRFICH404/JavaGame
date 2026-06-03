package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemShop {
    private final static int maxItems = 8;
    private static List<Item> items = new ArrayList<>();
    private static boolean isFrozen = false;

    static{
        populateItemShop();
    }

    public static void freeShopItems(){
        items = new ArrayList<>();
    }
    public static void addItem(Item item){
        items.add(item);
    }
    public static void removeItem(Item item){
        items.remove(item);
    }
    public static void removeItemAtIndex(int index){
        items.remove(index);
    }
    public static Item getItemAtIndex(int index){
        return items.get(index);
    }
    public static void populateItemShop(){
        if(!items.isEmpty()){
            ItemShop.freeShopItems();
        }
        List<Item> allItems = TowerOfGodApplication.getItems();
        for(int i = 0; i < maxItems; i++){
            Random rand = new Random();
            items.add(allItems.get(rand.nextInt(allItems.size())));
        }
        System.out.println("Items in Shop");
        for(Item item : items){
            System.out.println(item.toString());
        }
    }
    public  static List<Item> getItems(){
        return items;
    }

    public static boolean isFrozen(){return isFrozen;}

    public static void switchFrozen(){
        isFrozen = !isFrozen;
    }

    public static void setFreezeTrue(){
        isFrozen = true;
    }
    public static void setFreezeFalse(){
        isFrozen = false;
    }
}
