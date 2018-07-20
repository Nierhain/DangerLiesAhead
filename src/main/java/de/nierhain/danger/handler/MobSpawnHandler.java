package de.nierhain.danger.handler;

import de.nierhain.danger.utils.Reference;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

import static de.nierhain.danger.config.Configuration.*;

public class MobSpawnHandler {


    private static BlockPos spawn;
    private BlockPos mobSpawn;
    private EntityLiving mob;

    @SubscribeEvent
    public void onMobSpawn(EntityJoinWorldEvent event){

        if(!(event.getEntity() instanceof IMob) || DISABLE_MOB_LEVELING)
            return;

        mob = (EntityLiving) event.getEntity();
        spawn = event.getWorld().getSpawnPoint();

        if(hasLevelUp()){
            levelStats();
        }
    }

    private void levelStats(){
        IAttributeInstance healthAttribute = mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        double healthAmount = getLevel() * MOB_MODIFIER_HEALTH;
        setAttribute(healthAttribute, healthAmount);

        IAttributeInstance dmgAttribute = mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);

        //slimes and shulkers do not have attack damage. throws error if left unchecked
        if(dmgAttribute != null){
            double dmgAmount = getLevel() * MOB_MODIFIER_ATTACK_DAMAGE;
            setAttribute(dmgAttribute, dmgAmount);
        }
    }

    private void setAttribute(IAttributeInstance attribute, double amount){

        double newAmount = attribute.getAttributeValue() + amount;
        AttributeModifier attrMod = new AttributeModifier(UUID.fromString(Reference.UUID), attribute.getAttribute().getName() + " Modifier", newAmount, 0);

        attribute.removeAllModifiers();
        attribute.applyModifier(attrMod);

        // Heal to full health as the mobs current health does not get changed; even on full health
        if(attribute.getAttribute().equals(SharedMonsterAttributes.MAX_HEALTH)){
            mob.setHealth(mob.getMaxHealth());
        }
    }

    private boolean hasLevelUp(){
        return getDistance() >= DISTANCE_PER_LEVEL;
    }

    private int getLevel(){
        return (int) getDistance() / DISTANCE_PER_LEVEL;
    }

    private double getDistance(){
        return spawn.getDistance(mob.chunkCoordX, mob.chunkCoordY, mob.chunkCoordZ);
    }
}
