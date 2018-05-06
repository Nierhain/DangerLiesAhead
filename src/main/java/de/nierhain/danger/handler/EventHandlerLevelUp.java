package de.nierhain.danger.handler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

import de.nierhain.danger.stats.IncreaseHealth;
import de.nierhain.danger.util.Reference;

public class EventHandlerLevelUp {
	@SubscribeEvent
	public void PlayerPickupXPEvent(PlayerPickupXpEvent e) {
		EntityPlayer p =  e.getEntityPlayer();
		
		IncreaseHealth health = new IncreaseHealth(p);
		
		health.increaseHealth();
		
	}
	
	
}
