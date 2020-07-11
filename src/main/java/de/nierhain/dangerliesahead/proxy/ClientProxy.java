package de.nierhain.dangerliesahead.proxy;

import de.nierhain.dangerliesahead.network.PacketHandler;
import de.nierhain.dangerliesahead.registries.ModBlocks;
import de.nierhain.dangerliesahead.registries.ModHandlers;
import de.nierhain.dangerliesahead.registries.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void preInit(FMLPreInitializationEvent event){
        ModHandlers.registerClientHandlers(mc);
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event){
        PacketHandler.registerClientMessages();
        super.init(event);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModItems.initModels();
        ModBlocks.initModels();
    }

}