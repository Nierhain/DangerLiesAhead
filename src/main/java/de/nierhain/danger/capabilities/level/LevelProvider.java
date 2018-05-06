package de.nierhain.danger.capabilities.level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class LevelProvider implements ICapabilitySerializable<NBTBase>{
	
	@CapabilityInject(ILevel.class)
	public final static Capability<ILevel> LEVEL_CAP = null;

	private ILevel instance = LEVEL_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == LEVEL_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == LEVEL_CAP) {
			return LEVEL_CAP.<T>cast(this.instance);
		} else {
			return null;
		}
	}

	@Override
	public NBTBase serializeNBT() {
		return LEVEL_CAP.getStorage().writeNBT(LEVEL_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		LEVEL_CAP.getStorage().readNBT(LEVEL_CAP, this.instance, null, nbt);
		
	}
	
	

}
