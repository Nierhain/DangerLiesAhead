package de.nierhain.danger.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandPurge extends CommandBase {

    @Override
    public String getName() {
        return "purge";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "purge";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(new TextComponentString("purge command executed"));
    }
}
