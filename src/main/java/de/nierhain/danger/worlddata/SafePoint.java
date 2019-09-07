package de.nierhain.danger.worlddata;

import de.nierhain.danger.Danger;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class SafePoint extends WorldSavedData {
    public static final String DATA_NAME = Danger.MODID + "_safePoint";

    private static BlockPos safepoint;

    public SafePoint(){
        super(DATA_NAME);
    }

    public SafePoint(World world){
        super(DATA_NAME);
        safepoint = world.getSpawnPoint();
    }

    public BlockPos getSafePoint(){
        return safepoint;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        safepoint.add(nbt.getInteger("x"), nbt.getInteger("y"), nbt.getInteger("z"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("x", safepoint.getX());
        compound.setInteger("y", safepoint.getY());
        compound.setInteger("z", safepoint.getZ());
        return compound;
    }

    public void setSafePoint(BlockPos pos){
        safepoint = pos;
    }

    public static SafePoint get(World world){
        MapStorage storage =  world.getPerWorldStorage();
        SafePoint instance = (SafePoint) storage.getOrLoadData(SafePoint.class, DATA_NAME);

        if (instance == null){
            instance = new SafePoint(world);
            storage.setData(DATA_NAME, instance);
        }
        return instance;
    }
}
