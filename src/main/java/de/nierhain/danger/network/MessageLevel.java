package de.nierhain.danger.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageLevel implements IMessage{
	
	private int xp;
	private int level;
	
	public MessageLevel(int xp, int level) {
		this.xp = xp;
		this.level = level;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		xp = buf.readInt();
		level = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(xp);
		buf.writeInt(level);
	}
	
	
	public class MessageHandler implements IMessageHandler<MessageLevel, IMessage>{

		@Override
		public IMessage onMessage(MessageLevel message, MessageContext ctx) {
			EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
			
			int xp = message.xp;
			int level = message.level;
			
			return null;
		}
		
	}

}
