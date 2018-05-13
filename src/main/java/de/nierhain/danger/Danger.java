package de.nierhain.danger;

import java.util.concurrent.Callable;

import de.nierhain.danger.capabilities.level.DefaultLevelHandler;
import de.nierhain.danger.capabilities.level.ILevelHandler;
import de.nierhain.danger.capabilities.level.Storage;
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

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Danger {

	@Instance(Reference.MODID)
	public static Danger instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		CapabilityManager.INSTANCE.register(ILevelHandler.class, new Storage(), DefaultLevelHandler.class);
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
}
