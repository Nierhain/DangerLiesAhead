package de.nierhain.dangerliesahead.enums;

import java.util.HashMap;
import java.util.Map;

public enum Attribute {
    HEALTH(0),
    LUCK(1),
    MOVEMENT_SPEED(2),
    ATTACK_DAMAGE(3),
    ATTACK_SPEED(4);

    private int value;
    private static Map map = new HashMap<>();

    Attribute(int value){
        this.value = value;
    }

    static {
        for (Attribute attribute : Attribute.values()){
            map.put(attribute.value, attribute);
        }
    }

    public static Attribute valueOf(int ability){
        return (Attribute) map.get(ability);
    }

    public int getValue(){
        return value;
    }
}
