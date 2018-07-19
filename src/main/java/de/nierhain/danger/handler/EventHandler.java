package de.nierhain.danger.handler;

<<<<<<< HEAD
import de.nierhain.danger.capabilities.attributes.IAttributes;
=======
import de.nierhain.danger.capabilities.skills.IAttributes;
>>>>>>> master
import de.nierhain.danger.network.PacketGetAbilities;
import de.nierhain.danger.network.PacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

<<<<<<< HEAD
import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_SKILL;
=======
import static de.nierhain.danger.capabilities.skills.ProviderAttributes.CAPABILITY_SKILL;
>>>>>>> master

public class EventHandler {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        if(!event.player.getEntityWorld().isRemote){
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            IAttributes skillsObj = player.getCapability(CAPABILITY_SKILL, null);
            int[] abilityLevels = {skillsObj.getHealth(), skillsObj.getLuck(), skillsObj.getMovementSpeed(), skillsObj.getAttackDamage(), skillsObj.getAttackSpeed()};
            int skillPoints = skillsObj.getSkillpoints();
            PacketHandler.INSTANCE.sendTo(new PacketGetAbilities(abilityLevels, skillPoints), player);
        }
    }
}
