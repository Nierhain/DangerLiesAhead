package de.nierhain.dangerliesahead.capabilities.level;

public interface ILevel {

    public int getLevel();
    public void setLevel(int amount);
    public void addLevel(int amount);

    public int getXP();
    public void setXP(int amount);
    public void addXP(int amount);

    public void reset();


}
