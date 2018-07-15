package de.nierhain.danger.enums;

import java.util.HashMap;
import java.util.Map;

public enum Ability {
    HEALTH(0),
    LUCK(1),
    MOVEMENT_SPEED(2),
    ATTACK_DAMAGE(3),
    ATTACK_SPEED(4);

    private int value;
    private static Map map = new HashMap<>();

    private Ability(int value){
        this.value = value;
    }

    static {
        for (Ability ability : Ability.values()){
            map.put(ability.value, ability);
        }
    }

    public static Ability valueOf(int ability){
        return (Ability) map.get(ability);
    }

    public int getValue(){
        return value;
    }
}
