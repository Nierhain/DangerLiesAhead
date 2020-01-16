package de.nierhain.dangerliesahead.enums;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;

public enum Attributes {
    HEALTH (SharedMonsterAttributes.MAX_HEALTH);


    private final IAttribute attr;

    Attributes(IAttribute attr){
        this.attr = attr;
    }
}