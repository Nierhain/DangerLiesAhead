package de.nierhain.danger.proxy;

import de.nierhain.danger.blocks.BeaconBlock;
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
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        ModCapabilities.registerCapabilities();
    }

    public void init(FMLInitializationEvent event){
        ModHandlers.registerHandlers();
    }

    public void postInit(FMLPostInitializationEvent event){
        PacketHandler.registerServerMessages();
    }

    public void serverLoad(FMLServerStartingEvent event){
        ModCommands.registerCommands(event);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BeaconBlock());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModBlocks.beaconBlock).setRegistryName(ModBlocks.beaconBlock.getRegistryName()));
        event.getRegistry().register(new ItemCreatureCompound());
    }

}
