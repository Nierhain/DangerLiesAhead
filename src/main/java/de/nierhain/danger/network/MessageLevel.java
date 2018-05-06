package de.nierhain.danger.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
	
}
