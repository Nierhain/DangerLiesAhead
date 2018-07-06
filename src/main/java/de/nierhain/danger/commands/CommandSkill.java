package de.nierhain.danger.commands;

import de.nierhain.danger.handler.SkillsHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import static de.nierhain.danger.capabilities.skills.ProviderSkills.CAPABILITY_SKILL;

public class CommandSkill extends CommandBase {

    @Override
    public String getName() {
        return "skill";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "skill <attribute> - spends a point in the defined attribute \n available attributes: hp, hunger, attackDamage, attackSpeed, moveSpeed, luck";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length < 1) {
           return;
        }
        String s = args[0];

        if( sender instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) sender;
            if(player.getCapability(CAPABILITY_SKILL, null).getSkillpoints() > 0){
                switch(s){
                    case "hp":
                        SkillsHandler.skillHealth(player);
                        SkillsHandler.removeSkillpoint(player);
                        break;
                    case "moveSpeed":
                        SkillsHandler.skillMovementSpeed(player);
                        SkillsHandler.removeSkillpoint(player);
                        break;
                    case "attackDamage":
                        SkillsHandler.skillAttackDamage(player);
                        SkillsHandler.removeSkillpoint(player);
                        break;
                    case "attackSpeed":
                        SkillsHandler.skillAttackSpeed(player);
                        SkillsHandler.removeSkillpoint(player);
                        break;
                    case "luck":
                        SkillsHandler.skillLuck(player);
                        SkillsHandler.removeSkillpoint(player);
                        break;
                    default:
                        return;
                }
            } else {
                sender.sendMessage(new TextComponentString("No skillpoints available"));
            }

        }

    }
}
