package de.nierhain.danger.proxy;

import de.nierhain.danger.Danger;
import de.nierhain.danger.gui.HandlerGuiSkill;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        MinecraftForge.EVENT_BUS.register(new HandlerGuiSkill());
    }

    @Override
    public void init(FMLInitializationEvent event){
        super.init(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(Danger.instance, new HandlerGuiSkill());
    }
}
