package de.nierhain.danger.capabilities.level;

public class DefaultLevelHandler implements ILevelHandler{

	private int level;
	private int xp;
	
	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public int getXP() {
		return xp;
	}

	@Override
	public void addXP(int xp) {
		this.xp += xp;
	}

	@Override
	public void setXP(int xp) {
		this.xp = xp;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}
	
}
