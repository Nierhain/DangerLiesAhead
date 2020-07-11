package de.nierhain.dangerliesahead.capabilities.level;


public class DefaultLevel implements ILevel {

    private int level = 1;
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
        level = 1;
        xp = 0;
    }
}
