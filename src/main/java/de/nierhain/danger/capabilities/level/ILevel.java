package de.nierhain.danger.capabilities.level;

public interface ILevel {

    public int getLevel();
    public void setLevel(int amount);
    public void addLevel(int amount);

    public int getXP();
    public void setXP(int amount);
    public void addXP(int amount);

    public int getSkillpointsAvailable();
    public void setSkillpointsAvailable(int amount);
    public void addSkillpointsAvailable(int amount);
    public void removeSkillpointsAvailable(int amount);

    public int getSkillpointsMax();
    public void setSkillpointsMax(int amount);
    public void addSkillpointsMax(int amount);

    public void reset();


}
