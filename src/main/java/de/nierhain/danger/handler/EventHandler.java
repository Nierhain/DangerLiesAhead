package de.nierhain.danger.handler;


import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.network.PacketAttributesToClient;
import de.nierhain.danger.network.PacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_SKILL;

public class EventHandler {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        if(!event.player.getEntityWorld().isRemote){
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            IAttributes skillsObj = player.getCapability(CAPABILITY_SKILL, null);
            int[] abilityLevels = skillsObj.getAllAttributes();
            int skillPoints = skillsObj.getSkillpoints();
            PacketHandler.INSTANCE.sendTo(new PacketAttributesToClient(abilityLevels, skillPoints), player);
        }
    }
}
