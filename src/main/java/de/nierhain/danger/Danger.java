package de.nierhain.danger;

import de.nierhain.danger.gui.RenderGuiHandler;
import de.nierhain.danger.level.EventHandlerLevelUp;
import de.nierhain.danger.proxy.CommonProxy;
import de.nierhain.danger.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Danger {

	@Instance
	public static Danger instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventHandlerLevelUp());
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
	}
}
