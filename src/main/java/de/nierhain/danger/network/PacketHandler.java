package de.nierhain.danger.network;

import de.nierhain.danger.Danger;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler{

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Danger.MODID);

    private static final byte ATTRIBUTES_TO_CLIENT_ID = 0,
                              ATTRIBUTES_TO_SERVER_ID = 1,
                              SKILLPOINTS_TO_CLIENT_ID = 2,
                              LEVEL_TO_CLIENT_ID = 3,
    LEVEL_UP_TO_CLIENT_ID = 4;

    public static void registerServerMessages(){
        INSTANCE.registerMessage(PacketAttributeToServer.Handler.class, PacketAttributeToServer.class, ATTRIBUTES_TO_SERVER_ID, Side.SERVER);
        INSTANCE.registerMessage(PacketHandlerOnServerDummy.Attributes.class, PacketAttributesToClient.class, ATTRIBUTES_TO_CLIENT_ID, Side.SERVER);
        INSTANCE.registerMessage(PacketHandlerOnServerDummy.Skillpoints.class, PacketSkillpointsToClient.class, SKILLPOINTS_TO_CLIENT_ID, Side.SERVER);
        INSTANCE.registerMessage(PacketHandlerOnServerDummy.Levels.class, PacketLevelToClient.class, LEVEL_TO_CLIENT_ID, Side.SERVER);
        INSTANCE.registerMessage(PacketHandlerOnServerDummy.Notification.class, PacketLevelUpToClient.class, LEVEL_UP_TO_CLIENT_ID, Side.SERVER);

    }

    public static void registerClientMessages(){
        INSTANCE.registerMessage(PacketAttributesToClient.Handler.class, PacketAttributesToClient.class, ATTRIBUTES_TO_CLIENT_ID, Side.CLIENT);
        INSTANCE.registerMessage(PacketSkillpointsToClient.Handler.class, PacketSkillpointsToClient.class, SKILLPOINTS_TO_CLIENT_ID, Side.CLIENT);
        INSTANCE.registerMessage(PacketLevelToClient.Handler.class, PacketLevelToClient.class, LEVEL_TO_CLIENT_ID, Side.CLIENT);
        INSTANCE.registerMessage(PacketLevelUpToClient.Handler.class, PacketLevelUpToClient.class, LEVEL_UP_TO_CLIENT_ID, Side.CLIENT);
    }

}
