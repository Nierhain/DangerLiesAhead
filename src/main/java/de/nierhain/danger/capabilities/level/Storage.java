package de.nierhain.danger.capabilities.level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class Storage implements Capability.IStorage<ILevelHandler>{

	@Override
	public NBTBase writeNBT(Capability<ILevelHandler> capability, ILevelHandler instance, EnumFacing side) {
		
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("dangerlevel", instance.getLevel());
		tag.setInteger("dangerxp", instance.getXP());
		return tag;
	}

	@Override
	public void readNBT(Capability<ILevelHandler> capability, ILevelHandler instance, EnumFacing side, NBTBase nbt) {
		
		final NBTTagCompound tag = (NBTTagCompound) nbt;
		instance.setLevel(tag.getInteger("dangerlevel"));
		instance.setXP(tag.getInteger("dangerxp"));
		
	}
	
}
