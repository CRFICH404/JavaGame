package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.enums.RarityEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectTargetEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectTriggerConditionEnum;
import com.cvut.fit.biopj.portniagin.semestralka.items.Item;
import com.cvut.fit.biopj.portniagin.semestralka.items.ItemSpecialEffect;
import com.cvut.fit.biopj.portniagin.semestralka.statusEffects.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemLoader {
    private final static int totalItems = 52;
    public static List<Item> loadItems() throws IOException {
        int iters = 0;
        List<Item> items = new ArrayList<Item>();
        try(FileInputStream file = new FileInputStream("src/main/resources/com/cvut/fit/biopj/portniagin/semestralka/items.xlsx");
            XSSFWorkbook workbook =  new XSSFWorkbook(file);)
        {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext() && iters <= totalItems) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                //System.out.print("Row" + row.getRowNum() + "\t");

                boolean itemReadSuccess = false;

                int itemId = 999;
                RarityEnum itemRarity = RarityEnum.COMMON;
                int itemCost = 999;
                int itemDamage = 0;
                int itemLevel;
                int itemMulticast = 1;
                float itemCritChance = 0;
                float itemCooldown = 200;
                String itemName = "LOADERROR";
                StatusEffectsList effects = new StatusEffectsList();
                ItemSpecialEffect itemSpecialEffect = new ItemSpecialEffect(SpecialEffectTriggerConditionEnum.NONE, SpecialEffectTargetEnum.NONE, SpecialEffectEnum.NONE, 0);

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //System.out.print("Column" + cell.getColumnIndex() + "\t");

                    //printCell(cell);

                    if(cell.getRowIndex() > 0) {
                        switch (cell.getColumnIndex()) {
                            case 0:
                                itemId = (int) cell.getNumericCellValue();
                                break;
                            case 1:
                                itemName = cell.getStringCellValue();
                                break;
                            case 2:
                                itemCost = (int) cell.getNumericCellValue();
                                break;
                            case 3:
                                itemDamage = (int) cell.getNumericCellValue();
                                break;
                            case 4:
                                itemCooldown = (float) cell.getNumericCellValue();
                                break;
                            case 5:
                                switch (cell.getStringCellValue()) {
                                    case "Common":
                                        itemRarity = RarityEnum.COMMON;
                                        break;
                                    case "Rare":
                                        itemRarity = RarityEnum.RARE;
                                        break;
                                    case "Epic":
                                        itemRarity = RarityEnum.EPIC;
                                        break;
                                    case "Legendary":
                                        itemRarity = RarityEnum.LEGENDARY;
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case 6:
                                itemCritChance = (float) cell.getNumericCellValue();
                                break;
                            case 7:
                                if (cell.getNumericCellValue() > 0) {
                                    StatusEffect shock = new StatusEffectShock((int) cell.getNumericCellValue());
                                    effects.addEffect(shock);
                                }
                                break;
                            case 8:
                                if (cell.getNumericCellValue() > 0) {
                                    StatusEffect burn = new StatusEffectBurn((int) cell.getNumericCellValue());
                                    effects.addEffect(burn);
                                }
                                break;
                            case 9:
                                if (cell.getNumericCellValue() > 0) {
                                    StatusEffect bleed = new StatusEffectBleed((int) cell.getNumericCellValue());
                                    effects.addEffect(bleed);
                                }
                                break;
                            case 10:
                                if (cell.getNumericCellValue() > 0) {
                                    StatusEffect poison = new StatusEffectPoison((int) cell.getNumericCellValue());
                                    effects.addEffect(poison);
                                }
                                break;
                            case 11:
                                if (cell.getNumericCellValue() > 0) {
                                    StatusEffect shield = new StatusEffectShield((int) cell.getNumericCellValue());
                                    effects.addEffect(shield);
                                }
                                break;
                            case 12:
                                if (cell.getNumericCellValue() > 0) {
                                    StatusEffect heal = new StatusEffectHeal((int) cell.getNumericCellValue());
                                    effects.addEffect(heal);
                                }
                                break;
                            case 13:
                                itemMulticast = (int) cell.getNumericCellValue();
                                break;
                            case 14:
                                itemSpecialEffect = ItemSpecialEffectLoader.loadSpecialEffect((int) cell.getNumericCellValue());
                                itemReadSuccess = true;
                                break;
                            default:
                                break;
                        }
                    }
                }
                //System.out.println();
                Item item = new Item(itemId, itemRarity, itemCost, itemDamage, 1, itemMulticast, itemCritChance, itemCooldown, itemName, effects.getEffects());
                item.setItemSpecialEffect(itemSpecialEffect);
                itemSpecialEffect.setEffectHolder(item);
                if(item.getItemID() != 999){items.add(item);}
                iters++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        for(Item item : items) {
            System.out.println(item.toString());
        }
        return items;
    }

    public static void printCell (Cell cell){
        switch (cell.getCellType()) {
            case STRING:
                System.out.print(cell.getStringCellValue() + "\t");
                break;
            case NUMERIC:
                System.out.print(cell.getNumericCellValue() + "\t");
                break;
            default:
                break;
        }
    }
}
