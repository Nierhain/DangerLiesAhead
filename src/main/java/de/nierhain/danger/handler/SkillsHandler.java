package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.skills.ISkills;
import de.nierhain.danger.capabilities.skills.ProviderSkills;
import de.nierhain.danger.event.EventLevelUp;
import de.nierhain.danger.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

import static de.nierhain.danger.capabilities.skills.ProviderSkills.CAPABILITY_SKILL;

public class SkillsHandler {

    private static final double MODIFIER_HEALTH = 2;
    private static final double MODIFIER_SPEED = 0.1;
    private static final double MODIFIER_DMG = 1;
    private static final double MODIFIER_AS = 1;
    private static final double MODIFIER_LUCK = 1;

    private static final double DEFAULT_HEALTH = 20;
    private static final double DEFAULT_SPEED = 0.1;
    private static final double DEFAULT_DMG = 2;
    private static final double DEFAULT_AS = 4;
    private static final double DEFAULT_LUCK = 0;

    private static ISkills getHandler(Entity entity) {

        if(entity.hasCapability(CAPABILITY_SKILL, null))
            return entity.getCapability(CAPABILITY_SKILL, null);

        return null;
    }

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(new ResourceLocation("danger", "skills"), new ProviderSkills());
        }
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone event) {

        final ISkills original = getHandler(event.getOriginal());
        final ISkills clone = getHandler(event.getEntityPlayer());

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
        ISkills skills = getHandler(player);
        skills.setSkillpoints(skills.getSkillpoints() + 1);
        event.getPlayer().sendMessage(new TextComponentString("Level up! New skillpoint available"));
    }

    public static void removeSkillpoint(EntityPlayer player){
        player.getCapability(CAPABILITY_SKILL, null).removeSkillpoint();
    }

    public static void addAttribute(IAttribute attribute, AttributeModifier mod, EntityPlayer player){
        IAttributeInstance attrInstance = player.getEntityAttribute(attribute);
        attrInstance.removeAllModifiers();
        attrInstance.applyModifier(mod);
    }

    public static void skillHealth(EntityPlayer player){
        int newSkill = getHandler(player).getHealth() + 1;
        getHandler(player).setHealth(newSkill);

        double newHealth = (getHandler(player).getHealth() * MODIFIER_HEALTH ) + DEFAULT_HEALTH;
        AttributeModifier healthMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Health Modifier", newHealth, 0);

        addAttribute(SharedMonsterAttributes.MAX_HEALTH, healthMod, player);
    }

    public static void skillHunger(EntityPlayer player){

    }

    public static void skillMovementSpeed(EntityPlayer player){
        int newSkill = getHandler(player).getMovementSpeed() + 1;
        getHandler(player).setMovementSpeed(newSkill);

         double newSpeed = getHandler(player).getMovementSpeed() * MODIFIER_SPEED + DEFAULT_SPEED;
         AttributeModifier moveMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Movement Speed Modifier", newSpeed, 0 );
         addAttribute(SharedMonsterAttributes.MOVEMENT_SPEED, moveMod, player);
    }

    public static void skillAttackDamage(EntityPlayer player){
        int newSkill = getHandler(player).getAttackDamage() + 1;
        getHandler(player).setAttackDamage(newSkill);

        double newDmg = getHandler(player).getAttackDamage() * MODIFIER_DMG + DEFAULT_DMG;
        AttributeModifier dmgMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Attack Damage Modifier", newDmg, 0);
        addAttribute(SharedMonsterAttributes.ATTACK_DAMAGE, dmgMod, player);
    }

    public static void skillAttackSpeed(EntityPlayer player){
        int newSkill = getHandler(player).getAttackSpeed() + 1;
        getHandler(player).setAttackSpeed(newSkill);

        double newAS = getHandler(player).getAttackSpeed() * MODIFIER_AS + DEFAULT_AS;
        AttributeModifier asMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Attack Speed Modifier", newAS, 0);
        addAttribute(SharedMonsterAttributes.ATTACK_SPEED, asMod, player);
    }

    public static void skillLuck(EntityPlayer player){
        int newSkill = getHandler(player).getLuck() + 1;
        getHandler(player).setLuck(newSkill);

        double newLuck = getHandler(player).getLuck() * MODIFIER_LUCK + DEFAULT_LUCK;
        AttributeModifier luckMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Luck Modifier", newLuck, 0);
        addAttribute(SharedMonsterAttributes.LUCK, luckMod, player);
    }

}
