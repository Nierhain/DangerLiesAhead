package de.nierhain.dangerliesahead.capabilities;

public interface ILevel {

    int getLevel();
    int getXP();

    void addLevel(int levels);
    void addXP(int xp);
    void setLevel(int level);
    void setXP(int xp);

}
