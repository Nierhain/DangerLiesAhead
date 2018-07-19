package de.nierhain.danger.network;

<<<<<<< HEAD
import de.nierhain.danger.capabilities.attributes.IAttributes;
=======
import de.nierhain.danger.capabilities.skills.IAttributes;
>>>>>>> master
import de.nierhain.danger.enums.Attribute;
import de.nierhain.danger.handler.AttributesHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

<<<<<<< HEAD
import static de.nierhain.danger.capabilities.attributes.ProviderAttributes.CAPABILITY_SKILL;
=======
import static de.nierhain.danger.capabilities.skills.ProviderAttributes.CAPABILITY_SKILL;
>>>>>>> master


public class PacketAbilitySkill implements IMessage {
    public PacketAbilitySkill(){}

    private Attribute attribute;

    public PacketAbilitySkill(Attribute attribute){
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

    public static class Handler implements IMessageHandler<PacketAbilitySkill, IMessage> {

        @Override
        public IMessage onMessage(PacketAbilitySkill message, MessageContext ctx) {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            IAttributes skillsObj = serverPlayer.getCapability(CAPABILITY_SKILL, null);

            Attribute attribute = message.attribute;
            serverPlayer.getServerWorld().addScheduledTask(() -> {
                switch(attribute){
                    case HEALTH:
                        AttributesHandler.skillHealth(serverPlayer);
                        break;
                    case LUCK:
                        AttributesHandler.skillLuck(serverPlayer);
                        break;
                    case MOVEMENT_SPEED:
                        AttributesHandler.skillMovementSpeed(serverPlayer);
                        break;
                    case ATTACK_DAMAGE:
                        AttributesHandler.skillAttackDamage(serverPlayer);
                        break;
                    case ATTACK_SPEED:
                        AttributesHandler.skillAttackSpeed(serverPlayer);
                        break;
                }
                int[] abilityLevels = {skillsObj.getHealth(), skillsObj.getLuck(), skillsObj.getMovementSpeed(), skillsObj.getAttackDamage(), skillsObj.getAttackSpeed()};
                int skillPoints = skillsObj.getSkillpoints();
                PacketHandler.INSTANCE.sendTo(new PacketGetAbilities(abilityLevels, skillPoints), serverPlayer);
            });

            return null;
        }
    }
}