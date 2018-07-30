package de.nierhain.danger.network;

import de.nierhain.danger.capabilities.attributes.IAttributes;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;

public class PacketSkillpointsToClient implements IMessage {

    private int toSend;

    public PacketSkillpointsToClient(){}

    public PacketSkillpointsToClient(int toSend){
        this.toSend = toSend;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.toSend = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.toSend);
    }

    public static class Handler implements IMessageHandler<PacketSkillpointsToClient, IMessage>{

        @Override
        public IMessage onMessage(PacketSkillpointsToClient message, MessageContext ctx) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            IAttributes attrObj = player.getCapability(CAPABILITY_ATTRIBUTES, null);
            attrObj.setSkillpoints(message.toSend);
            return null;
        }
    }
}
