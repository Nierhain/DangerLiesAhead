package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.capabilities.attributes.ProviderAttributes;
import de.nierhain.danger.enums.Attribute;
import de.nierhain.danger.event.EventLevelUp;
import de.nierhain.danger.gui.NotificationLevelUp;
import de.nierhain.danger.network.PacketAttributesToClient;
import de.nierhain.danger.network.PacketHandler;
import de.nierhain.danger.network.PacketLevelToClient;
import de.nierhain.danger.network.PacketSkillpointsToClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;
import static de.nierhain.danger.config.ConfigHandler.*;

public class AttributesHandler {


    private static double[] MODIFIER = {PLAYER_MODIFIER_HEALTH, PLAYER_MODIFIER_LUCK, PLAYER_MODIFIER_SPEED, PLAYER_MODIFIER_DMG, PLAYER_MODIFIER_AS};
    private static IAttribute[] ATTRIBUTES = {SharedMonsterAttributes.MAX_HEALTH,
            SharedMonsterAttributes.LUCK,
            SharedMonsterAttributes.MOVEMENT_SPEED,
            SharedMonsterAttributes.ATTACK_DAMAGE,
            SharedMonsterAttributes.ATTACK_SPEED};
    private static final int INCREMENT_PER_LEVEL = 1;
    private static double newAmount;
    private static final UUID[] DANGER_UUID = new UUID[]{UUID.fromString("d057381d-3e42-4e35-bc98-00857e3691b8"),
            UUID.fromString("d057381d-3e42-4e35-bc98-00857e3691b8"),
            UUID.fromString("d057381d-3e42-4e35-bc98-00857e3691b8"),
            UUID.fromString("d057381d-3e42-4e35-bc98-00857e3691b8"),
            UUID.fromString("d057381d-3e42-4e35-bc98-00857e3691b8")};

    private static IAttributes getHandler(Entity entity) {

        if(entity.hasCapability(CAPABILITY_ATTRIBUTES, null))
            return entity.getCapability(CAPABILITY_ATTRIBUTES, null);

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

    public static void purge(EntityPlayer player){
        for (IAttribute ATTRIBUTE : ATTRIBUTES) player.getEntityAttribute(ATTRIBUTE).removeAllModifiers();
    }

    public static void skill(Attribute attr, EntityPlayer player){
        if(canSkill(player)){
            int index = attr.getValue();
            getHandler(player).setAttribute(attr, getHandler(player).getAttribute(attr) + INCREMENT_PER_LEVEL);
            getHandler(player).removeSkillpoint();
            newAmount = getHandler(player).getAttribute(attr) * MODIFIER[index];

            if(player.getEntityAttribute(ATTRIBUTES[index]).getModifier(DANGER_UUID[index]) == null){
                player.getEntityAttribute(ATTRIBUTES[index]).applyModifier(new AttributeModifier(DANGER_UUID[index], ATTRIBUTES[index].getName(), newAmount, 0));
            }
            else if(player.getEntityAttribute(ATTRIBUTES[index]).getModifier(DANGER_UUID[index]).getAmount() != newAmount){
                player.getEntityAttribute(ATTRIBUTES[index]).removeModifier(player.getEntityAttribute(ATTRIBUTES[index]).getModifier(DANGER_UUID[index]));
                player.getEntityAttribute(ATTRIBUTES[index]).applyModifier(new AttributeModifier(DANGER_UUID[index], ATTRIBUTES[index].getName(), newAmount, 0));
            }

            // player needs to be healed after max health has changed
            if(attr == Attribute.HEALTH)
                player.setHealth(player.getMaxHealth());
        }
    }

}
