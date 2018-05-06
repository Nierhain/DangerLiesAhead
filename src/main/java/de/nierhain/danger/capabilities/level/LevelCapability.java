package de.nierhain.danger.capabilities.level;

import de.nierhain.danger.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LevelCapability {
	
	
	public static void register() {
		CapabilityManager.INSTANCE.register(ILevel.class, new CapabilityLevel(), () -> new Level(null));
	}
	
	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent event) {
		if(event.getObject() instanceof EntityPlayer) {
			final ResourceLocation ID = new ResourceLocation(Reference.MODID, "Level");
			final Level level = new Level((EntityPlayer) event.getObject());
			event.addCapability(ID, new Provider(level));
		}
			
	}

	
	public static class CapabilityLevel implements IStorage<ILevel> {

		@Override
		public NBTBase writeNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side) {
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("currentXP", instance.getCurrentXP());
			nbt.setInteger("currentLevel", instance.getCurrentLevel());
			nbt.setInteger("neededXP", instance.getNeededXP());
			return nbt;
		}

		@Override
		public void readNBT(Capability<ILevel> capability, ILevel instance, EnumFacing side, NBTBase nbt) {
			instance.setCurrentLevel(((NBTTagCompound) nbt).getInteger("currentLevel"));
			instance.setCurrentXP(((NBTTagCompound) nbt).getInteger("currentXP"));
			instance.setNeededXP(((NBTTagCompound) nbt).getInteger("neededXP"));
		}
		
	}
	
	public static class Provider implements ICapabilitySerializable<NBTTagCompound> {
		
		@CapabilityInject(ILevel.class)
		public final static Capability<ILevel> LEVEL = null;
	
		private final ILevel cap = new Level();
		
		private final Level lvl;
		
		public Provider(Level level) {
			this.lvl = level;
		}
		
		@Override
		public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
			return capability == LEVEL;
		}

		@Override
		public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			if(capability == LEVEL) {
				return LEVEL.cast(cap);
			} else {
				return null;
			}
		}

		@Override
		public NBTTagCompound serializeNBT() {
			return lvl.serializeNBT();
		}

		@Override
		public void deserializeNBT(NBTTagCompound nbt) {
			lvl.deserializeNBT(nbt);
		}
		
	}

}
