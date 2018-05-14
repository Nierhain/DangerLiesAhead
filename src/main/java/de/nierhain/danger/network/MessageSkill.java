package de.nierhain.danger.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSkill implements IMessage{
	
	private int availablePoints;
	private int usedPoints;
	
	public MessageSkill(int available, int used) {
		this.availablePoints = available;
		this.usedPoints = used;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		availablePoints = buf.readInt();
		usedPoints = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(availablePoints);
		buf.writeInt(usedPoints);
	}
	
	public static class MessageHandler implements IMessageHandler<MessageSkill, IMessage>{

		@Override
		public IMessage onMessage(MessageSkill message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			
			int availablePoints = message.availablePoints;
			int usedPoints = message.usedPoints;
			return null;
		}
		
	}

}
