package de.nierhain.danger;

import de.nierhain.danger.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Danger.MODID, name = Danger.NAME, version = Danger.VERSION)
public class Danger {
    public static final String MODID = "danger";
    public static final String NAME = "Danger Lies Ahead";
    public static final String VERSION = "v1.0";

    public static Logger logger = LogManager.getLogger(MODID);

    @SidedProxy(serverSide = "de.nierhain.danger.proxy.CommonProxy", clientSide = "de.nierhain.danger.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static Danger instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
        logger.info("DLA preInit over");
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
        logger.info("DLA init over");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
        logger.info("DLA postInit over");
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event){
        proxy.serverLoad(event);
        logger.info("DLA Server started");
    }
}
