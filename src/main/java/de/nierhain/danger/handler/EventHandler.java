package de.nierhain.danger.handler;


import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.network.PacketAttributesToClient;
import de.nierhain.danger.network.PacketHandler;
import de.nierhain.danger.network.PacketLevelToClient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;
import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;

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
