package de.nierhain.danger.capabilities.skills;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ProviderSkills implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(ISkills.class)
    public static final Capability<ISkills> CAPABILITY_SKILL = null;

    ISkills instance = CAPABILITY_SKILL.getDefaultInstance();

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_SKILL;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? CAPABILITY_SKILL.<T>cast(instance) : null;
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
