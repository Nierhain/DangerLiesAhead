package de.nierhain.danger.capabilities.attributes;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class StorageAttributes implements Capability.IStorage<IAttributes> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IAttributes> capability, IAttributes instance, EnumFacing side) {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("skillpoints", instance.getSkillpoints());
        tag.setInteger("health", instance.getHealth());
        tag.setInteger("hunger", instance.getHunger());
        tag.setInteger("movementSpeed", instance.getMovementSpeed());
        tag.setInteger("attackDamage", instance.getAttackDamage());
        tag.setInteger("attackSpeed", instance.getAttackSpeed());
        tag.setInteger("luck", instance.getLuck());
        return tag;
    }

    @Override
    public void readNBT(Capability<IAttributes> capability, IAttributes instance, EnumFacing side, NBTBase nbt) {
        final NBTTagCompound tag = (NBTTagCompound) nbt;
        instance.setSkillpoints(tag.getInteger("skillpoints"));
        instance.setHealth(tag.getInteger("health"));
        instance.setHunger(tag.getInteger("hunger"));
        instance.setMovementSpeed(tag.getInteger("movementSpeed"));
        instance.setAttackDamage(tag.getInteger("attackDamage"));
        instance.setAttackSpeed(tag.getInteger("attackSpeed"));
        instance.setLuck(tag.getInteger("luck"));
    }
}
