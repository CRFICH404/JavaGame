package com.cvut.fit.biopj.portniagin.semestralka.application;

import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectTargetEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectTriggerConditionEnum;
import com.cvut.fit.biopj.portniagin.semestralka.items.ItemSpecialEffect;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ItemSpecialEffectLoader {

    public static ItemSpecialEffect loadSpecialEffect(int specialEffectID) throws IOException {
        SpecialEffectTriggerConditionEnum triggerCond = SpecialEffectTriggerConditionEnum.NONE;
        SpecialEffectTargetEnum target =  SpecialEffectTargetEnum.NONE;
        SpecialEffectEnum specialEffect  = SpecialEffectEnum.NONE;
        float amount = 0.0F;
        try (
                FileInputStream file = new FileInputStream("src/main/resources/com/cvut/fit/biopj/portniagin/semestralka/itemSpecialEffect.xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook(file);) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == specialEffectID) {
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getColumnIndex()) {
                            case 0:
                                triggerCond = readTriggerCond(cell.getStringCellValue());
                                break;
                            case 1:
                                target = readTriggerTarget(cell.getStringCellValue());
                                break;
                            case 2:
                                specialEffect = readSpecialEffect(cell.getStringCellValue());
                                break;
                            case 3:
                                amount = (float) cell.getNumericCellValue();
                                break;
                            default:
                                break;
                        }
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ItemSpecialEffect(triggerCond, target, specialEffect, amount);
    }

    public static SpecialEffectTriggerConditionEnum readTriggerCond(String triggerCond) {
        switch (triggerCond) {
            case "Trigger":
                return SpecialEffectTriggerConditionEnum.ONTRIGGER;
            case "StartOfCombat":
                return SpecialEffectTriggerConditionEnum.ONSTARTOFCOMBAT;
            case "StartOfDay":
                return SpecialEffectTriggerConditionEnum.ONSTARTOFDAY;
            default:
                return SpecialEffectTriggerConditionEnum.NONE;
        }
    }

    public static SpecialEffectTargetEnum readTriggerTarget(String triggerTarget) {
        switch (triggerTarget) {
            case "Self":
                return SpecialEffectTargetEnum.SELF;
            case "Top":
                return SpecialEffectTargetEnum.ONTOP;
            case "Under":
                return SpecialEffectTargetEnum.UNDER;
            case "Left":
                return SpecialEffectTargetEnum.ONLEFT;
            case "Right":
                return SpecialEffectTargetEnum.ONRIGHT;
            default:
                return SpecialEffectTargetEnum.NONE;
        }
    }

    public static SpecialEffectEnum readSpecialEffect(String specialEffect) {
        switch (specialEffect) {
            case "AddDamage":
                return SpecialEffectEnum.ADDDAMAGE;
            case "AddMulticast":
                return SpecialEffectEnum.ADDMULTICAST;
            case "AddCrit":
                return SpecialEffectEnum.ADDCRITCHANCE;
            case "ReduceCooldown":
                return SpecialEffectEnum.REDUCECOOLDOWN;
            default:
                return SpecialEffectEnum.NONE;
        }
    }
}
