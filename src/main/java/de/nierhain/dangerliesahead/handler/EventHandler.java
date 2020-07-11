package de.nierhain.dangerliesahead.handler;


import de.nierhain.dangerliesahead.capabilities.attributes.IAttributes;
import de.nierhain.dangerliesahead.capabilities.level.ILevel;
import de.nierhain.dangerliesahead.network.PacketAttributesToClient;
import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.network.PacketLevelToClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static de.nierhain.dangerliesahead.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;
import static de.nierhain.dangerliesahead.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;

public class EventHandler {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        EntityPlayer player = event.player;
        if(!player.getEntityWorld().isRemote){
            synchronize(event.player);
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event){
        EntityPlayer player = event.player;
        if(!player.getEntityWorld().isRemote && !event.isEndConquered()) synchronize(player);
    }



    private void synchronize(EntityPlayer player){
        IAttributes skills = player.getCapability(CAPABILITY_ATTRIBUTES, null);
        ILevel levels = player.getCapability(CAPABILITY_LEVEL, null);
        PacketHandler.INSTANCE.sendTo(new PacketAttributesToClient(skills.getAllAttributes(), skills.getSkillpoints()), (EntityPlayerMP) player);
        PacketHandler.INSTANCE.sendTo(new PacketLevelToClient(levels.getLevel(), levels.getXP()), (EntityPlayerMP) player);
    }
}
