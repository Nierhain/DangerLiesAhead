package de.nierhain.danger.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.UUID;

import static de.nierhain.danger.config.ConfigHandler.*;
import static de.nierhain.danger.config.ConfigHandler.ModifierCategory.MOB_MODIFIER_ATTACK_DAMAGE;
import static de.nierhain.danger.config.ConfigHandler.ModifierCategory.MOB_MODIFIER_HEALTH;

public class MobSpawnHandler {


    private static BlockPos spawn;
    private static UUID[] uuid = {UUID.fromString("d057381d-3e42-4e35-bc98-00857e3691b8"), UUID.fromString("d057381d-3e42-4e35-bc98-00857e3691b8")};

    @SubscribeEvent
    public void onMobSpawn(EntityJoinWorldEvent event){
        if(event.getWorld().isRemote) return;
        if(DISABLE_MOB_LEVELING) return;
        if(!(event.getEntity() instanceof IMob) || PASSIVES_LEVELING) return;
        if(LOCK_DIMENSIONS && !Arrays.stream(ENABLED_DIMENSIONS).anyMatch(i -> i == event.getEntity().dimension)) return;
        if(event.getEntity().ticksExisted > 0) return;

        spawn = new BlockPos(event.getWorld().getWorldInfo().getSpawnX(), event.getWorld().getWorldInfo().getSpawnY(), event.getWorld().getWorldInfo().getSpawnZ());
        if(hasLevelUp((EntityLiving) event.getEntity())){
            levelStats((EntityLiving) event.getEntity());
        }
    }

    private void levelStats(EntityLiving mob){
        double healthAmount = getLevel(mob) * MOB_MODIFIER_HEALTH;
        double dmgAmount = getLevel(mob) * MOB_MODIFIER_ATTACK_DAMAGE;

        if(mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(uuid[0]) == null){
            mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier(uuid[0],"Mob Health Mod", healthAmount, 0));
            mob.setHealth(mob.getMaxHealth());
        }
        if(mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(uuid[0]).getAmount() != healthAmount){
            mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(uuid[0]));
            mob.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(new AttributeModifier(uuid[0],"Mob Health Mod", healthAmount, 0));
            mob.setHealth(mob.getMaxHealth());
        }

        if(mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null){
            if(mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(uuid[1]) == null){
                mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(new AttributeModifier(uuid[1],"Mob Attack Mod", dmgAmount, 0));
            }
            if(mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(uuid[1]).getAmount() != dmgAmount){
                mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(uuid[1]));
                mob.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(new AttributeModifier(uuid[1],"Mob Attack Mod", dmgAmount, 0));
            }
        }
    }

    private boolean hasLevelUp(EntityLiving mob){
        return getDistance(mob) >= DISTANCE_PER_LEVEL;
    }

    private int getLevel(EntityLiving mob){
        int level = (int) (getDistance(mob) / (DISTANCE_PER_LEVEL * 16));
        if(level >= MOB_MAX_LEVEL) {
            level = MOB_MAX_LEVEL;
        }
        return level;
    }

    private double getDistance(EntityLiving mob){
        double xDist = (int) (mob.posX - spawn.getX());
        double zDist = (int) (mob.posZ - spawn.getZ());
        return Math.sqrt(xDist * xDist + zDist * zDist);
    }
}
