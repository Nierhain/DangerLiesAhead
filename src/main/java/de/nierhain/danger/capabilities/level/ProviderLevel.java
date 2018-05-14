package de.nierhain.danger.capabilities.level;

import de.nierhain.danger.handler.CapabilitiesHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ProviderLevel implements ICapabilitySerializable<NBTTagCompound>{
	
    @CapabilityInject(ILevelHandler.class)
    public static final Capability<ILevelHandler> CAPABILITY_LEVEL = null;
	
	ILevelHandler instance = CAPABILITY_LEVEL.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CAPABILITY_LEVEL;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return hasCapability(capability, facing) ? CAPABILITY_LEVEL.cast(instance): null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) CAPABILITY_LEVEL.getStorage().writeNBT(CAPABILITY_LEVEL, instance, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		CAPABILITY_LEVEL.getStorage().readNBT(CAPABILITY_LEVEL, instance, null, nbt);
	}
	

}
