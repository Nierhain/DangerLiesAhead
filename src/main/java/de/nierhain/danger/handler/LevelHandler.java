package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.MapLevels;
import de.nierhain.danger.capabilities.level.ProviderLevel;
import de.nierhain.danger.event.EventLevelUp;
import de.nierhain.danger.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;
import static sun.misc.Version.print;

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
        if(!e.isCanceled()){
            int xp = e.getOrb().getXpValue();
            xpPickUp(player, xp);
        }
    }

    private void xpPickUp(EntityPlayer player, int xp){
        ILevel cap = player.getCapability(CAPABILITY_LEVEL, null);
        int currentLevel = cap.getLevel();
        int nextLevel = currentLevel + 1;

        cap.addXP(xp);
        if(MapLevels.isLevelUp(nextLevel, cap.getXP())) {
            cap.addLevel(1);
            MinecraftForge.EVENT_BUS.post(new EventLevelUp(player));
        }
    }
}
