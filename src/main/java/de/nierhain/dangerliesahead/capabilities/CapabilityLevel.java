package de.nierhain.dangerliesahead.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityLevel implements ILevel, ICapabilitySerializable<CompoundNBT> {

    private int level = 0;
    private int xp = 0;

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getXP() {
        return 0;
    }

    @Override
    public void addLevel(int levels) {
        setLevel(this.level + levels);
    }

    @Override
    public void addXP(int xp) {
        setXP(this.xp + xp);
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setXP(int xp) {
        this.xp = xp;
    }

    @CapabilityInject(ILevel.class)
    public static Capability<ILevel> LEVEL = null;

    private LazyOptional<ILevel> instance = LazyOptional.of(LEVEL::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap ==  LEVEL ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) LEVEL.getStorage().writeNBT(LEVEL, this.instance.orElse(null), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        LEVEL.getStorage().readNBT(LEVEL, this.instance.orElseThrow(() -> new IllegalArgumentException("Lazy optional cannot be empty")), null, nbt);
    }

    public static class LevelStorage implements Capability.IStorage<ILevel> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<ILevel> capability, ILevel instance, Direction side) {
            final CompoundNBT tag = new CompoundNBT();
            tag.putInt("level", instance.getLevel());
            tag.putInt("xp", instance.getXP());
            return tag;
        }

        @Override
        public void readNBT(Capability<ILevel> capability, ILevel instance, Direction side, INBT nbt) {
            final CompoundNBT tag = (CompoundNBT) nbt;
            instance.setLevel(tag.getInt("level"));
            instance.setXP(tag.getInt("xp"));
        }
    }
}
