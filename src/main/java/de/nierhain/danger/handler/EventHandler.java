package de.nierhain.danger.handler;

import de.nierhain.danger.utils.Reference;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

public class EventHandler {
    @SubscribeEvent
    public void onXPPickup(PlayerPickupXpEvent e){
        EntityPlayer player = e.getEntityPlayer();
        float oldHealth = player.getMaxHealth() - 20;
        AttributeModifier healthMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Health Modifier", oldHealth + 2, 0);
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeAllModifiers();
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(healthMod);

    }
}
