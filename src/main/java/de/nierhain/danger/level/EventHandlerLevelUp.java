package de.nierhain.danger.level;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerLevelUp {
	@SubscribeEvent
	public void PlayerPickupXPEvent(PlayerPickupXpEvent e) {
		EntityPlayerMP p = (EntityPlayerMP) e.getEntityPlayer();
		LevelSystem system = new LevelSystem();
		
		if(system.isLevelUp((int) p.experience, (int) p.experienceLevel)) {
			
		}
	}
}
