package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.skills.ISkills;
import de.nierhain.danger.capabilities.skills.ProviderSkills;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static de.nierhain.danger.capabilities.skills.ProviderSkills.CAPABILITY_SKILL;

public class SkillsHandler {

    private static ISkills getHandler(Entity entity) {
        if(entity.hasCapability(CAPABILITY_SKILL, null))
            return entity.getCapability(CAPABILITY_SKILL, null);

        return null;
    }

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation("danger", "skills"), new ProviderSkills());
        }
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {

        final ISkills original = getHandler(event.getOriginal());
        final ISkills clone = getHandler(event.getEntity());

        clone.setHealth(original.getHealth());
        clone.setHunger(original.getHunger());
        clone.setMovementSpeed(original.getMovementSpeed());
        clone.setAttackDamage(original.getAttackDamage());
        clone.setAttackSpeed(original.getAttackSpeed());
        clone.setLuck(original.getLuck());
    }


}
