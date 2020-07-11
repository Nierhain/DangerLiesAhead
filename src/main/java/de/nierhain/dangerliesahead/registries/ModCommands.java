package de.nierhain.dangerliesahead.registries;

import de.nierhain.dangerliesahead.commands.CommandPurge;
import de.nierhain.dangerliesahead.commands.CommandReloadPlayer;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class ModCommands {

    public static void registerCommands(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandPurge());
        event.registerServerCommand(new CommandReloadPlayer());
    }
}
