package de.nierhain.danger.registries;

import de.nierhain.danger.handler.*;
import de.nierhain.danger.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ModHandlers {

    public static void registerHandlers(){
        PacketHandler.registerMessages();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new LevelHandler());
        MinecraftForge.EVENT_BUS.register(new SkillsHandler());
        MinecraftForge.EVENT_BUS.register(new MobSpawnHandler());
    }

    public static void registerClientHandlers(Minecraft mc) {
        MinecraftForge.EVENT_BUS.register(new KeyHandler(mc));
    }
}
