package com.cvut.fit.biopj.portniagin.semestralka.enums;

public enum SpecialEffectTriggerConditionEnum {
    NONE("NONE"),
    ONSTARTOFDAY("ON DAY START"),
    ONSTARTOFCOMBAT("ON START OF COMBAT"),
    ONTRIGGER("ON TRIGGER");

    private final String triggerCondition;

    SpecialEffectTriggerConditionEnum(String triggerCondition) {
        this.triggerCondition = triggerCondition;
    }

    public String getTriggerCondition() {
        return triggerCondition;
    }
}
