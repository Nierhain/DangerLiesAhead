package de.nierhain.danger.capabilities.level;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ProviderLevel implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(ILevel.class)
    public static final Capability<ILevel> CAPABILITY_LEVEL = null;

    ILevel instance = CAPABILITY_LEVEL.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_LEVEL;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? CAPABILITY_LEVEL.<T>cast(instance) : null;
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
