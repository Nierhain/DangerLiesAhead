package de.nierhain.dangerliesahead.tile;

import de.nierhain.dangerliesahead.config.ConfigHandler;
import de.nierhain.dangerliesahead.util.DangerEnergy;
import de.nierhain.dangerliesahead.worlddata.SafePoint;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.List;

public class SafeBeaconTileEntity extends TileEntity implements ITickable {

    public static final int MAX_ENERGY = ConfigHandler.BeaconCategory.MAX_CAPACITY;
    public static final int RF_PER_TICK = ConfigHandler.BeaconCategory.RF_PER_TICK;
    public static final int MAX_RECEIVE = ConfigHandler.BeaconCategory.MAX_RECEIVE;

    private boolean isAdded = false;
    private String DESTROY_MESSAGE = "The haven at X: %d Z: %d has fallen";

    public static DangerEnergy energyStorage = new DangerEnergy(MAX_ENERGY, MAX_RECEIVE, 0);

    @Override
    public void update() {
        if(!world.isRemote){
            if(energyStorage.getEnergyStored() < RF_PER_TICK){
                if(isAdded) removeSafePoint();
            } else {
                energyStorage.extractEnergy(RF_PER_TICK, true);
                if(!isAdded) addSafePoint();
                energyStorage.consumePower(RF_PER_TICK);
                markDirty();
            }

        }
    }

    private void addSafePoint() {
        SafePoint safePoint = SafePoint.get(world);
        safePoint.addSafePoint(pos);
        isAdded = true;
        sendMessage(new TextComponentString(String.format("There is a new haven at X: %d Z: %d",pos.getX(), pos.getZ())), world.playerEntities);
    }

    private void removeSafePoint(){
        SafePoint safePoint = SafePoint.get(world);
        safePoint.removeSafePoint(pos);
        isAdded = false;
        if(ConfigHandler.BeaconCategory.ONLY_ONE_BEACON) DESTROY_MESSAGE = "The danger creeps back";
        sendMessage(new TextComponentString(String.format(DESTROY_MESSAGE, pos.getX(), pos.getZ())), world.playerEntities);
    }

    private void sendMessage(TextComponentString msg, List<EntityPlayer> playerEntities){
        for (Entity player : playerEntities) {
            player.sendMessage(msg);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        energyStorage.receiveEnergy(compound.getInteger("energy"), true);
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("energy", energyStorage.getEnergyStored());
        return super.writeToNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY){
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY){
            return CapabilityEnergy.ENERGY.cast(energyStorage);
        }
        return super.getCapability(capability, facing);
    }

    public void onDestroy() {
        if(isAdded){
            removeSafePoint();
        }
    }
}
