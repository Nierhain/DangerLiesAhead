package de.nierhain.danger.network;

import de.nierhain.danger.utils.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler{

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

    private static final byte ATTRIBUTES_TO_CLIENT_ID = 0,
                              ATTRIBUTES_TO_SERVER_ID = 1,
                              SKILLPOINTS_TO_CLIENT_ID = 2;

    public static void registerServerMessages(){
        INSTANCE.registerMessage(PacketAttributeToServer.Handler.class, PacketAttributeToServer.class, ATTRIBUTES_TO_SERVER_ID, Side.SERVER);
        INSTANCE.registerMessage(PacketHandlerOnServerDummy.Attributes.class, PacketAttributesToClient.class, ATTRIBUTES_TO_CLIENT_ID, Side.SERVER);
        INSTANCE.registerMessage(PacketHandlerOnServerDummy.Skillpoints.class, PacketSkillpointsToClient.class, SKILLPOINTS_TO_CLIENT_ID, Side.SERVER);

    }

    public static void registerClientMessages(){
        INSTANCE.registerMessage(PacketAttributesToClient.Handler.class, PacketAttributesToClient.class, ATTRIBUTES_TO_CLIENT_ID, Side.CLIENT);
        INSTANCE.registerMessage(PacketSkillpointsToClient.Handler.class, PacketSkillpointsToClient.class, SKILLPOINTS_TO_CLIENT_ID, Side.CLIENT);
    }

}
