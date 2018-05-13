package de.nierhain.danger.handler;


import de.nierhain.danger.capabilities.level.ILevelHandler;
import de.nierhain.danger.capabilities.level.Provider;
import de.nierhain.danger.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilitiesHandler {
	
	public static ILevelHandler getHandler(Entity entity) {
		if (entity.hasCapability(Provider.CAPABILITY_LEVEL, EnumFacing.DOWN))
			return entity.getCapability(Provider.CAPABILITY_LEVEL, EnumFacing.DOWN);
	    return null;
	}
	
	@SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        
        if (event.getObject() instanceof EntityPlayer)
            event.addCapability(new ResourceLocation(Reference.MODID, "DangerLiesAhead"), new Provider());
    }
	
	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		final ILevelHandler original = getHandler(event.getOriginal());
		final ILevelHandler clone = getHandler(event.getEntity());
		clone.setLevel(original.getLevel());
		clone.setXP(original.getXP());
	}
}
