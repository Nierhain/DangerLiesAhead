package de.nierhain.dangerliesahead;

import de.nierhain.dangerliesahead.blocks.BlockSafeBeacon;
import de.nierhain.dangerliesahead.registry.ModBlocks;
import de.nierhain.dangerliesahead.setup.ClientProxy;
import de.nierhain.dangerliesahead.setup.IProxy;
import de.nierhain.dangerliesahead.setup.ModSetup;
import de.nierhain.dangerliesahead.setup.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("dangerliesahead")
public class DangerLiesAhead
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static ModSetup setup = new ModSetup();

    public static final String MODID = "dangerliesahead";

    public DangerLiesAhead() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        setup.init();
        proxy.init();
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            blockRegistryEvent.getRegistry().register(new BlockSafeBeacon());
        }
        public static void onItemRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
            Item.Properties properties = new Item.Properties().group(setup.itemGroup);
            itemRegistryEvent.getRegistry().register(new BlockItem(ModBlocks.SAFEBEACON, properties).setRegistryName("safebeacon"));
        }
    }

    //private void doClientStuff(final FMLClientSetupEvent event) {}
    //private void enqueueIMC(final InterModEnqueueEvent event){}
    //private void processIMC(final InterModProcessEvent event){}
    //@SubscribeEvent
    //public void onServerStarting(FMLServerStartingEvent event) {}
}
