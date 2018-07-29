package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.capabilities.attributes.ProviderAttributes;
import de.nierhain.danger.enums.Attribute;
import de.nierhain.danger.event.EventLevelUp;
import de.nierhain.danger.network.PacketHandler;
import de.nierhain.danger.network.PacketSkillpointsToClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_SKILL;
import static de.nierhain.danger.config.Configuration.*;

public class AttributesHandler {


    private static double[] MODIFIER = {2, 1, 0.1, 1, 1};
    private static double[] DEFAULT = {20, 0, 0.1, 2, 4};
    private static IAttribute[] ATTRIBUTES = {SharedMonsterAttributes.MAX_HEALTH,
            SharedMonsterAttributes.LUCK,
            SharedMonsterAttributes.MOVEMENT_SPEED,
            SharedMonsterAttributes.ATTACK_DAMAGE,
            SharedMonsterAttributes.ATTACK_SPEED};
    private static final int INCREMENT_PER_LEVEL = 1;
    private static double newAmount;

    private static IAttributes getHandler(Entity entity) {

        if(entity.hasCapability(CAPABILITY_SKILL, null))
            return entity.getCapability(CAPABILITY_SKILL, null);

        return null;
    }

    private static boolean canSkill(EntityPlayer player){
        IAttributes skills = getHandler(player);
        if(skills.getSkillpoints() > 0){
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation("danger", "skills"), new ProviderAttributes());
        }
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {

        final IAttributes original = getHandler(event.getOriginal());
        final IAttributes clone = getHandler(event.getEntityPlayer());

        clone.setSkillpoints(original.getSkillpoints());
        clone.setAllAttributes(original.getAllAttributes());
    }

    @SubscribeEvent
    public void onPlayerLevelUp(EventLevelUp event){
        EntityPlayer player = event.getPlayer();
        IAttributes skills = getHandler(player);
        skills.setSkillpoints(skills.getSkillpoints() + INCREMENT_PER_LEVEL);
        PacketHandler.INSTANCE.sendTo(new PacketSkillpointsToClient(skills.getSkillpoints()), (EntityPlayerMP) player);
    }

    public static void skill(Attribute attr, EntityPlayer player){
        if(canSkill(player)){
            int index = attr.getValue();
            newAmount = getHandler(player).getAttribute(attr) * MODIFIER[index] + DEFAULT[index];

            player.getEntityAttribute(ATTRIBUTES[index]).setBaseValue(newAmount);
            getHandler(player).setAttribute(attr, getHandler(player).getAttribute(attr) + INCREMENT_PER_LEVEL);
            getHandler(player).removeSkillpoint();

            // player needs to be healed after max health has changed
            if(attr == Attribute.HEALTH)
                player.setHealth(player.getMaxHealth());
            // velocityChanged flag needs to be enabled for Minecraft to make the change permanent
            if(attr == Attribute.MOVEMENT_SPEED)
                player.velocityChanged = true;
        }
    }

}
