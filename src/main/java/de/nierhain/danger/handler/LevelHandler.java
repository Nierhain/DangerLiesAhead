package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.MapLevels;
import de.nierhain.danger.capabilities.level.ProviderLevel;
import de.nierhain.danger.capabilities.skills.ProviderSkills;
import de.nierhain.danger.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;

public class LevelHandler {

    private static ILevel getHandler(Entity entity) {

        if (entity.hasCapability(CAPABILITY_LEVEL, EnumFacing.DOWN))
            return entity.getCapability(CAPABILITY_LEVEL, EnumFacing.DOWN);

        return null;
    }

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation("danger", "level"), new ProviderLevel());
        }
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {

        final ILevel original = getHandler(event.getOriginal());
        final ILevel clone = getHandler(event.getEntity());

        clone.setXP(original.getXP());
        clone.setLevel(original.getLevel());
        clone.setSkillpointsAvailable(original.getSkillpointsAvailable());
        clone.setSkillpointsMax(original.getSkillpointsMax());
    }

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
