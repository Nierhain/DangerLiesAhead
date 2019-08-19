package de.nierhain.danger.capabilities.level;

import java.util.HashMap;


import static de.nierhain.danger.config.ConfigHandler.PLAYER_MAX_LVL;


public class MapLevels {

    private static final double CONST_A = 5;
    private static final double CONST_B = 10;
    static final HashMap<Integer, Integer> map;

    static {
        map = new HashMap<>();
        for(int i = 1; i <= PLAYER_MAX_LVL; i++) map.put(i, (int) Math.ceil(CONST_A * (i * i) + (CONST_B * i)));
    }

    public static int getNeededXP(int level){
       return map.get(level);
    }

    public static boolean isLevelUp(int level, int xp){
        return map.get(level) <= xp;
    }

}
