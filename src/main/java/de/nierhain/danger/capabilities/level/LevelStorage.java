package de.nierhain.danger.capabilities.level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LevelStorage implements IStorage<ILevel>{
	@Override
	public NBTBase writeNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side) {
			
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("currentXP", instance.getCurrentXP());
		nbt.setInteger("currentLevel", instance.getCurrentLevel());
		nbt.setIntArray("neededXP", instance.getNeededArr());
		return nbt;
		
	}

	@Override
	public void readNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side, NBTBase nbt) {
		instance.setCurrentLevel(((NBTTagCompound) nbt).getInteger("currentLevel"));
		instance.setCurrentXP(((NBTTagCompound) nbt).getInteger("currentXP"));
		instance.setNeededXP(((NBTTagCompound) nbt).getIntArray("neededXP"));
	}
}
