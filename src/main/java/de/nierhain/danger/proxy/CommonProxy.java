package de.nierhain.danger.proxy;

import de.nierhain.danger.network.PacketHandler;
import de.nierhain.danger.registries.ModCapabilities;
import de.nierhain.danger.registries.ModCommands;
import de.nierhain.danger.registries.ModHandlers;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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
}
