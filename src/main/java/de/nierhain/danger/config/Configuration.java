package de.nierhain.danger.config;

import de.nierhain.danger.utils.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;

@Config(modid = Reference.MODID, name =  "dangerliesahead" ,type = Config.Type.INSTANCE, category = "general")
public class Configuration {

    //  ATTRIBUTES HANDLER

    @Comment("Changes the health modifier per level up")
    @RangeDouble
    public static double PLAYER_MODIFIER_HEALTH = 2;

    @Comment("Changes the luck modifier per level up")
    @RangeDouble
    public static double PLAYER_MODIFIER_LUCK = 1;

    @Comment("Changes the movement speed modifier per level up")
    @RangeDouble
    public static double PLAYER_MODIFIER_SPEED = 0.1;

    @Comment("Changes the attack damage modifier per level up")
    @RangeDouble
    public static double PLAYER_MODIFIER_DMG = 1;

    @Comment("Changes the attack damage modifier per level up")
    @RangeDouble
    public static double PLAYER_MODIFIER_AS = 1;



    //MAP LEVELS
    @Comment("Changes the max level a player can reach")
    @RangeInt
    public static int PLAYER_MAX_LVL = 20;

    @Comment({"As the amount of experienced needed per level is internally calculated by", "using a quadratic equation ( a * x^2 + b * x)", "you have to set the two coefficients a and b", "the equation is using. "})
    @RangeDouble
    public static double CONST_A = 1 / 8;
    @RangeDouble
    public static double CONST_B = 9 + (7 / 8);

    // MOB SPAWN HANDLER

    @Comment("Number of chunks between mob and spawn to level the mob")
    @RangeInt
    public static int DISTANCE_PER_LEVEL = 50;

    @Comment("changes the health a mob gets per level")
    @RangeInt
    public static int MOB_MODIFIER_HEALTH = 5;

    @Comment("changes the amount of attack damage a mob gets per level")
    @RangeInt
    public static int MOB_MODIFIER_ATTACK_DAMAGE = 1;
}
