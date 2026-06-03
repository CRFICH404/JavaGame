package com.cvut.fit.biopj.portniagin.semestralka.items;

import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectTargetEnum;
import com.cvut.fit.biopj.portniagin.semestralka.enums.SpecialEffectTriggerConditionEnum;

public class ItemSpecialEffect {
    private final SpecialEffectTriggerConditionEnum triggerCondition;
    private final SpecialEffectTargetEnum target;
    private final SpecialEffectEnum effect;
    private Item effectHolder;
    private float amount;

    public ItemSpecialEffect(SpecialEffectTriggerConditionEnum triggerCondition, SpecialEffectTargetEnum target, SpecialEffectEnum effect, float amount) {
        this.triggerCondition = triggerCondition;
        this.target = target;
        this.effect = effect;
        this.amount = amount;
    }

    public SpecialEffectTriggerConditionEnum getTriggerCondition() {
        return triggerCondition;
    }
    public SpecialEffectTargetEnum getTarget() {
        return target;
    }
    public SpecialEffectEnum getEffect() {
        return effect;
    }
    public Item getEffectHolder() {
        return effectHolder;
    }
    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setEffectHolder(Item effectHolder) {
        this.effectHolder = effectHolder;
    }
    @Override
    public String toString() {
        return String.format("%s %s %s %.1f", triggerCondition.getTriggerCondition(), target.getTarget(), effect.getSpecialEffect(), amount);
    }
}
