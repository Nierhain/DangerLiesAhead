package de.nierhain.danger.capabilities.level;


public class DefaultLevel implements ILevel {

    private int level = 0;
    private int xp = 0;
    private int skillpointsAvailable = 0;
    private int skillpointsMax = 0;

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getXP() {
        return this.xp;
    }

    @Override
    public int getSkillpointsAvailable() {
        return this.skillpointsAvailable;
    }

    @Override
    public int getSkillpointsMax() {
        return this.skillpointsMax;
    }

    @Override
    public void setLevel(int amount) {
        this.skillpointsAvailable = amount;
    }

    @Override
    public void setXP(int amount) {
        this.xp = amount;
    }

    @Override
    public void setSkillpointsAvailable(int amount) {
        this.skillpointsAvailable = amount;
    }

    @Override
    public void setSkillpointsMax(int amount) {
        this.skillpointsMax = amount;
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
    public void addSkillpointsAvailable(int amount) {
        this.skillpointsAvailable += amount;
    }

    @Override
    public void addSkillpointsMax(int amount) {
        this.skillpointsMax += amount;
    }

    @Override
    public void removeSkillpointsAvailable(int amount) {
        this.skillpointsAvailable -= amount;
    }

    public boolean hasSkillpointsAvailable() {
        return skillpointsAvailable > 0;
    }

    @Override
    public void reset(){
        level = 0;
        xp = 0;
        skillpointsAvailable = 0;
        skillpointsMax = 0;
    }
}
