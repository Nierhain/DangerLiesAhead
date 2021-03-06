package de.nierhain.dangerliesahead.network;


import de.nierhain.dangerliesahead.capabilities.attributes.IAttributes;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static de.nierhain.dangerliesahead.capabilities.attributes.ProviderAttributes.CAPABILITY_ATTRIBUTES;


public class PacketAttributesToClient implements IMessage {
    public PacketAttributesToClient(){}
    private int[] abilityLevels = new int[5];
    private int skillPoints;

    @Override
    public void fromBytes(ByteBuf buf) {
        abilityLevels[0] = buf.readInt();
        abilityLevels[1] = buf.readInt();
        abilityLevels[2] = buf.readInt();
        abilityLevels[3] = buf.readInt();
        abilityLevels[4] = buf.readInt();
        skillPoints = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(abilityLevels[0]);
        buf.writeInt(abilityLevels[1]);
        buf.writeInt(abilityLevels[2]);
        buf.writeInt(abilityLevels[3]);
        buf.writeInt(abilityLevels[4]);
        buf.writeInt(skillPoints);
    }

    public PacketAttributesToClient(int[] abilityLevels, int skillPoints){
        this.abilityLevels = abilityLevels;
        this.skillPoints = skillPoints;
    }

    public static class Handler implements IMessageHandler<PacketAttributesToClient, IMessage>{

        @Override
        public IMessage onMessage(PacketAttributesToClient message, MessageContext ctx) {

            Minecraft minecraft = Minecraft.getMinecraft();
            minecraft.addScheduledTask(() -> {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    IAttributes skillsObj = player.getCapability(CAPABILITY_ATTRIBUTES, null);

                    skillsObj.setAllAttributes(message.abilityLevels);
                    skillsObj.setSkillpoints(message.skillPoints);
            });
            return null;
        }
    }
}
