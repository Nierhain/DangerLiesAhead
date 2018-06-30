package de.nierhain.danger.proxy;

import de.nierhain.danger.capabilities.level.DefaultLevel;
import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.StorageLevel;
import de.nierhain.danger.capabilities.skilling.DefaultSkilling;
import de.nierhain.danger.capabilities.skilling.ISkilling;
import de.nierhain.danger.capabilities.skilling.StorageSkilling;
import de.nierhain.danger.commands.CommandPurge;
import de.nierhain.danger.commands.CommandPurgeHealth;
import de.nierhain.danger.handler.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        CapabilityManager.INSTANCE.register(ILevel.class, new StorageLevel(), DefaultLevel::new);
        CapabilityManager.INSTANCE.register(ISkilling.class, new StorageSkilling(), DefaultSkilling::new);
    }

    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    public void postInit(FMLPostInitializationEvent event){

    }

    public void serverLoad(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandPurge());
        event.registerServerCommand(new CommandPurgeHealth());
    }
}
