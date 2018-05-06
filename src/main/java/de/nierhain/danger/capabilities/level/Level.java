package de.nierhain.danger.capabilities.level;

import javax.annotation.Nullable;

import de.nierhain.danger.util.Constants;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.INBTSerializable;

public class Level implements ILevel, INBTSerializable<NBTTagCompound> {
	
	private int currentXP;
	private int currentLevel;
	private int neededXP;
	private EntityLivingBase entity;
	public static final EnumFacing DEFAULT_FACING = null;
	
	public Level(@Nullable EntityLivingBase entity) {
		deserializeNBT(entity.getEntityData());
	}

	public Level() {
		this.currentXP = 0;
		this.currentLevel = 0;
		this.neededXP = 10;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("currentXP", this.currentXP);
		nbt.setInteger("currentLevel", this.currentLevel);
		nbt.setInteger("neededXP", this.neededXP);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.currentXP = nbt.getInteger("currentXP");
		this.currentLevel = nbt.getInteger("currentLevel");
		this.neededXP = nbt.getInteger("neededXP");
		
	}

	@Override
	public int getCurrentLevel() {
		return this.currentLevel;
	}

	@Override
	public int getCurrentXP() {
		return this.currentXP;
	}

	@Override
	public int getNeededXP() {
		return this.neededXP;
	}

	@Override
	public void setCurrentXP(int xp) {
		this.currentXP = xp;
	}

	@Override
	public void setCurrentLevel(int level) {
		this.currentLevel = level;
		
	}

	@Override
	public void setNeededXP(int needed) {
		this.neededXP = needed;
		
	}

}
