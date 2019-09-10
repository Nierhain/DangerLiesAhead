package de.nierhain.danger.handler;

import de.nierhain.danger.Danger;
import de.nierhain.danger.registries.ModBlocks;
import de.nierhain.danger.registries.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static de.nierhain.danger.config.ConfigHandler.BeaconCategory.BEACONS_CRAFTABLE;

public class RecipeHandler {

    public static void loadRecipes(){
        ResourceLocation group = new ResourceLocation("DangerLiesAhead");

        if(BEACONS_CRAFTABLE){
            GameRegistry.addShapedRecipe(new ResourceLocation(Danger.MODID, "Safe Beacon"), group, new ItemStack(ModBlocks.blockSafeBeacon, 1),     "ggg", "ici","drd", 'g', new ItemStack(Blocks.GLASS), 'i', new ItemStack(Items.IRON_INGOT), 'c', new ItemStack(ModItems.creatureCompound), 'd', new ItemStack(Items.DIAMOND), 'r', new ItemStack(Blocks.REDSTONE_BLOCK));
            GameRegistry.addShapedRecipe(new ResourceLocation(Danger.MODID, "Creature Compound"), group, new ItemStack(ModItems.creatureCompound, 1),     " s ", "reb", " p ", 's', new ItemStack(Items.SLIME_BALL), 'r', new ItemStack(Items.ROTTEN_FLESH), 'e', new ItemStack(Items.ENDER_PEARL), 'b', new ItemStack(Items.BONE), 'p', new ItemStack(Items.BLAZE_ROD));
        }

    }
}
