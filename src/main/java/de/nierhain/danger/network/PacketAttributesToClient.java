package de.nierhain.danger.network;


import de.nierhain.danger.capabilities.attributes.IAttributes;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_SKILL;


public class PacketAttributesToClient implements IMessage {
    public PacketAttributesToClient(){}
    private int[] abilityLevels = new int[5];
    private int skillPoints;

    @Override
    public void fromBytes(ByteBuf buf) {
        for(int i = 0; i < abilityLevels.length; i++){
            abilityLevels[i] = buf.readInt();
        }
        skillPoints = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for(int i = 0; i < abilityLevels.length; i++){
            buf.writeInt(abilityLevels[i]);
        }
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
                    IAttributes skillsObj = player.getCapability(CAPABILITY_SKILL, null);

                    skillsObj.setHealth(message.abilityLevels[0]);
                    skillsObj.setLuck(message.abilityLevels[1]);
                    skillsObj.setMovementSpeed(message.abilityLevels[2]);
                    skillsObj.setAttackDamage(message.abilityLevels[3]);
                    skillsObj.setAttackSpeed(message.abilityLevels[4]);
                    skillsObj.setSkillpoints(message.skillPoints);
            });
            return null;
        }
    }
}