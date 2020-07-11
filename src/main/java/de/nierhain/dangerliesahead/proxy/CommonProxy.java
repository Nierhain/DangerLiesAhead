package de.nierhain.dangerliesahead.proxy;

import de.nierhain.dangerliesahead.DangerLiesAhead;
import de.nierhain.dangerliesahead.blocks.BlockSafeBeacon;
import de.nierhain.dangerliesahead.handler.RecipeHandler;
import de.nierhain.dangerliesahead.items.ItemCreatureCompound;
import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.registries.ModBlocks;
import de.nierhain.dangerliesahead.registries.ModCapabilities;
import de.nierhain.dangerliesahead.registries.ModCommands;
import de.nierhain.dangerliesahead.registries.ModHandlers;
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

@Mod.EventBusSubscriber(modid = DangerLiesAhead.MODID)
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
