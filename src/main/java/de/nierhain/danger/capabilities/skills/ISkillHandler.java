package de.nierhain.danger.capabilities.skills;

public interface ISkillHandler {
	int getAvailableSkillPoints();
	int getUsedSkillPoints();
	
	void setAvailableSkillPoints(int points);
	void addAvailableSkillPoints(int points);
	void removeAvailableSkillPoints(int points);
	
	void setUsedSkillPoints(int points);
}
