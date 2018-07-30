package de.nierhain.danger.registries;

import de.nierhain.danger.gui.GuiLevelUp;
import de.nierhain.danger.gui.NotificationLevelUp;
import de.nierhain.danger.handler.*;
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
        MinecraftForge.EVENT_BUS.register(new KeyHandler(mc));
        MinecraftForge.EVENT_BUS.register(new NotificationLevelUp());
    }
}
