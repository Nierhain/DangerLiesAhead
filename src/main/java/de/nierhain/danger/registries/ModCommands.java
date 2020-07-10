package de.nierhain.danger.registries;

import de.nierhain.danger.commands.CommandPurge;
import de.nierhain.danger.commands.CommandReloadPlayer;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class ModCommands {

    public static void registerCommands(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandPurge());
        event.registerServerCommand(new CommandReloadPlayer());
    }
}
