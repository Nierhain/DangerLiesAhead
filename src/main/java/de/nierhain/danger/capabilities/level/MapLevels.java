package de.nierhain.danger.capabilities.level;

import java.util.HashMap;

public class MapLevels {

    private static final int MAX_LVL = 20;
    private static final double constA = 1;
    private static final double constB = 1;

    private static HashMap<Integer, Integer> map = new HashMap<>();

    {
        for(int i = 1; i < MAX_LVL; i++) map.put(i, (int) Math.ceil(constA * i * i + constB * i));
    }

    public static int getNeededXP(int level){
       return map.get(level);
    }

    public static boolean isLevelUp(int level, int xp){
        return map.get(level) <= xp;
    }

}
