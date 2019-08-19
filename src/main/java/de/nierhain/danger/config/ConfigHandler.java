package de.nierhain.danger.config;

import de.nierhain.danger.Danger;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Danger.MODID, name = "dangerliesahead")
public class ConfigHandler {

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(Danger.MODID))
        {
            ConfigManager.sync(Danger.MODID, Config.Type.INSTANCE);
        }
    }


    @Config.LangKey("danger.config.xpmultiplier")
    @Config.Comment("Changes the experience multiplier | default: 1")
    public static double XP_MULTIPLIER = 1;

    @Config.LangKey("danger.config.player.maxlvl")
    @Config.Comment("Changes the max level a player can reach | default: 20")
    public static int PLAYER_MAX_LVL = 20;

    @Config.LangKey("danger.config.player.healthmod")
    @Config.Comment("Changes the health modifier per level up | default: 2")
    public static double PLAYER_MODIFIER_HEALTH = 2;

    @Config.LangKey("danger.config.player.luckmod")
    @Config.Comment("Changes the luck modifier per level up | default: 1")
    public static double PLAYER_MODIFIER_LUCK = 1;

    @Config.LangKey("danger.config.player.speed")
    @Config.Comment("Changes the movement speed modifier per level up | default: 0.01")
    public static double PLAYER_MODIFIER_SPEED = 0.01;

    @Config.LangKey("danger.config.player.dmgmod")
    @Config.Comment("Changes the attack damage modifier per level up | default: 1")
    public static double PLAYER_MODIFIER_DMG = 1;

    @Config.LangKey("danger.config.player.asmod")
    @Config.Comment("Changes the attack speed modifier per level up | default: 1")
    public static double PLAYER_MODIFIER_AS = 1;

    @Config.LangKey("danger.config.mob.disable")
    @Config.Comment("Disables leveling of mobs | default: false")
    public static boolean DISABLE_MOB_LEVELING = false;

    @Config.LangKey("danger.config.mob.maxlvl")
    @Config.Comment("Changes the max level a mob can reach | default: 15")
    public static int MOB_MAX_LEVEL = 15;

    @Config.LangKey("danger.config.mob.distance")
    @Config.Comment("Number of chunks between mob and spawn to level the mob | default: 20")
    public static double DISTANCE_PER_LEVEL = 20;

    @Config.LangKey("danger.config.mob.healthmod")
    @Config.Comment("Changes the health a mob gets per level | default: 1")
    public static double MOB_MODIFIER_HEALTH = 1;

    @Config.LangKey("danger.config.mod.dmgmod")
    @Config.Comment("Changes the amount of attack damage a mob gets per level | default: 0.3")
    public static double MOB_MODIFIER_ATTACK_DAMAGE = 0.3;

}