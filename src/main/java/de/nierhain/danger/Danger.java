package de.nierhain.danger;

import de.nierhain.danger.proxy.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = Danger.MODID, name = Danger.NAME, version = Danger.VERSION, certificateFingerprint = "3b024834bcbc5c9badb97afe9e26ed9ab7182a7d")
public class Danger {
    public static final String MODID = "danger";
    public static final String NAME = "Danger Lies Ahead";
    public static final String VERSION = "v1.1.1";

    public static Logger logger = LogManager.getLogger(MODID);

    public static ResourceLocation levelUpSoundLocation = new ResourceLocation(MODID, "level_up");
    public static SoundEvent levelUpSound = new SoundEvent(levelUpSoundLocation);

    @SidedProxy(serverSide = "de.nierhain.danger.proxy.CommonProxy", clientSide = "de.nierhain.danger.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static Danger instance;

    @EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {

        logger.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author and you should get a valid copy ASAP!");
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
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
