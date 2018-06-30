package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.MapLevels;
import de.nierhain.danger.utils.Reference;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;

public class EventHandler {
    @SubscribeEvent
    public void onXPPickup(PlayerPickupXpEvent e){
        EntityPlayer player = e.getEntityPlayer();

        ILevel cap = player.getCapability(CAPABILITY_LEVEL, null);
        int currentLevel = cap.getLevel();
        int nextLevel = currentLevel++;

        cap.addXP(e.getOrb().getXpValue());

        if(MapLevels.isLevelUp(nextLevel, cap.getXP())) {
            cap.addLevel(1);
            cap.setXP(cap.getXP() - MapLevels.getNeededXP(nextLevel));
            cap.addSkillpointsMax(1);
            cap.addSkillpointsAvailable(1);
        }



        float oldHealth = player.getMaxHealth() - 20;
        AttributeModifier healthMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Health Modifier", oldHealth + 2, 0);
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeAllModifiers();
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(healthMod);

    }
}
