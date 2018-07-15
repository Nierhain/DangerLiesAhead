package de.nierhain.danger.network;

import de.nierhain.danger.capabilities.skills.ISkills;
import de.nierhain.danger.enums.Ability;
import de.nierhain.danger.handler.SkillsHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static de.nierhain.danger.capabilities.skills.ProviderSkills.CAPABILITY_SKILL;


public class PacketAbilitySkill implements IMessage {
    public PacketAbilitySkill(){}

    private Ability ability;

    public PacketAbilitySkill(Ability ability){
        this.ability = ability;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.ability = ability.valueOf(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(ability.getValue());
    }

    public static class Handler implements IMessageHandler<PacketAbilitySkill, IMessage> {

        @Override
        public IMessage onMessage(PacketAbilitySkill message, MessageContext ctx) {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
            ISkills skillsObj = serverPlayer.getCapability(CAPABILITY_SKILL, null);

            Ability ability = message.ability;
            serverPlayer.getServerWorld().addScheduledTask(() -> {
                switch(ability){
                    case HEALTH:
                        SkillsHandler.skillHealth(serverPlayer);
                        break;
                    case LUCK:
                        SkillsHandler.skillLuck(serverPlayer);
                        break;
                    case MOVEMENT_SPEED:
                        SkillsHandler.skillMovementSpeed(serverPlayer);
                        break;
                    case ATTACK_DAMAGE:
                        SkillsHandler.skillAttackDamage(serverPlayer);
                        break;
                    case ATTACK_SPEED:
                        SkillsHandler.skillAttackSpeed(serverPlayer);
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