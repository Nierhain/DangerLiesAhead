package de.nierhain.danger.capabilities.skilling;

import de.nierhain.danger.capabilities.level.ILevel;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ProviderSkilling implements ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(ISkilling.class)
    public static final Capability<ILevel> CAPABILITY_SKILL = null;

    ILevel instance = CAPABILITY_SKILL.getDefaultInstance();

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
