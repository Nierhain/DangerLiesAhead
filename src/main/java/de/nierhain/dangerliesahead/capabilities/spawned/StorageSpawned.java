package de.nierhain.dangerliesahead.capabilities.spawned;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class StorageSpawned implements Capability.IStorage<ISpawned> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ISpawned> capability, ISpawned instance, EnumFacing enumFacing) {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("spawned", instance.isSpawned());
        return tag;
    }

    @Override
    public void readNBT(Capability<ISpawned> capability, ISpawned instance, EnumFacing enumFacing, NBTBase nbtBase) {
        final NBTTagCompound tag = (NBTTagCompound) nbtBase;
        instance.setSpawned(tag.getBoolean("spawned"));
    }
}
