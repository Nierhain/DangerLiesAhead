package de.nierhain.dangerliesahead.network;


import de.nierhain.dangerliesahead.capabilities.attributes.IAttributes;
import de.nierhain.dangerliesahead.enums.Attribute;
import de.nierhain.dangerliesahead.handler.PlayerHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static de.nierhain.dangerliesahead.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;



public class PacketAttributeToServer implements IMessage {
    public PacketAttributeToServer(){}

    private Attribute attribute;

    public PacketAttributeToServer(Attribute attribute){
        this.attribute = attribute;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.attribute = attribute.valueOf(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(attribute.getValue());
    }

    public static class Handler implements IMessageHandler<PacketAttributeToServer, IMessage> {

        @Override
        public IMessage onMessage(PacketAttributeToServer message, MessageContext ctx) {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            IAttributes skillsObj = serverPlayer.getCapability(CAPABILITY_ATTRIBUTES, null);

            serverPlayer.getServerWorld().addScheduledTask(() -> {
                PlayerHandler.skill(message.attribute, serverPlayer);
                PacketHandler.INSTANCE.sendTo(new PacketAttributesToClient(skillsObj.getAllAttributes(), skillsObj.getSkillpoints()), serverPlayer);
            });

            return null;
        }
    }
}