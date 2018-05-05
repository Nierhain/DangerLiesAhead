package de.nierhain.danger.level;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public class LevelPlayer {
	
	private int currentXp;
	private int currentLevel;
	private int skillPointsAvailable;
	private EntityLivingBase player;
	
	public LevelPlayer(EntityLivingBase player) {
		currentXp = 0;
		currentLevel = 0;
		skillPointsAvailable = 0;
		this.player = player;
	}
	
	public void levelsUp() {
		skillPointsAvailable += 1;
	}
	
	public void addHealth() {
		player.setHealth(player.getMaxHealth() * 1.1f);
	}
}
