package de.nierhain.danger.capabilities.skills;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class StorageSkill implements IStorage<ISkillHandler> {

	@Override
	public NBTBase writeNBT(Capability<ISkillHandler> capability, ISkillHandler instance, EnumFacing side) {
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("availableSkillPoints", instance.getAvailableSkillPoints());
		tag.setInteger("usedSkillPoints", instance.getUsedSkillPoints());
		return tag;
	}

	@Override
	public void readNBT(Capability<ISkillHandler> capability, ISkillHandler instance, EnumFacing side, NBTBase nbt) {
		final NBTTagCompound tag = (NBTTagCompound) nbt;
		instance.setAvailableSkillPoints(tag.getInteger("availableSkillPoints"));
		instance.setUsedSkillPoints(tag.getInteger("usedSkillPoints"));
	}

}
