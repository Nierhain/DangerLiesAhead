package de.nierhain.dangerliesahead;

import de.nierhain.dangerliesahead.proxy.CommonProxy;
import de.nierhain.dangerliesahead.registries.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = DangerLiesAhead.MODID, name = DangerLiesAhead.NAME, version = DangerLiesAhead.VERSION)
public class DangerLiesAhead {
    public static final String MODID = "danger";
    public static final String NAME = "Danger Lies Ahead";
    public static final String VERSION = "v1.2.1";

    public static Logger dangerLogger = LogManager.getLogger(MODID);

    public static ResourceLocation levelUpSoundLocation = new ResourceLocation(MODID, "level_up");
    public static SoundEvent levelUpSound = new SoundEvent(levelUpSoundLocation);

    @SidedProxy(serverSide = "de.nierhain.danger.proxy.CommonProxy", clientSide = "de.nierhain.danger.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabDangerLiesAhead = new CreativeTabs("DangerLiesAhead") {
        @Override
        public ItemStack createIcon() {
            return  new ItemStack(ModItems.creatureCompound);
        }
    };
    @Mod.Instance(MODID)
    public static DangerLiesAhead instance;

//    @EventHandler
//    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
//        logger.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This jar file will NOT be supported by the author and you should get a valid copy ASAP!");
//    }

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
