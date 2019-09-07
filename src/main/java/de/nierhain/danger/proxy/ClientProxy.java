package de.nierhain.danger.proxy;

import de.nierhain.danger.Danger;
import de.nierhain.danger.config.ConfigHandler;
import de.nierhain.danger.network.PacketHandler;
import de.nierhain.danger.registries.ModBlocks;
import de.nierhain.danger.registries.ModHandlers;
import de.nierhain.danger.registries.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
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