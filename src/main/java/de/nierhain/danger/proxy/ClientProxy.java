package de.nierhain.danger.proxy;

import de.nierhain.danger.registries.ModHandlers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void preInit(FMLPreInitializationEvent event){

        ModHandlers.registerClientHandlers(mc);
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){

        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        super.postInit(event);
    }
}
