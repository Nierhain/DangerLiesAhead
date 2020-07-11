package de.nierhain.dangerliesahead.registries;

import de.nierhain.dangerliesahead.config.ConfigHandler;
import de.nierhain.dangerliesahead.handler.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ModHandlers {

    public static void registerHandlers(){
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new LevelHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerHandler());
        MinecraftForge.EVENT_BUS.register(new MobSpawnHandler());
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
    }

    public static void registerClientHandlers(Minecraft mc) {
        MinecraftForge.EVENT_BUS.register(new KeyHandler(mc));
        MinecraftForge.EVENT_BUS.register(new NotificationHandler());
    }
}
