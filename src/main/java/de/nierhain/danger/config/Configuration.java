package de.nierhain.danger.config;

import de.nierhain.danger.utils.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeInt;

@Config(modid = Reference.MODID, name =  "dangerliesahead" ,type = Config.Type.INSTANCE, category = "general")
public class Configuration {

        @Comment("Changes the health modifier per level up | default: 2")
        @Name("Health Modifier")
        public static double PLAYER_MODIFIER_HEALTH = 2;

        @Comment("Changes the luck modifier per level up | default: 1")
        @Name("Luck Modifier")
        public static double PLAYER_MODIFIER_LUCK = 1;

        @Comment("Changes the movement speed modifier per level up | default: 0.1")
        @Name("Movement Speed Modifier")
        public static double PLAYER_MODIFIER_SPEED = 0.1;

        @Comment("Changes the attack damage modifier per level up | default: 1")
        @Name("Attack Damage Modifier")
        public static double PLAYER_MODIFIER_DMG = 1;

        @Comment("Changes the attack speed modifier per level up | default: 1")
        @Name("Attack Speed Modifier")
        public static double PLAYER_MODIFIER_AS = 1;

        @Comment("Changes the max level a player can reach | default: 20")
        @Name("Max Level")
        public static int PLAYER_MAX_LVL = 20;

        @Comment({"As the amount of experienced needed per level is internally calculated by", "using a quadratic equation ( a * x^2 + b * x)", "you have to set the two coefficients a and b", "the equation is using. "})
        @Name("Coefficient a")
        public static double CONST_A = (1 / 8);
        @Name("Coefficient b")
        public static double CONST_B = (9 + (7 / 8));

        @Comment("Disables leveling of mobs | default: false")
        @Name("Disable Leveling")
        public static boolean DISABLE_MOB_LEVELING = false;

        @Comment("Number of chunks between mob and spawn to level the mob | default: 20")
        @RangeInt(min = 1)
        @Name("Chunk distance per Level")
        public static int DISTANCE_PER_LEVEL = 20;

        @Comment("changes the health a mob gets per level | default: 1")
        @Name("Mob Health Modifier")
        public static int MOB_MODIFIER_HEALTH = 1;

        @Comment("changes the amount of attack damage a mob gets per level | default: 0.3")
        @Name("Mob Attack Damage Modifier")
        public static double MOB_MODIFIER_ATTACK_DAMAGE = 0.3;

//    @Comment("Disable CustomNPCs Integration")
//    @Name("Disable CustomNPCs")
//    public static boolean DISABLE_CUSTOM_NPC = false;

}
