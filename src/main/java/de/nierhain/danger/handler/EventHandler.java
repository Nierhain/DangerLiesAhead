package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.level.ILevelHandler;
import de.nierhain.danger.capabilities.skills.ISkillHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventHandler {
	
	@SubscribeEvent
	public void onXPPickup(PlayerPickupXpEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		int xp = event.getOrb().getXpValue();
		
		ILevelHandler level = CapabilitiesHandler.getLevelHandler(player);
		level.addXP(xp);
		
		ISkillHandler skill = CapabilitiesHandler.getSkillHandler(player);
		
		if(level.hadLevelUp())
			skill.addAvailableSkillPoints(1);
			level.setHadLevelUp(false);
	}
	
	@SubscribeEvent
	public void onPlayerLogIn(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		ILevelHandler level = CapabilitiesHandler.getLevelHandler(player);

	}
}