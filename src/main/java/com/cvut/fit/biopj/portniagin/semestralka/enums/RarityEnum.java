package com.cvut.fit.biopj.portniagin.semestralka.enums;

public enum RarityEnum {
    COMMON(0),
    RARE(1),
    EPIC(2),
    LEGENDARY(3);
    private final int value;
    RarityEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
