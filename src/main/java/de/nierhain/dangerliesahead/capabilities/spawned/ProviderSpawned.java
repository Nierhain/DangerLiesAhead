package de.nierhain.dangerliesahead.capabilities.spawned;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ProviderSpawned implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(ISpawned.class)
    public static final Capability<ISpawned> CAPABILITY_SPAWNED = null;

    ISpawned instance = CAPABILITY_SPAWNED.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing enumFacing) {
        return capability == CAPABILITY_SPAWNED;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing enumFacing) {
        return hasCapability(capability, enumFacing) ? CAPABILITY_SPAWNED.<T>cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) CAPABILITY_SPAWNED.getStorage().writeNBT(CAPABILITY_SPAWNED, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        CAPABILITY_SPAWNED.getStorage().readNBT(CAPABILITY_SPAWNED, instance, null, nbt);
    }
}
