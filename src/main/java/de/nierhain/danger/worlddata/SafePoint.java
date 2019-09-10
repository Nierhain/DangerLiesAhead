package de.nierhain.danger.worlddata;

import de.nierhain.danger.Danger;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;

public class SafePoint extends WorldSavedData {
    public static final String DATA_NAME = Danger.MODID + "_safePoint";

    private ArrayList<BlockPos> safePoints = new ArrayList<>();

    public SafePoint(){
        super(DATA_NAME);
    }
    public SafePoint(String s){
        super(s);
    }

    public SafePoint(World world){
        super(DATA_NAME);
        safePoints.add(world.getSpawnPoint());
    }

    public ArrayList<BlockPos> getSafePoint(){
        return safePoints;
    }

    public void addSafePoint(BlockPos pos){
        safePoints.add(pos);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        int[] x = nbt.getIntArray("x");
        int[] y = nbt.getIntArray("y");
        int[] z = nbt.getIntArray("z");

        for(int i = 0; i < x.length; i++){
            safePoints.add(new BlockPos(x[i], y[i], z[i]));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        int[] x = new int[safePoints.size()];
        int[] y = new int[safePoints.size()];
        int[] z = new int[safePoints.size()];
        for(int i = 0; i < safePoints.size(); i++) {
            x[i] = safePoints.get(i).getX();
            y[i] = safePoints.get(i).getY();
            z[i] = safePoints.get(i).getZ();
        }
        compound.setIntArray("x", x);
        compound.setIntArray("y", y);
        compound.setIntArray("z", z);
        return compound;
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
