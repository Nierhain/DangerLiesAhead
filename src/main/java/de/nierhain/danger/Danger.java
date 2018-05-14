package de.nierhain.danger;

import java.util.concurrent.Callable;

import de.nierhain.danger.capabilities.level.DefaultLevelHandler;
import de.nierhain.danger.capabilities.level.ILevelHandler;
import de.nierhain.danger.capabilities.level.StorageLevel;
import de.nierhain.danger.capabilities.skills.DefaultSkillHandler;
import de.nierhain.danger.capabilities.skills.ISkillHandler;
import de.nierhain.danger.capabilities.skills.StorageSkill;
import de.nierhain.danger.commands.InfoCommand;
import de.nierhain.danger.commands.PurgeCommand;
import de.nierhain.danger.gui.RenderGuiHandler;
import de.nierhain.danger.handler.CapabilitiesHandler;
import de.nierhain.danger.proxy.CommonProxy;
import de.nierhain.danger.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Danger {

	@Instance(Reference.MODID)
	public static Danger instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		CapabilityManager.INSTANCE.register(ILevelHandler.class, new StorageLevel(), DefaultLevelHandler.class);
		CapabilityManager.INSTANCE.register(ISkillHandler.class, new StorageSkill(), DefaultSkillHandler.class);
		MinecraftForge.EVENT_BUS.register(new CapabilitiesHandler());
		MinecraftForge.EVENT_BUS.register(new de.nierhain.danger.handler.EventHandler());
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
	}
	
	@Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new PurgeCommand());
        event.registerServerCommand(new InfoCommand());
    }
}
