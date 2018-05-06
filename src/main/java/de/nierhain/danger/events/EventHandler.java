package de.nierhain.danger.events;

import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.LevelProvider;
import de.nierhain.danger.stats.IncreaseHealth;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventHandler {
	
	@SubscribeEvent
	public void onPlayerLogsIn(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		ILevel level = player.getCapability(LevelProvider.LEVEL_CAP, null);
		
		String message = String.format("aktuelles Level: %d", level.getCurrentLevel());
		
		player.sendMessage(new TextComponentString(message));
	}
	
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		EntityPlayer player = event.getEntityPlayer();
		
		ILevel level = player.getCapability(LevelProvider.LEVEL_CAP, null);
		ILevel oldLevel = event.getOriginal().getCapability(LevelProvider.LEVEL_CAP, null);
		
		level.setCurrentLevel(oldLevel.getCurrentLevel());
		level.setCurrentXP(oldLevel.getCurrentXP());
	}
	
	@SubscribeEvent
	public void onPlayerPickupXP(PlayerPickupXpEvent e) {
		EntityPlayer p =  e.getEntityPlayer();
		ILevel level = p.getCapability(LevelProvider.LEVEL_CAP, null);
		
		int newXp = e.getOrb().xpValue + level.getCurrentXP();
		
		if (level.isLevelUp(e.getOrb().getXpValue())) {
			new IncreaseHealth(p).increaseHealth();
			level.setCurrentXP(newXp - level.getCurrentXP());
		} else {
			level.setCurrentXP(newXp);
		}	
	}
	
}
