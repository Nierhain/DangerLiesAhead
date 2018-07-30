package de.nierhain.danger.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerOnServerDummy {

    public static class Attributes implements IMessageHandler<PacketAttributesToClient, IMessage>{
        @Override
        public IMessage onMessage(PacketAttributesToClient message, MessageContext ctx) {
            return null;
        }
    }

    public static class Skillpoints implements IMessageHandler<PacketSkillpointsToClient, IMessage>{
        @Override
        public IMessage onMessage(PacketSkillpointsToClient message, MessageContext ctx) {
            return null;
        }
    }

    public static class Levels implements IMessageHandler<PacketLevelToClient, IMessage>{
        @Override
        public IMessage onMessage(PacketLevelToClient message, MessageContext ctx) { return null;}
    }

    public static class Notification implements IMessageHandler<PacketLevelUpToClient, IMessage>{

        @Override
        public IMessage onMessage(PacketLevelUpToClient message, MessageContext ctx) {
            return null;
        }
    }

}
