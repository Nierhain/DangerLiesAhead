package de.nierhain.danger.network;

import de.nierhain.danger.capabilities.level.ILevel;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static de.nierhain.danger.capabilities.level.ProviderLevel.CAPABILITY_LEVEL;

public class PacketLevelToClient implements IMessage {
    public PacketLevelToClient(){}
    private int level;
    private int xp;
    @Override
    public void fromBytes(ByteBuf buf) {
        level = buf.readInt();
        xp = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(level);
        buf.writeInt(xp);
    }

    public static class Handler implements IMessageHandler<PacketLevelToClient, IMessage>{

        @Override
        public IMessage onMessage(PacketLevelToClient message, MessageContext ctx) {
            Minecraft minecraft = Minecraft.getMinecraft();
            minecraft.addScheduledTask(() ->{
                EntityPlayer player = Minecraft.getMinecraft().player;
                ILevel level = player.getCapability(CAPABILITY_LEVEL, null);

                level.setLevel(message.level);
                level.setXP(message.xp);
            });
            return null;
        }
    }
}
