package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.level.Level;
import de.nierhain.danger.capabilities.level.LevelProvider;
import de.nierhain.danger.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilitiesHandler {
	
	public static final ResourceLocation LOCATION = new ResourceLocation(Reference.MODID, "level");
	
	@SubscribeEvent
	public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if(!(event.getObject() instanceof EntityPlayer)) return;
		
		event.addCapability(LOCATION, new LevelProvider());
	}
}
