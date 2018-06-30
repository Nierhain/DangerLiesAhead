package de.nierhain.danger.capabilities.skills;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class StorageSkills implements Capability.IStorage<ISkills> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ISkills> capability, ISkills instance, EnumFacing side) {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("health", instance.getHealth());
        tag.setInteger("hunger", instance.getHunger());
        tag.setInteger("movementSpeed", instance.getMovementSpeed());
        tag.setInteger("attackDamage", instance.getAttackDamage());
        tag.setInteger("attackSpeed", instance.getAttackSpeed());
        tag.setInteger("luck", instance.getLuck());
        return tag;
    }

    @Override
    public void readNBT(Capability<ISkills> capability, ISkills instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.setHealth(tag.getInteger("health"));
        instance.setHunger(tag.getInteger("hunger"));
        instance.setMovementSpeed(tag.getInteger("movementSpeed"));
        instance.setAttackDamage(tag.getInteger("attackDamage"));
        instance.setAttackSpeed(tag.getInteger("attackSpeed"));
        instance.setLuck(tag.getInteger("luck"));
    }
}
