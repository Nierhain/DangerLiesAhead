package de.nierhain.dangerliesahead.capabilities.level;


public class DefaultLevel implements ILevel {

    private int level = 0;
    private int xp = 0;

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getXP() {
        return this.xp;
    }


    @Override
    public void setLevel(int amount) {
        this.level = amount;
    }

    @Override
    public void setXP(int amount) {
        this.xp = amount;
    }

    @Override
    public void addLevel(int amount) {
        this.level += amount;
    }

    @Override
    public void addXP(int amount) {
        this.xp += amount;
    }

    @Override
    public void reset(){
        level = 0;
        xp = 0;
    }
}
