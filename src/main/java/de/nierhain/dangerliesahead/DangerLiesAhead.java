package de.nierhain.dangerliesahead;

import de.nierhain.dangerliesahead.proxy.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Logger;


@Mod(modid = DangerLiesAhead.MODID, name = DangerLiesAhead.NAME, version = DangerLiesAhead.VERSION)
public class DangerLiesAhead {
    public static final String MODID = "dangerliesahead";
    public static final String NAME = "Danger Lies Ahead";
    public static final String VERSION = "v1.0.1";

    public static Logger logger;

    public static ResourceLocation levelUpSoundLocation = new ResourceLocation(MODID, "level_up");
    public static SoundEvent levelUpSound = new SoundEvent(levelUpSoundLocation);

    @SidedProxy(serverSide = "de.nierhain.danger.proxy.CommonProxy", clientSide = "de.nierhain.dangerliesahead.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static DangerLiesAhead instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event){
        proxy.serverLoad(event);
    }
}
