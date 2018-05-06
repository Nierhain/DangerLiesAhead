package de.nierhain.danger.proxy;

import de.nierhain.danger.events.EventHandler;
import de.nierhain.danger.handler.CapabilitiesHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	
	public void reg() {
		MinecraftForge.EVENT_BUS.register(new CapabilitiesHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}

}
