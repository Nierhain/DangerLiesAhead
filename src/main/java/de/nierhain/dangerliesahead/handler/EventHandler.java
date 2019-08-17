package de.nierhain.dangerliesahead.handler;


import de.nierhain.dangerliesahead.capabilities.attributes.IAttributes;
import de.nierhain.dangerliesahead.capabilities.level.ILevel;
import de.nierhain.dangerliesahead.network.PacketAttributesToClient;
import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.network.PacketLevelToClient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static de.nierhain.dangerliesahead.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;
import static de.nierhain.dangerliesahead.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;

public class EventHandler {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        if(!event.player.getEntityWorld().isRemote){
            IAttributes skills = event.player.getCapability(CAPABILITY_ATTRIBUTES, null);
            ILevel levels = event.player.getCapability(CAPABILITY_LEVEL, null);
            PacketHandler.INSTANCE.sendTo(new PacketAttributesToClient(skills.getAllAttributes(), skills.getSkillpoints()), (EntityPlayerMP) event.player);
            PacketHandler.INSTANCE.sendTo(new PacketLevelToClient(levels.getLevel(), levels.getXP()), (EntityPlayerMP) event.player);
        }
    }
}
