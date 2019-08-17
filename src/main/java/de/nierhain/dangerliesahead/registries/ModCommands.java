package de.nierhain.dangerliesahead.registries;

import de.nierhain.dangerliesahead.commands.CommandPurge;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class ModCommands {

    public static void registerCommands(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandPurge());
    }
}
