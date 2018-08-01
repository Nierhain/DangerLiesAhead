package de.nierhain.danger.handler;

import de.nierhain.danger.capabilities.spawned.ISpawned;
import de.nierhain.danger.capabilities.spawned.ProviderSpawned;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

import static de.nierhain.danger.capabilities.spawned.ProviderSpawned.CAPABILITY_SPAWNED;
import static de.nierhain.danger.config.ConfigHandler.*;

public class MobSpawnHandler {


    private static BlockPos spawn;
    private EntityLiving mob;
    private static UUID[] uuid = {UUID.fromString("d057381d-3e42-4e35-bc98-00857e3691b8"), UUID.fromString("d057381d-3e42-4e35-bc98-00857e3691b8")};

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof IMob) {
            event.addCapability(new ResourceLocation("danger", "spawned"), new ProviderSpawned());
        }
    }

    @SubscribeEvent
    public void onMobSpawn(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof EntityPlayer)
            return;
        if(!(event.getEntity() instanceof IMob) || DISABLE_MOB_LEVELING)
            return;
        if(event.getWorld().isRemote)
            return;

        this.mob = (EntityLiving) event.getEntity();
        this.spawn = new BlockPos(event.getWorld().getWorldInfo().getSpawnX(), event.getWorld().getWorldInfo().getSpawnY(), event.getWorld().getWorldInfo().getSpawnZ());
        ISpawned isSpawned = mob.getCapability(CAPABILITY_SPAWNED, null);

        if(hasLevelUp() && !isSpawned.isSpawned()){
            levelStats();
            isSpawned.setSpawned(true);
        }
    }

    private void levelStats(){
        double healthAmount = getLevel() * MOB_MODIFIER_HEALTH;
        double dmgAmount = getLevel() * MOB_MODIFIER_ATTACK_DAMAGE;

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

    private boolean hasLevelUp(){
        return getDistance() >= DISTANCE_PER_LEVEL;
    }

    private int getLevel(){
        int level = (int) (getDistance() / (DISTANCE_PER_LEVEL * 16));
        if(level >= MOB_MAX_LEVEL) {
            level = MOB_MAX_LEVEL;
        }
        return level;
    }

    private double getDistance(){
        double xDist = (int) (mob.posX - spawn.getX());
        double zDist = (int) (mob.posZ - spawn.getZ());
        return Math.sqrt(xDist * xDist + zDist * zDist);
    }
}
