package de.nierhain.danger.proxy;

import de.nierhain.danger.capabilities.level.DefaultLevel;
import de.nierhain.danger.capabilities.level.ILevel;
import de.nierhain.danger.capabilities.level.StorageLevel;
import de.nierhain.danger.capabilities.skills.DefaultSkills;
import de.nierhain.danger.capabilities.skills.ISkills;
import de.nierhain.danger.capabilities.skills.StorageSkills;
import de.nierhain.danger.commands.CommandPurge;
import de.nierhain.danger.commands.CommandPurgeHealth;
import de.nierhain.danger.handler.EventHandler;
import de.nierhain.danger.handler.LevelHandler;
import de.nierhain.danger.handler.SkillsHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event){
        CapabilityManager.INSTANCE.register(ILevel.class, new StorageLevel(), DefaultLevel::new);
        CapabilityManager.INSTANCE.register(ISkills.class, new StorageSkills(), DefaultSkills::new);
    }

    public void init(FMLInitializationEvent event){

        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new LevelHandler());
        MinecraftForge.EVENT_BUS.register(new SkillsHandler());
    }

    public void postInit(FMLPostInitializationEvent event){

    }

    public void serverLoad(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandPurge());
        event.registerServerCommand(new CommandPurgeHealth());
    }
}
