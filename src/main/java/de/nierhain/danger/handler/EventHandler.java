package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.skills.ISkills;
import de.nierhain.danger.network.PacketGetAbilities;
import de.nierhain.danger.network.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import static de.nierhain.danger.capabilities.skills.ProviderSkills.CAPABILITY_SKILL;

public class EventHandler {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        if(!event.player.getEntityWorld().isRemote){
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            ISkills skillsObj = player.getCapability(CAPABILITY_SKILL, null);
            int[] abilityLevels = {skillsObj.getHealth(), skillsObj.getLuck(), skillsObj.getMovementSpeed(), skillsObj.getAttackDamage(), skillsObj.getAttackSpeed()};
            int skillPoints = skillsObj.getSkillpoints();
            PacketHandler.INSTANCE.sendTo(new PacketGetAbilities(abilityLevels, skillPoints), player);
        }
    }
}
