package de.nierhain.danger.network;


import de.nierhain.danger.capabilities.attributes.IAttributes;
import de.nierhain.danger.enums.Attribute;
import de.nierhain.danger.handler.AttributesHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_SKILL;



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
            IAttributes skillsObj = serverPlayer.getCapability(CAPABILITY_SKILL, null);

            serverPlayer.getServerWorld().addScheduledTask(() -> {
                AttributesHandler.skill(message.attribute, serverPlayer);
                PacketHandler.INSTANCE.sendTo(new PacketAttributesToClient(skillsObj.getAllAttributes(), skillsObj.getSkillpoints()), serverPlayer);
            });

            return null;
        }
    }
}