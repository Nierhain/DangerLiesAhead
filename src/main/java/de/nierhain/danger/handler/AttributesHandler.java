package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.capabilities.attributes.ProviderAttributes;
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


    private static final double DEFAULT_HEALTH = 20;
    private static final double DEFAULT_SPEED = 0.1;
    private static final double DEFAULT_DMG = 2;
    private static final double DEFAULT_AS = 4;
    private static final double DEFAULT_LUCK = 0;

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

        clone.setHealth(original.getHealth());
        clone.setHunger(original.getHunger());
        clone.setMovementSpeed(original.getMovementSpeed());
        clone.setAttackDamage(original.getAttackDamage());
        clone.setAttackSpeed(original.getAttackSpeed());
        clone.setLuck(original.getLuck());

    }

    @SubscribeEvent
    public void onPlayerLevelUp(EventLevelUp event){
        EntityPlayer player = event.getPlayer();
        IAttributes skills = getHandler(player);
        skills.setSkillpoints(skills.getSkillpoints() + 1);
        PacketHandler.INSTANCE.sendTo(new PacketSkillpointsToClient(skills.getSkillpoints()), (EntityPlayerMP) player);
    }

    public static void removeSkillpoint(EntityPlayer player){
        player.getCapability(CAPABILITY_SKILL, null).removeSkillpoint();
    }

    public static void addAttribute(IAttribute attribute, double newValue, EntityPlayer player){
        player.getEntityAttribute(attribute).setBaseValue(newValue);
        removeSkillpoint(player);
    }

    public static void skillHealth(EntityPlayer player){

        if(canSkill(player)){
            int newSkill = getHandler(player).getHealth() + 1;
            getHandler(player).setHealth(newSkill);

            double newHealth = (getHandler(player).getHealth() * PLAYER_MODIFIER_HEALTH ) + DEFAULT_HEALTH;
            addAttribute(SharedMonsterAttributes.MAX_HEALTH, newHealth, player);

            // player does not get healed when his max health changes, therefore we have to do it manually
            player.setHealth(player.getMaxHealth());
        }

    }

    public static void skillMovementSpeed(EntityPlayer player){
        if (canSkill(player)) {
            int newSkill = getHandler(player).getMovementSpeed() + 1;
            getHandler(player).setMovementSpeed(newSkill);

            double newSpeed = getHandler(player).getMovementSpeed() * PLAYER_MODIFIER_SPEED + DEFAULT_SPEED;
            addAttribute(SharedMonsterAttributes.MOVEMENT_SPEED, newSpeed, player);
        }
    }

    public static void skillAttackDamage(EntityPlayer player){
        if (canSkill(player)) {
            int newSkill = getHandler(player).getAttackDamage() + 1;
            getHandler(player).setAttackDamage(newSkill);

            double newDmg = getHandler(player).getAttackDamage() * PLAYER_MODIFIER_DMG + DEFAULT_DMG;
            addAttribute(SharedMonsterAttributes.ATTACK_DAMAGE, newDmg, player);
        }
    }

    public static void skillAttackSpeed(EntityPlayer player){
        if (canSkill(player)) {
            int newSkill = getHandler(player).getAttackSpeed() + 1;
            getHandler(player).setAttackSpeed(newSkill);


            double newAS = getHandler(player).getAttackSpeed() * PLAYER_MODIFIER_AS + DEFAULT_AS;
            addAttribute(SharedMonsterAttributes.ATTACK_SPEED, newAS, player);
        }
    }

    public static void skillLuck(EntityPlayer player){
        if (canSkill(player)) {
            int newSkill = getHandler(player).getLuck() + 1;
            getHandler(player).setLuck(newSkill);

            double newLuck = getHandler(player).getLuck() * PLAYER_MODIFIER_LUCK + DEFAULT_LUCK;
            addAttribute(SharedMonsterAttributes.LUCK, newLuck, player);
        }
    }

}
