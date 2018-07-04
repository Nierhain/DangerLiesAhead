package de.nierhain.danger.proxy;

import de.nierhain.danger.Danger;
import de.nierhain.danger.gui.HandlerGuiSkill;
import de.nierhain.danger.handler.KeyHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.FMLThrowingEventBus;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    private static KeyBinding[] binds = new KeyBinding[1];

    public static KeyBinding[] getBinds(){
        return binds;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){
        NetworkRegistry.INSTANCE.registerGuiHandler(Danger.instance, new HandlerGuiSkill());
        MinecraftForge.EVENT_BUS.register(new KeyHandler());

        binds[0] = new KeyBinding("key.gui.desc", Keyboard.KEY_X, "key.danger.category");
        ClientRegistry.registerKeyBinding(binds[0]);
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new HandlerGuiSkill());
        super.postInit(event);
    }
}
