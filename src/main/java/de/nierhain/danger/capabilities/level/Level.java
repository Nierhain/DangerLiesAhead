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
	private int[] neededXP = new int[10];
	
	private final double CONSTANT_A = 1 + 2/3;
	private final double CONSTANT_B = 8 + 1/3;
	
	private EntityLivingBase entity;
	public static final EnumFacing DEFAULT_FACING = null;
	
	public Level(@Nullable EntityLivingBase entity) {
		deserializeNBT(entity.getEntityData());
		this.initNeededXP();
	}

	public Level() {
		this.currentXP = 0;
		this.currentLevel = 0;
		this.initNeededXP();
	}

	private void initNeededXP() {
		for (int i = 0; i < neededXP.length; i++) {
			neededXP[i] = (int) Math.ceil((CONSTANT_A * Math.pow(i, 2) + CONSTANT_B * i));
		}
		
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("currentXP", this.currentXP);
		nbt.setInteger("currentLevel", this.currentLevel);
		nbt.setIntArray("neededXP", this.neededXP);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.currentXP = nbt.getInteger("currentXP");
		this.currentLevel = nbt.getInteger("currentLevel");
		this.neededXP = nbt.getIntArray("neededXP");
		
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
	public int getNeededXP(int index) {
		return this.neededXP[index];
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
	public void setNeededXP(int[] needed) {
		this.neededXP = needed;
		
	}

	@Override
	public int[] getNeededArr() {
		return this.neededXP;
	}
	
	@Override
	public boolean isLevelUp(int xp) {
		if (this.getCurrentXP() + xp >= this.neededXP[this.getCurrentLevel()]) {
			return true;
		}
		return false;
	}

}
