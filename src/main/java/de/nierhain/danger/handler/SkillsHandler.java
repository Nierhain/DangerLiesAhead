package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.skills.ISkills;
import de.nierhain.danger.capabilities.skills.ProviderSkills;
import de.nierhain.danger.event.EventLevelUp;
import de.nierhain.danger.utils.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

import static de.nierhain.danger.capabilities.skills.ProviderSkills.CAPABILITY_SKILL;

public class SkillsHandler {

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
        final ISkills clone = getHandler(event.getEntity());

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

    }

    private void skillHealth(EntityPlayer player){
        IAttributeInstance healthAttribute = player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        float oldHealth = player.getMaxHealth() - 20;
        AttributeModifier healthMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Health Modifier", oldHealth + 2, 0);
        healthAttribute.removeAllModifiers();
        healthAttribute.applyModifier(healthMod);
    }

    public void skillHunger(EntityPlayer player){
    }

    public void skillMovementSpeed(EntityPlayer player){
         IAttributeInstance movementSpeed = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
         double oldSpeed = movementSpeed.getBaseValue();
         AttributeModifier moveMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Movement Speed Modifier", oldSpeed + 0.1, 0 );
         movementSpeed.removeAllModifiers();
         movementSpeed.applyModifier(moveMod);
    }

    public void skillAttackDamage(EntityPlayer player){
        IAttributeInstance dmgAttribute = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        double oldDamage = dmgAttribute.getBaseValue();
        AttributeModifier dmgMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Attack Damage Modifier", oldDamage + 1, 0);
        dmgAttribute.removeAllModifiers();
        dmgAttribute.applyModifier(dmgMod);
    }

    public void skillAttackSpeed(EntityPlayer player){
        IAttributeInstance asAttribute = player.getEntityAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        double oldAS = asAttribute.getBaseValue();
        AttributeModifier asMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Attack Speed Modifier", oldAS + 1, 0);
        asAttribute.removeAllModifiers();
        asAttribute.applyModifier(asMod);
    }

    public void skillLuck(EntityPlayer player){
        IAttributeInstance luckAttribute = player.getEntityAttribute(SharedMonsterAttributes.LUCK);
        double oldLuck = luckAttribute.getBaseValue();
        AttributeModifier luckMod = new AttributeModifier(UUID.fromString(Reference.UUID), "Luck Modifier", oldLuck + 1, 0);
        luckAttribute.removeAllModifiers();
        luckAttribute.applyModifier(luckMod);
    }

}
