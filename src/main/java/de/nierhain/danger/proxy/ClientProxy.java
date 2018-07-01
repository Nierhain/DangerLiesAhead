package de.nierhain.danger.proxy;

import de.nierhain.danger.gui.HandlerGuiSkill;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        MinecraftForge.EVENT_BUS.register(new HandlerGuiSkill());
    }
}
