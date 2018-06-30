package de.nierhain.danger.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandPurgeHealth extends CommandBase {

    @Override
    public String getName() {
        return "purgehp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "purgehp";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeAllModifiers();
        player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
    }
}
