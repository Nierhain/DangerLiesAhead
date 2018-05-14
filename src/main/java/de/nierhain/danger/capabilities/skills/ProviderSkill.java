package de.nierhain.danger.capabilities.skills;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ProviderSkill implements ICapabilitySerializable<NBTTagCompound>{
	
    @CapabilityInject(ISkillHandler.class)
    public static final Capability<ISkillHandler> CAPABILITY_SKILL = null;
	
	ISkillHandler instance = CAPABILITY_SKILL.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CAPABILITY_SKILL;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return hasCapability(capability, facing) ? CAPABILITY_SKILL.cast(instance): null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) CAPABILITY_SKILL.getStorage().writeNBT(CAPABILITY_SKILL, instance, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		CAPABILITY_SKILL.getStorage().readNBT(CAPABILITY_SKILL, instance, null, nbt);
	}

}
