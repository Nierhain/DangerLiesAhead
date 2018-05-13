package de.nierhain.danger.capabilities.level;

import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;

public interface ILevelHandler {
	int getLevel();
	int getXP();
	
	void addXP(int xp);
	void setXP(int xp);
	
	void setLevel(int level);
}
