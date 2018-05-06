package de.nierhain.danger.capabilities.level;

public interface ILevel {
	
	public int getCurrentLevel();
	public int getCurrentXP();
	public int getNeededXP();
	
	public void setCurrentXP(int xp);
	public void setCurrentLevel(int level);
	public void setNeededXP(int needed);
}
