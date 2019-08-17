package de.nierhain.dangerliesahead.proxy;

import de.nierhain.dangerliesahead.config.ConfigHandler;
import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.registries.ModHandlers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
        ModHandlers.registerClientHandlers(mc);
        ConfigHandler.clientPreInit();
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
        PacketHandler.registerClientMessages();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
