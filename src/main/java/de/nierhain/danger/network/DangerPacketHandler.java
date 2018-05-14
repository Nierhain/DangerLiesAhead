package de.nierhain.danger.network;

import de.nierhain.danger.util.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class DangerPacketHandler {
	
	public static final SimpleNetworkWrapper NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	private static int ID = 0;
	
	public DangerPacketHandler() {
	}
	
	public static void registerMessages() {
		NETWORK_WRAPPER.registerMessage(MessageLevel.MessageHandler.class, MessageLevel.class, ID++, Side.CLIENT );
		NETWORK_WRAPPER.registerMessage(MessageSkill.MessageHandler.class, MessageSkill.class, ID++, Side.CLIENT );
	}
}
