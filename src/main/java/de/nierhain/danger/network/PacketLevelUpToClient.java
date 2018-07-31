package de.nierhain.danger.network;

import de.nierhain.danger.Danger;
import de.nierhain.danger.event.EventNotifyPlayer;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketLevelUpToClient implements IMessage {

    public PacketLevelUpToClient(){}

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PacketLevelUpToClient, IMessage>{

        @Override
        public IMessage onMessage(PacketLevelUpToClient message, MessageContext ctx) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer player = mc.player;
            mc.addScheduledTask(() -> {
                MinecraftForge.EVENT_BUS.post(new EventNotifyPlayer());
                player.getEntityWorld().playSound(player.posX, player.posY, player.posZ, Danger.levelUpSound, SoundCategory.PLAYERS, 1.0f, 1.0f, false);
            });
            return null;
        }
    }
}
