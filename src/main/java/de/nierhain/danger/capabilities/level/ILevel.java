package de.nierhain.danger.capabilities.level;

public interface ILevel {

    public int getLevel();
    public int getXP();
    public int getSkillpointsAvailable();
    public int getSkillpointsMax();

    public void setLevel(int amount);
    public void setXP(int amount);
    public void setSkillpointsAvailable(int amount);
    public void setSkillpointsMax(int amount);

    public void addLevel(int amount);
    public void addXP(int amount);
    public void addSkillpointsAvailable(int amount);
    public void addSkillpointsMax(int amount);

    public void removeSkillpointsAvailable(int amount);
}
