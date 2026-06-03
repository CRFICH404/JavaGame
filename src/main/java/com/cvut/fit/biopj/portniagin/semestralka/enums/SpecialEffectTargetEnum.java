package com.cvut.fit.biopj.portniagin.semestralka.enums;

public enum SpecialEffectTargetEnum {
    NONE("NONE"),
    SELF("TO THIS ITEM"),
    ONLEFT("TO ITEM ON THE LEFT"),
    ONRIGHT("TO ITEM ON THE RIGHT"),
    UNDER("TO ITEM UNDER THIS"),
    ONTOP("TO ITEM ON TOP OF THIS");

    private String target;

    SpecialEffectTargetEnum(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }
}
