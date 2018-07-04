package de.nierhain.danger.proxy;

import de.nierhain.danger.Danger;
import de.nierhain.danger.gui.HandlerGuiSkill;
import de.nierhain.danger.handler.KeyHandler;
import de.nierhain.danger.registries.ModHandlers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy {

    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void preInit(FMLPreInitializationEvent event){

        ModHandlers.registerClientHandlers(mc);
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){

        NetworkRegistry.INSTANCE.registerGuiHandler(Danger.instance, new HandlerGuiSkill());

        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new HandlerGuiSkill());

        super.postInit(event);
    }
}
