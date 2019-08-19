package de.nierhain.danger.capabilities.attributes;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ProviderAttributes implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(IAttributes.class)
    public static final Capability<IAttributes> CAPABILITY_ATTRIBUTES = null;

    IAttributes instance = CAPABILITY_ATTRIBUTES.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_ATTRIBUTES;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? CAPABILITY_ATTRIBUTES.<T>cast(instance) : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) CAPABILITY_ATTRIBUTES.getStorage().writeNBT(CAPABILITY_ATTRIBUTES, instance, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        CAPABILITY_ATTRIBUTES.getStorage().readNBT(CAPABILITY_ATTRIBUTES, instance, null, nbt);
    }
}
