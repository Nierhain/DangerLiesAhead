package de.nierhain.danger.proxy;

import de.nierhain.danger.Danger;
import de.nierhain.danger.blocks.BlockSafeBeacon;
import de.nierhain.danger.handler.RecipeHandler;
import de.nierhain.danger.items.ItemCreatureCompound;
import de.nierhain.danger.network.PacketHandler;
import de.nierhain.danger.registries.ModBlocks;
import de.nierhain.danger.registries.ModCapabilities;
import de.nierhain.danger.registries.ModCommands;
import de.nierhain.danger.registries.ModHandlers;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Danger.MODID)
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        ModCapabilities.registerCapabilities();
    }

    public void init(FMLInitializationEvent event){
        ModHandlers.registerHandlers();
        RecipeHandler.loadRecipes();
    }

    public void postInit(FMLPostInitializationEvent event){
        PacketHandler.registerServerMessages();
    }

    public void serverLoad(FMLServerStartingEvent event){
        ModCommands.registerCommands(event);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                new BlockSafeBeacon()
        );
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new ItemBlock(ModBlocks.blockSafeBeacon).setRegistryName(ModBlocks.blockSafeBeacon.getRegistryName()),
                new ItemCreatureCompound()
        );
    }
}
