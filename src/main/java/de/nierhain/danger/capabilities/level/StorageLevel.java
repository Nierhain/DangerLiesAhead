package de.nierhain.danger.capabilities.level;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class StorageLevel implements Capability.IStorage<ILevel> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side) {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("xp", instance.getXP());
        tag.setInteger("level", instance.getLevel());
        return tag;
    }

    @Override
    public void readNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.setXP(tag.getInteger("xp"));
        instance.setLevel(tag.getInteger("level"));
    }
}
