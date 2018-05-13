package de.nierhain.danger.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageLevel implements IMessage{
	
	private int currentXP;
	private int currentLevel;

	@Override
	public void fromBytes(ByteBuf buf) {
		currentXP = buf.readInt();
		currentLevel = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(currentXP);
		buf.writeInt(currentLevel);
	}
	
	
	public static class MessageHandler implements IMessageHandler<MessageLevel, IMessage>{

		@Override
		public IMessage onMessage(MessageLevel message, MessageContext ctx) {
			EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
			
			return null;
		}
		
	}

}
