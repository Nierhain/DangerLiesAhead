package de.nierhain.dangerliesahead.proxy;

import de.nierhain.dangerliesahead.config.ConfigHandler;
import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.registries.ModCapabilities;
import de.nierhain.dangerliesahead.registries.ModCommands;
import de.nierhain.dangerliesahead.registries.ModHandlers;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        ModCapabilities.registerCapabilities();
        ConfigHandler.preInit();
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
}
