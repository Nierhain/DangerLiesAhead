package de.nierhain.dangerliesahead.registries;

import de.nierhain.dangerliesahead.handler.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ModHandlers {

    public static void registerHandlers(){
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new LevelHandler());
        MinecraftForge.EVENT_BUS.register(new AttributesHandler());
        MinecraftForge.EVENT_BUS.register(new MobSpawnHandler());
    }

    public static void registerClientHandlers(Minecraft mc) {
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        MinecraftForge.EVENT_BUS.register(new NotificationHandler());
    }
}
