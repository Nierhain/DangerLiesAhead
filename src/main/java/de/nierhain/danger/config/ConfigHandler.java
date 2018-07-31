package de.nierhain.danger.config;

import de.nierhain.danger.Danger;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.Int;

import java.io.File;

public class ConfigHandler {

    public static double CONST_A;
    public static double CONST_B;
    public static int PLAYER_MAX_LVL;
    public static double PLAYER_MODIFIER_HEALTH;
    public static double PLAYER_MODIFIER_LUCK;
    public static double PLAYER_MODIFIER_SPEED;
    public static double PLAYER_MODIFIER_DMG;
    public static double PLAYER_MODIFIER_AS;

    public static boolean DISABLE_MOB_LEVELING;
    public static int MOB_MAX_LEVEL;
    public static int DISTANCE_PER_LEVEL;
    public static int MOB_MODIFIER_HEALTH;
    public static double MOB_MODIFIER_ATTACK_DAMAGE;

    public static final String CATEGORY_NAME_EXPERIENCE = "category_experience";
    public static final String CATEGORY_NAME_PLAYER = "category_player";
    public static final String CATEGORY_NAME_MOBS = "category_mobs";
    private static String coefficient = "As the amount of experienced needed per level is internally calculated by using a\nquadratic equation ( a * x^2 + b * x), you have to set the two coefficients a and b the equation is using. ";

    public static void preInit(){
        File configFile = new File(Loader.instance().getConfigDir(), "DangerLiesAhead.cfg");
        config = new Configuration(configFile);
        syncFromFile();
    }

    public static void clientPreInit(){
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
    }

    public static void syncFromFile(){
        syncConfig(true, true);
    }

    public static void syncFromGUI(){
        syncConfig(false, true);
    }

    public static void syncFromFields(){
        syncConfig(false, false);
    }

    private static void syncConfig(boolean loadConfigFromFile, boolean readFieldsFromConfig){
        if (loadConfigFromFile){
            config.load();
        }

        config.setCategoryComment(CATEGORY_NAME_EXPERIENCE, coefficient.toString());

        // DEFAULT VALUES
        final double defConstA = (double)(1/8);
        final double defConstB = (double)(9+(7/8));
        final int defPMaxLvl = 20;
        final int defPModH = 2;
        final int defPModL = 1;
        final double defPModS = 0.01;
        final int defPModAD = 1;
        final int defPModAS = 1;

        final boolean defMobSpawn = false;
        final int defMobMaxLvl = 15;
        final int defMobDist = 20;
        final int defMMobH = 2;
        final double defMMobAD = 0.3;

        Property propConstA = config.get(CATEGORY_NAME_EXPERIENCE, "CONST_A", defConstA, "Coefficient a", 0, Double.MAX_VALUE);
        Property propConstB = config.get(CATEGORY_NAME_EXPERIENCE, "CONST_B", defConstB, "Coefficient b", Double.MIN_VALUE, Double.MAX_VALUE);

        Property propPlayerMaxLevel = config.get(CATEGORY_NAME_PLAYER, "PLAYER_MAX_LVL", defPMaxLvl, "Changes the max level a player can reach | default: 20", 0, Int.MaxValue());
        Property propPlayerModHealth = config.get(CATEGORY_NAME_PLAYER, "PLAYER_MODIFIER_HEALTH", defPModH, "Changes the health modifier per level up | default: 2");
        Property propPlayerModLuck = config.get(CATEGORY_NAME_PLAYER, "PLAYER_MODIFIER_LUCK", defPModL, "Changes the luck modifier per level up | default: 1");
        Property propPlayerModSpeed = config.get(CATEGORY_NAME_PLAYER, "PLAYER_MODIFIER_SPEED", defPModS, "Changes the movement speed modifier per level up | default: 0.01");
        Property propPlayerModDmg = config.get(CATEGORY_NAME_PLAYER, "PLAYER_MODIFIER_DMG", defPModAD, "Changes the attack damage modifier per level up | default: 1");
        Property propPlayerModAs = config.get(CATEGORY_NAME_PLAYER, "PLAYER_MODIFIER_AS", defPModAS, "Changes the attack speed modifier per level up | default: 1");

        Property propDisableMobLeveling = config.get(CATEGORY_NAME_MOBS, "DISABLE_MOB_LEVELING", defMobSpawn, "Disables leveling of mobs | default: false");
        Property propMobMaxLvl = config.get(CATEGORY_NAME_MOBS, "MOB_MAX_LEVEL", defMobMaxLvl, "Changes the max level a player can reach | default: 15");
        Property propMobDistance = config.get(CATEGORY_NAME_MOBS, "DISTANCE_PER_LEVEL", defMobDist, "Number of chunks between mob and spawn to level the mob | default: 20");
        Property propMobModHealth = config.get(CATEGORY_NAME_MOBS, "MOB_MODIFIER_HEALTH", defMMobH, "Changes the health a mob gets per level | default: 1");
        Property propMobModDmg = config.get(CATEGORY_NAME_MOBS, "MOB_MODIFIER_ATTACK_DAMAGE", defMMobAD, "Changes the amount of attack damage a mob gets per level | default: 0.3");

        if(readFieldsFromConfig){
            CONST_A = propConstA.getDouble(defConstA);
            CONST_B = propConstB.getDouble(defConstB);

            PLAYER_MAX_LVL = propPlayerMaxLevel.getInt(defPMaxLvl);
            PLAYER_MODIFIER_HEALTH = propPlayerModHealth.getDouble(defPModH);
            PLAYER_MODIFIER_LUCK = propPlayerModLuck.getInt(defPModL);
            PLAYER_MODIFIER_SPEED = propPlayerModSpeed.getDouble(defPModS);
            PLAYER_MODIFIER_DMG = propPlayerModDmg.getInt(defPModAD);
            PLAYER_MODIFIER_AS = propPlayerModAs.getInt(defPModAS);

            DISABLE_MOB_LEVELING = propDisableMobLeveling.getBoolean(defMobSpawn);
            MOB_MAX_LEVEL = propMobMaxLvl.getInt(defMobMaxLvl);
            DISTANCE_PER_LEVEL = propMobDistance.getInt(defMobDist);
            MOB_MODIFIER_HEALTH = propMobModHealth.getInt(defMMobH);
            MOB_MODIFIER_ATTACK_DAMAGE = propMobModDmg.getDouble(defMMobAD);
        }

        propConstA.set(CONST_A);
        propConstB.set(CONST_B);
        propPlayerMaxLevel.set(PLAYER_MAX_LVL);
        propPlayerModHealth.set(PLAYER_MODIFIER_HEALTH);
        propPlayerModLuck.set(PLAYER_MODIFIER_LUCK);
        propPlayerModSpeed.set(PLAYER_MODIFIER_SPEED);
        propPlayerModDmg.set(PLAYER_MODIFIER_DMG);
        propPlayerModAs.set(PLAYER_MODIFIER_AS);

        propDisableMobLeveling.set(DISABLE_MOB_LEVELING);
        propMobDistance.set(DISTANCE_PER_LEVEL);
        propMobMaxLvl.set(MOB_MAX_LEVEL);
        propMobModDmg.set(MOB_MODIFIER_ATTACK_DAMAGE);
        propMobModHealth.set(MOB_MODIFIER_HEALTH);

        if(config.hasChanged()){
            config.save();
        }
    }
    private static Configuration config = null;

    public static class ConfigEventHandler {

        @SubscribeEvent(priority = EventPriority.NORMAL)
        public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event){
            if(Danger.MODID.equals(event.getModID()) && !event.isWorldRunning()){
                if(event.getConfigID().equals(CATEGORY_NAME_PLAYER) || event.getConfigID().equals(CATEGORY_NAME_MOBS)){
                    syncFromGUI();
                }
            }
        }
    }

}
