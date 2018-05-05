package de.nierhain.danger.level;

public class LevelSystem {
	
	public final int MAX_LEVEL = 10;
	private final float FIRST_CONSTANT = 1/3f;
	private final float SECOND_CONSTANT = 10f + 1/3f;
	
	public LevelSystem() {
	}
	
	public int getNeededXP(int currentXp, int currentLevel) {
		return (int) (currentXp - (FIRST_CONSTANT * (currentLevel + 1) + SECOND_CONSTANT * (currentLevel + 1 )));
	}
	
	public boolean isLevelUp(int currentXp, int currentLevel) {
		if (getNeededXP(currentXp, currentLevel) <= 0) {
			return true;
		} else {
			return false;
		}
	}
}
