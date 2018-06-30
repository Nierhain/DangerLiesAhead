package de.nierhain.danger.capabilities.level;


public class DefaultLevel implements ILevel {

    private int level;
    private int xp;
    private int skillpointsAvailable;
    private int skillpointsMax;

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
        this.setSkillpointsAvailable(amount);
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
}
