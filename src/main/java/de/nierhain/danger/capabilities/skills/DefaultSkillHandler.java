package de.nierhain.danger.capabilities.skills;

public class DefaultSkillHandler implements ISkillHandler {

	private int points = 0;
	private int used = 0;
	
	
	@Override
	public int getAvailableSkillPoints() {
		return points;
	}

	@Override
	public int getUsedSkillPoints() {
		return used;
	}

	@Override
	public void setAvailableSkillPoints(int points) {
		this.points = points;

	}

	@Override
	public void addAvailableSkillPoints(int points) {
		this.points += points;

	}

	@Override
	public void setUsedSkillPoints(int points) {
		this.used = points;
	}

	@Override
	public void removeAvailableSkillPoints(int points) {
		this.points -= points;
	}

}
