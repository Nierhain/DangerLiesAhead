package de.nierhain.danger.skills;

import java.util.logging.Logger;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import de.nierhain.danger.api.level.IMaxHealth;
import de.nierhain.danger.util.Reference;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityMaxHealth {
	
	@CapabilityInject(IMaxHealth.class)
	public static final Capability<IMaxHealth> MAX_HEALTH_CAPABILITY = null;
	
	public static final EnumFacing DEFAULT_FACING = null;
	
	public static final ResourceLocation ID = new ResourceLocation(Reference.MODID, "Max Health");
	
	public static final Marker LOG_MARKER = MarkerManager.getMarker("MaxHealth");
	
	public static void register() {
		CapabilityManager.INSTANCE.register(IMaxHealth.class, new Capability.IStorage<IMaxHealth>(){

			@Override
			public NBTBase writeNBT(Capability<IMaxHealth> capability, IMaxHealth instance, EnumFacing side) {
				return new NBTTagFloat(instance.getBonusMaxHealth());
			}

			@Override
			public void readNBT(Capability<IMaxHealth> capability, IMaxHealth instance, EnumFacing side, NBTBase nbt) {
				instance.setBonusMaxHealth(((NBTTagFloat) nbt).getFloat());
				
			}
			
		}, () -> new MaxHealth(null));
	}
	

}
